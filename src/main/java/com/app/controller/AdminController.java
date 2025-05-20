package com.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.Admin;

import com.app.service.IAdminService;

import java.util.Random;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private IAdminService service;

	@Autowired
	private JavaMailSender sender;
	
	public AdminController() {
		// TODO Auto-generated constructor stub
		System.out.println("In constructor of AdminController");
	}
	
	@GetMapping("/login")
	public String showDeliveryPage() {
		System.out.println("In show delivery page");
		return "/admin/adminlogin";
	}
	
	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email , @RequestParam String pass , RedirectAttributes
			flashmap,Model map,HttpSession session ) {
		System.out.println("In Admin process login form");
		try {
		
		 Admin admin = service.authenticateAdmin(email, pass);
		 session.setAttribute("admin_details", admin);
		
		flashmap.addFlashAttribute("message","Login Successful");
		System.out.println("login successful");
		return "/admin/adminhome";
		}
		catch(RuntimeException e){
			System.out.println("Login unsuccessful");
			map.addAttribute("error","**Invalid Email or password");
			return "/admin/adminlogin";
		}
	}
	
	
	@GetMapping("/allcustomer")
	public String getAllCustomers(Model map ) {
		map.addAttribute("all_customers" , service.getAllCustomers());
		
		return "/admin/getallcustomer";
	}
	
	@GetMapping("/allpartners")
	public String getAllPartners(Model map ) {
		map.addAttribute("all_partners" , service.getAllPartners());
		
		return "/admin/getallpartners";
	}
	
	@GetMapping("/allorders")
	public String getAllOrders(Model map) {
		map.addAttribute("all_orders",service.getAllOrders());
		
		
		return "/admin/getallorders";
	}
	
	@GetMapping("/deletecustomer/{cid}") 
	public String deleteCustomer(@PathVariable int cid) {
		
		System.out.println("In delete customer ");
		service.deleteCustomer(cid);
		return "/admin/getallcustomer";
	}
	
	@GetMapping("/deletepartner/{did}") 
	public String deletePartner(@PathVariable int did) {
		
		System.out.println("In delete customer ");
		service.deletePartner(did);
		return "/admin/getallpartners";
	}
	
	
	
	
	
	

	@GetMapping("/logout") 
	public String adminLogout(HttpSession session, Model map, HttpServletRequest request, 
			HttpServletResponse response) {
		System.out.println("in admin logout");
		// get user dtls from session scope n add it under model attribute
		map.addAttribute("user_details", session.getAttribute("admin_details"));
		// invalidate HttpSession
		session.invalidate();
		//set refresh header of HTTP resp
		response.setHeader("refresh", "5;url="+"http://localhost:8080/");
		return "/admin/adminlogout";// forward view name : /WEB-INF/view/user/logout.jsp
	}

	@GetMapping("/forgotpassword")
	public String showForgotPasswordForm() {
		return "/admin/forgotpassword";  // JSP to enter email
	}

	@PostMapping("/forgotpassword")
	public String processForgotPassword(@RequestParam String email, HttpSession session, Model map) {
		Admin admin = service.findByEmail(email);
		if (admin != null) {
			String otp = String.format("%04d", new Random().nextInt(10000));
			session.setAttribute("otp", otp);
			session.setAttribute("email", email);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("courier.delivery.app@gmail.com");
			message.setTo(email);
			message.setSubject("Admin Password Reset OTP");
			message.setText("Your OTP for resetting your admin password is: " + otp);
			sender.send(message);

			return "/admin/enterotp";
		} else {
			map.addAttribute("error", "Email not found!");
			return "/admin/forgotpassword";
		}
	}

	@PostMapping("/verifyotp")
	public String verifyOtp(@RequestParam String otp, HttpSession session, Model map) {
		String sessionOtp = (String) session.getAttribute("otp");
		if (sessionOtp != null && sessionOtp.equals(otp)) {
			return "/admin/resetpassword";
		} else {
			map.addAttribute("error", "Invalid OTP!");
			return "/admin/enterotp";
		}
	}

	@PostMapping("/resetpassword")
	public String resetPassword(@RequestParam String newPassword, HttpSession session, Model map) {
		String email = (String) session.getAttribute("email");
		Admin admin = service.findByEmail(email);
		if (admin != null) {
			admin.setApassword(newPassword);
			service.updateAdmin(admin);
			session.invalidate();
			map.addAttribute("success", "Password reset successfully. Please login.");
			return "/admin/adminlogin";
		} else {
			map.addAttribute("error", "Something went wrong.");
			return "/admin/resetpassword";
		}
	}


}
