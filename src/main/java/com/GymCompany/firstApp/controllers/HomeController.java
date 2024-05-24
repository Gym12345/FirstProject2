package com.GymCompany.firstApp.controllers;




import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.service.UserListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	

	
	@Autowired
	private UserListService userListService;
	

	
	


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
        System.out.println("Authentication Token: " + SecurityContextHolder.getContext().getAuthentication());

		return "testPage";
	}
	
	
	@PostMapping(value ="/loginCheck")
	public String loginCheck(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId, @RequestParam("password") String password) {
	    String hashedPw = userListService.getPasswordByUserId(userId);
	    System.out.println("hashedPw:" + hashedPw);

	    if (hashedPw != null && new BCryptPasswordEncoder().matches(password, hashedPw)) {
	        UserListDTO user = userListService.loadUserByUsername(userId);
	        System.out.println("UserListDTO:" + user);
	        
	        System.out.println("1. user.getAuthorities():" +user.getAuthorities());
	       
	        // Create authentication token
	        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	            user.getUserId(), null, Collections.singletonList(new SimpleGrantedAuthority(user.getAuth()))
	        ); // this is not jwt

	        SecurityContext context = SecurityContextHolder.createEmptyContext();
	        context.setAuthentication(authenticationToken);
	        SecurityContextHolder.setContext(context);

	        // Save the SecurityContext to the session
	        HttpSession session = request.getSession(true);
	        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

	        System.out.println("Authentication Token: " + SecurityContextHolder.getContext().getAuthentication());
	        
	        return "redirect:/normalUser/afterLogin";
	    }

	    System.out.println("Login failed");
	    return "redirect:/loginMenu?error";
	}

	
	
    


// 	       
// 	        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
// 	            user.getUserId(), null, Collections.singletonList(new SimpleGrantedAuthority(user.getAuth()))
// 	        ); // this is not jwt
//   
// 	       Authentication authentication = authenticationManager.authenticate(authenticationToken);
// 	      SecurityContextHolder.getContext().setAuthentication(authentication);
//

	 

	
}
