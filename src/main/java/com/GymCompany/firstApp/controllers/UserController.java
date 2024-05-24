package com.GymCompany.firstApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/normalUser")
public class UserController {
	@GetMapping(value = "/afterLogin")
	public String afterLogin(HttpServletRequest request) {
		System.out.println("afterLogin");
		
		return "afterLoginPage";
	}
	@GetMapping(value = "/invalidateSession")  // temporarily using this , delete this later
	public String invalidateSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "redirect:/";
	}
}
