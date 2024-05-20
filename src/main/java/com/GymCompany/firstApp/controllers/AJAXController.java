package com.GymCompany.firstApp.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.GymCompany.firstApp.model.TempUserDTO;
import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.service.UserListService;

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

    @PostMapping("/loginCheck")
    public int loginCheck(@RequestBody TempUserDTO tempUserDTO) {
        String userId = tempUserDTO.getUserId();
        String userPw = tempUserDTO.getUserPw();

        String hashedPw = userListService.getPasswordByUserId(userId);
        
        if (hashedPw != null && new BCryptPasswordEncoder().matches(userPw, hashedPw)) {
        	UserListDTO user = userListService.loadUserByUsername(userId);
        	System.out.println("UserListDTO:"+user);
        	
            // Create authentication token
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUserId(), null, Collections.singletonList(new SimpleGrantedAuthority(user.getAuth()))
            );// Principal=Gym1, Credentials=[PROTECTED], Authenticated=true, Details=null, Granted Authorities=[normalUser]
            
           
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        	
            System.out.println("Authentication Token: " + SecurityContextHolder.getContext().getAuthentication());// 성공토큰생성완료 but still not refering to it
            
            
        	System.out.println(" login success");
            return 1; // Login successful
        }
        
       
        System.out.println("login failed");
        return 0; // Login failed
    }
    
    
    
}
