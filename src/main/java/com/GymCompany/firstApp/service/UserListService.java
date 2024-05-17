package com.GymCompany.firstApp.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.GymCompany.firstApp.model.UserListDTO;

public interface UserListService {
    void registerUser(UserListDTO userDTO);
    int redundancyCheck(String userId);
    int loginCheck(String userId,String userPw);
    
}
