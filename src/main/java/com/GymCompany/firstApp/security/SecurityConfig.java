package com.GymCompany.firstApp.security;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.GymCompany.firstApp.service.UserListService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	
	
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // method Chaining type is deprecated now we have to use Lambda DSL
	    http
	        .csrf(AbstractHttpConfigurer::disable) // Disable CSRF using method reference
	        .sessionManagement(sessionManagement ->
	            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Configure session management using lambda
	        )
	        .authorizeHttpRequests(auth -> {
	            auth
	                .requestMatchers("/" ,"/registerMenu", "/invalidateSession", "/loginCheck", "/rddCheck",
	                                  "/registerCheck", "/testPage", "/loginMenu", "/favicon.ico").permitAll() 
	              
	                .requestMatchers("/normalUser/**")
	                .hasAuthority("normalUser") // restricting access , it refers to session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

	                .anyRequest().authenticated(); // Require authentication for all other requests but accessible with any kind of authentication
	        });
	    
     


	    return http.build();
	}

	

    
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder();
    }
  

  
}
