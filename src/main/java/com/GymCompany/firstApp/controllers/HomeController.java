package com.GymCompany.firstApp.controllers;




import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.GymCompany.firstApp.model.TempUserDTO;
import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.service.UserListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	

	
	@Autowired
	private UserListService userListService;
	    
	public HomeController(UserListService userListService) {
	super();
	this.userListService = userListService;
	}
	

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
	
	
//	 @PostMapping(value ="/loginCheck")
//	    public String loginCheck(HttpServletRequest request, HttpServletResponse response,@RequestParam("userId") String userId, @RequestParam("password") String password) {
//	        String hashedPw = userListService.getPasswordByUserId(userId);
//	        	System.out.println("hashedPw:"+hashedPw);
//	        	
//	        if (hashedPw != null && new BCryptPasswordEncoder().matches(password, hashedPw)) {
//	            UserListDTO user = userListService.loadUserByUsername(userId);
//	            System.out.println("UserListDTO:" + user);
//	            
//	            // Create authentication token
//	            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//	                user.getUserId(), null, Collections.singletonList(new SimpleGrantedAuthority(user.getAuth()))
//	            );
//	            
//	            
//	            SecurityContext context = SecurityContextHolder.createEmptyContext();
//	            context.setAuthentication(authenticationToken);
//	            SecurityContextHolder.setContext(context);
//	            
//	            System.out.println("Authentication Token: " + SecurityContextHolder.getContext());
//	           
//	            
//	            
//	            
//	      
//	            
//	           
//	            
//	            return "redirect:/normalUser/afterLogin";  // Assuming you have a controller mapping for this URL
//	        }   // 요청 처냄 에러 
//	        
//	        System.out.println("Login failed");
//	        return "redirect:/loginMenu?error";  // Redirect back to the login page with an error message
//	    }
//		
	@PostMapping(value ="/loginCheck")
	public String loginCheck(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId, @RequestParam("password") String password) {
	    String hashedPw = userListService.getPasswordByUserId(userId);
	    System.out.println("hashedPw:" + hashedPw);

	    if (hashedPw != null && new BCryptPasswordEncoder().matches(password, hashedPw)) {
	        UserListDTO user = userListService.loadUserByUsername(userId);
	        System.out.println("UserListDTO:" + user);

	        // Create authentication token
	        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	            user.getUserId(), null, Collections.singletonList(new SimpleGrantedAuthority(user.getAuth()))
	        );

	        SecurityContext context = SecurityContextHolder.createEmptyContext();
	        context.setAuthentication(authenticationToken);
	        SecurityContextHolder.setContext(context);

	        // Save the SecurityContext to the session
	        HttpSession session = request.getSession(true);
	        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

	        System.out.println("Authentication Token: " + SecurityContextHolder.getContext());
	        
	        return "redirect:/normalUser/afterLogin";
	    }

	    System.out.println("Login failed");
	    return "redirect:/loginMenu?error";
	}

	@GetMapping(value = "/invalidateSession")
	public String invalidateSession(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "/";
	}
	
	 

	
}
