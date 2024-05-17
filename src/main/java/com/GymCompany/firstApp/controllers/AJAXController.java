package com.GymCompany.firstApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
            userListService.registerUser(userDTO);
            result = 1;
            return result;
        } catch (IllegalArgumentException e) {
            result = 0;
            return result;
        }
    }

    @PostMapping("/loginCheck")
    public int loginCheck(@RequestBody TempUserDTO tempUserDTO) {
        int result = 0;
        String userId = tempUserDTO.getUserId();
        String userPw = tempUserDTO.getUserPw();
        result=userListService.loginCheck(userId, userPw);
        
        System.out.println("rsu:"+result);
        
        return result;
    }
    
    
    
}
