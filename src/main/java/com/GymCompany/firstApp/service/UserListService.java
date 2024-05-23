package com.GymCompany.firstApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.GymCompany.firstApp.model.UserListDTO;
import com.GymCompany.firstApp.repository.UserListRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class UserListService implements UserDetailsService {

    @Autowired
    private UserListRepository userListRepository;

    private final Logger logger = LoggerFactory.getLogger(UserListService.class);

    public int save(UserListDTO userDTO) { //register
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());
       
        UserListDTO user = new UserListDTO.Builder()  //빌더 패턴 , UserListDTO 에 정의되어있음
                .userId(userDTO.getUserId())
                .userPw(encodedPassword)
                .userName(userDTO.getUserName())
                .joinDate(LocalDate.now())
                .lastLoginTime(LocalDateTime.now())
                .auth(userDTO.getAuth())
                .build();

        return userListRepository.save(user).getUlid();
    }

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

    @Transactional(readOnly = true)  // for login 
    public String getPasswordByUserId(String userId) {
        return userListRepository.findPasswordByUserId(userId);
    }

    @Transactional(readOnly = true)
    public UserListDTO loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserListDTO user = userListRepository.findByUserId(userId);
        System.out.println("user:"+user.getUserId());
       
        return user;
    }
}
