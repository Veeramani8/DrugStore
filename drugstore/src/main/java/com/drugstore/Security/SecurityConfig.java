package com.drugstore.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.drugstore.Service.CustomUserDetailsService;
import com.drugstore.Service.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private LoginSuccessHandler LoginSuccessHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**" ,"/error", "/login", "/register","/verify-otp","/send-otp").permitAll() // Public endpoints
                .requestMatchers("/reports/**","/admin/**").hasRole("ADMIN")
                .requestMatchers("/edit/**", "/delete/**").hasRole("ADMIN")
                .requestMatchers("/pharmacist/**").hasRole("PHARMACIST")
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form
                .loginPage("/login")
                .successHandler(LoginSuccessHandler)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
        throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
