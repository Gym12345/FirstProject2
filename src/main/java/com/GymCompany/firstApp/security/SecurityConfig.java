package com.GymCompany.firstApp.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	 @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//	        httpSecurity
//	            .csrf().disable() // Disable CSRF protection as an example
//	            .logout(logout -> logout.permitAll())
//	  	            .authorizeHttpRequests(authorizeRequests ->
//	                authorizeRequests
//	                    .requestMatchers("/", "/registerMenu", "/loginCheck","/rddCheck", "/registerCheck", "/testPage", "/loginMenu", "/favicon.ico").permitAll()
//	                    .requestMatchers("/normalUser/**").hasAuthority("normalUser")
//	                    .anyRequest().authenticated()
//	            );
//	        return httpSecurity.build();
//	    }
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable()
            .logout(logout -> logout.permitAll())
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Ensure sessions are created when needed
            .and()
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/", "/registerMenu", "invalidateSession","/loginCheck", "/rddCheck", "/registerCheck", "/testPage", "/loginMenu", "/favicon.ico").permitAll()
                .requestMatchers("/normalUser/**").hasAuthority("normalUser")
                .anyRequest().authenticated()
            );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
  
}
