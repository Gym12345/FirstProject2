package com.GymCompany.firstApp.controllers;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	
//	@Autowired
//	private UserListService userListService;
	
	
	@GetMapping(value = "/")
	public String goHome(HttpServletRequest request) {
		System.out.println("main");
		return "MainPage";
	}
	
	@GetMapping(value = "/loginMenu")
	public String loginMenu(HttpServletRequest request) {
		System.out.println("loginMenu");
		return "loginMenu";
	}
	
	
	
	@GetMapping(value = "/registerMenu")
	public String registerMenu(HttpServletRequest request) {
		System.out.println("registerMenu");

		return "registerMenu";
	}
	
	
	
	
	@GetMapping(value = "/testPage")
	public String test(HttpServletRequest request) {
		System.out.println("test place");

		return "testPage";
	}
	
	

	
	

	
}
