package com.GymCompany.firstApp.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.GymCompany.firstApp.model.TempUserDTO;
import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.service.UserListService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AJAXController {
    
    @Autowired
    private UserListService userListService;
    
    public AJAXController(UserListService userListService) {
		super();
		this.userListService = userListService;
	}


	@PostMapping("/rddCheck")
    public int rddUserId(@RequestBody String userIdData) { 
        System.out.println("Data received from client: " + userIdData);
        int result = 0;
        try {
            result = userListService.redundancyCheck(userIdData);
            System.out.println("rddResult:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    @PostMapping("/registerCheck")
    public int registerUser(@RequestBody UserListDTO userDTO) {
        int result = 0;
        try {
        	userDTO.setAuth("normalUser");
        	result=userListService.save(userDTO);
            System.out.println("result:"+result); //returns the id of tuple  that just registered
            
            return result;
        } catch (IllegalArgumentException e) {
            result = 0;
            return result;
        }
    }

   
    
    
    
}
