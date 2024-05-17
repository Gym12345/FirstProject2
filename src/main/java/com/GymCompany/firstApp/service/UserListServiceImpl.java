package com.GymCompany.firstApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.repository.UserListRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class UserListServiceImpl implements UserListService {

    @Autowired
    private UserListRepository userListRepository;

    @Override
    @Transactional
    public void registerUser(UserListDTO userDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());

        UserListDTO newUser = new UserListDTO();
        newUser.setUserId(userDTO.getUserId());
        newUser.setUserPw(encodedPassword);
        newUser.setUserName(userDTO.getUserName());
        newUser.setJoinDate(LocalDate.now());
        newUser.setLastLoginTime(LocalDateTime.now());

        userListRepository.save(newUser);  // save is defined in JpaRepository interface
    }

    @Override
    @Transactional(readOnly = true)
    public int redundancyCheck(String userId) {
        List<UserListDTO> userList = userListRepository.findAll();

        userId = userId.replaceAll("^\"|\"$", "");  // Remove quotes from userId if present

        for (UserListDTO user : userList) {
            if (user.getUserId().equals(userId)) {
                return 1;
            }
        }

        return 0;
    }

    @Override
    public int loginCheck(String userId, String userPw) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPw = userListRepository.findPasswordByUserId(userId);
        if (hashedPw != null && passwordEncoder.matches(userPw, hashedPw)) {
            return 1;
        }

        return 0;
    }

   
}
