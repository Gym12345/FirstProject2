package com.GymCompany.firstApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable()
            .logout(logout -> logout.permitAll())
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/registerMenu", "/rddCheck", "/registerCheck", "/testPage", "/loginCheck", "/favicon.ico").permitAll()
                    .requestMatchers("/normalUser/**").hasAuthority("normalUser")

                    .anyRequest().authenticated()
            )
            .formLogin()
            .loginPage("/loginMenu")
            .permitAll()
            .successHandler((request, response, authentication) -> {
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("SecurityContextHolder at filterChain:"+SecurityContextHolder.getContext().getAuthentication());
                response.sendRedirect("/normalUser/afterLogin");
            });
            ;

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SimpleAuthorityMapper authorityMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setPrefix("");  // Removing the default ROLE_ prefix
        return authorityMapper;
    }
}
