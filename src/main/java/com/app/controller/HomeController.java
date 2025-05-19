package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller


public class HomeController {
	
	public HomeController() {
		// TODO Auto-generated constructor stub
		System.out.println("In Constructor of HomeController");
	}
	
	@GetMapping("/")
	public String showHomePage() {
		return "/index";
	}

	@GetMapping("/home")
	public String showHomePage1() {
		return "/index";
	}

	@GetMapping("/services")
	public String showServicesPage() {
		return "/services";
	}

	@GetMapping("/about")
	public String showAboutPage() {
		return "/aboutus";
	}

	@GetMapping("/contact")
	public String showContactPage() {
		return "/contact";
	}



}
