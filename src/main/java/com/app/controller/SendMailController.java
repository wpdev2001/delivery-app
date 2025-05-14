package com.app.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.pojos.Email;

@Controller
@Scope("session")
@RequestMapping("/email")
public class SendMailController {
	@Autowired
	private JavaMailSender sender;

	@GetMapping("/send_mail")
	public String showForm(Model m) {
		m.addAttribute(new Email());
		return "/send_mail";
	}

	@PostMapping("/send_mail")
	public String processForm(Email em, BindingResult res, HttpSession session) {
		Random r = new Random();
		String randomnumber = String.format("%04d", r.nextInt(10000)); // up to 9999

		System.out.println("Generated OTP: " + randomnumber);
		System.out.println("Sending to: " + em.getDestEmail());

		SimpleMailMessage mesg = new SimpleMailMessage();
		mesg.setFrom("courier.delivery.app@gmail.com"); // This must match the authenticated user
		mesg.setTo(em.getDestEmail());
		mesg.setSubject("Your OTP for placing the order");
		mesg.setText("Your OTP is: " + randomnumber);

		session.setAttribute("otp", randomnumber);

		sender.send(mesg);

		return "/sent-mail";
	}
}
