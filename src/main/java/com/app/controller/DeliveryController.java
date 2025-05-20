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

import com.app.pojos.Customer;
import com.app.pojos.DeliveryPartner;
import com.app.service.IPartnerService;

import java.util.Random;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private IPartnerService service;
	
	public DeliveryController() {
		// TODO Auto-generated constructor stub
		System.out.println("in contructor of delivery controller");
	}
	
	@GetMapping("/login")
	public String showDeliveryPage() {
		return "/delivery/deliverylogin";
	}
	
	
	@GetMapping("/reg")
	public String showRegistrationPage() {
		System.out.println("In show Registration page");
		return "/delivery/deliveryreg";
	}
	
	
	@PostMapping("/reg")
	public String processRegisterForm(@RequestParam String name,@RequestParam String email,@RequestParam String pass,
			@RequestParam String mobile,@RequestParam String area,@RequestParam String license,  Model map) {
		try {
		System.out.println("in process registration form");
		DeliveryPartner d = new DeliveryPartner(name,email,pass,mobile,area,license );

		map.addAttribute("success", "**Delivery Partner registration successful");
		String messgae= service.registerPartner(d);
		System.out.println(messgae);
		return "/delivery/deliverylogin";
		
		}
		catch (Exception e) {
			map.addAttribute("error" ,"**Details are already used , please enter valid details");
			return "/delivery/deliveryreg";
		}
	}
	
	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email , @RequestParam String pass , RedirectAttributes
			flashmap,Model map,HttpSession session ) {
		System.out.println("In delivery process login form");
		try {
		
		 DeliveryPartner partner = service.authenticatePartner(email, pass);
		 session.setAttribute("delivery_details", partner);
		
		flashmap.addFlashAttribute("message","Login Successful");
		System.out.println("login successful");
		return "/delivery/deliveryhome";
		}
		catch(RuntimeException e){
			System.out.println("Login unsuccessful");
			map.addAttribute("error","**Invalid Email or password");
			return "/delivery/deliverylogin";
		}
	}
	
	@GetMapping("/deliveryhome")
	public String showDeliveryHome() {
		System.out.println("In show Delivery Home");
		return "/delivery/deliveryhome";
	}
	
	@GetMapping("/deliveryprofile")
	public String showDeliveryProfile() {
		System.out.println("In show deliveryProfile");
		return "/delivery/deliveryprofile";
	}
	
	@GetMapping("/deliveryorders") 
	public String showOrders(HttpSession session, Model map ) {
		DeliveryPartner d = (DeliveryPartner)session.getAttribute("delivery_details");
		
		map.addAttribute("order_list",service.getorders(d.getDid()));
		//System.out.println(service.getorders(d.getDid()));
		return "/delivery/deliveryorders";
	}
	
	@GetMapping("/pendingorders")
	public String getPendingOrders(HttpSession session, Model map) {
		DeliveryPartner d = (DeliveryPartner)session.getAttribute("delivery_details");
		
		map.addAttribute("pending_orders",service.getPendingOrders(d.getDid()));
		return "/delivery/pendingorders";
	}

	@GetMapping("/updateorderstatus")
	public String updateOrderStatus(@RequestParam int orderId, HttpSession session) {
		System.out.println("In update Order status");

		Customer c = service.getCustomerDetails(orderId);
		service.updateOrderStatus(orderId);

		DeliveryPartner d = (DeliveryPartner) session.getAttribute("delivery_details");
		session.setAttribute("delivery_details", service.updateEarning(d.getDid()));

		try {
			System.out.println("In sending mail process");

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("courier.delivery.app@gmail.com"); // ✅ Add this line
			message.setTo(c.getCemail());
			message.setSubject("Order Delivered Successfully");
			String text = "Hello, " + c.getCname()
					+ ", your order has been delivered successfully by our Delivery Partner: "
					+ d.getDname() + ".\n\nThank you for choosing Wefast!";
			message.setText(text);

			sender.send(message);
			System.out.println("Confirmation email sent to " + c.getCemail());

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("mail_error", "Failed to send confirmation email.");
		}

		return "/delivery/pendingorders";
	}

	
	@GetMapping("/delete/{did}")
	public String deleteDeliveryPartner(@PathVariable int did) {
		System.out.println("in delete deliery partner ");
		service.deletePartner(did); 
		return "/index";
	}
	
	@GetMapping("/update")
	public String showUpdateForm() {
		
		return  "/delivery/update";
	}
	
	@PostMapping("/update/{did}")
	public String processUpdateForm(@RequestParam String name , @RequestParam String email , @RequestParam String mobile , 
			@RequestParam String area , @RequestParam String licenceno ,HttpSession session) {
		System.out.println("In process Update form");
		
		DeliveryPartner partner = (DeliveryPartner) session.getAttribute("delivery_details");
		DeliveryPartner d = new DeliveryPartner(partner.getDid() , name , email , mobile , area , licenceno);
		session.setAttribute("delivery_details", service.updatePartner(d));
		
		return "/delivery/deliveryprofile";
		
	}
	

	
	@GetMapping("/logout") 
	public String deliveryLogout(HttpSession session, Model map, HttpServletRequest request, 
			HttpServletResponse response) {
		System.out.println("in delivery's logout");
		// get user dtls from session scope n add it under model attribute
		map.addAttribute("user_details", session.getAttribute("delivery_details"));
		// invalidate HttpSession
		session.invalidate();
		//set refresh header of HTTP resp
		response.setHeader("refresh", "5;url="+"http://localhost:8080/");
		return "/delivery/deliverylogout";// forward view name : /WEB-INF/view/user/logout.jsp
	}

	@GetMapping("/forgotpassword")
	public String showForgotPasswordForm() {
		return "/delivery/forgotpassword";  // JSP page
	}

	@PostMapping("/forgotpassword")
	public String processForgotPassword(@RequestParam String email, HttpSession session, Model map) {
		DeliveryPartner partner = service.findByEmail(email);
		if (partner != null) {
			String otp = String.format("%04d", new Random().nextInt(10000));
			session.setAttribute("otp", otp);
			session.setAttribute("email", email);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("courier.delivery.app@gmail.com"); // must match spring.mail.username
			message.setTo(email);
			message.setSubject("Password Reset OTP");
			message.setText("Your OTP for resetting password is: " + otp);
			sender.send(message);

			return "/delivery/enterotp";
		} else {
			map.addAttribute("error", "Email not found!");
			return "/delivery/forgotpassword";
		}
	}

	@PostMapping("/verifyotp")
	public String verifyOtp(@RequestParam String otp, HttpSession session, Model map) {
		String storedOtp = (String) session.getAttribute("otp");
		if (storedOtp != null && storedOtp.equals(otp)) {
			return "/delivery/resetpassword";
		} else {
			map.addAttribute("error", "Invalid OTP!");
			return "/delivery/enterotp";
		}
	}

	@PostMapping("/resetpassword")
	public String resetPassword(@RequestParam String newPassword, HttpSession session, Model map) {
		String email = (String) session.getAttribute("email");
		DeliveryPartner partner = service.findByEmail(email);
		if (partner != null) {
			partner.setDpassword(newPassword);  // Set new password
			service.updatePartner(partner);
			session.invalidate();
			map.addAttribute("success", "Password reset successfully. Please login.");
			return "/delivery/deliverylogin";
		} else {
			map.addAttribute("error", "Something went wrong.");
			return "/delivery/resetpassword";
		}
	}



}
