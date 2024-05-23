package com.GymCompany.firstApp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(AbstractHttpConfigurer::disable) // Disable CSRF using method reference
	        .sessionManagement(sessionManagement ->
	            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Configure session management using lambda
	        )
	        .authorizeHttpRequests(auth -> {
	            auth
	                .requestMatchers("/","/invalidateSession" ,"/registerMenu", "/invalidateSession", "/loginCheck", "/rddCheck",
	                                  "/registerCheck", "/testPage", "/loginMenu", "/favicon.ico")
	                .permitAll() // Permit all for specified endpoints
	                .requestMatchers("/normalUser/**")
	                .hasAuthority("normalUser") // Restrict access to paths under /normalUser/ to those with 'normalUser' authority
	                .anyRequest().authenticated(); // Require authentication for all other requests but accessible with any kind of authentication
	        });
//	        .formLogin(formLogin -> 
//	            formLogin
//	                .loginPage("/login")
//	                .permitAll() // Configure form login and permit all users to access the login page
//	        );

	    return http.build();
	}

	  
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
  
}
