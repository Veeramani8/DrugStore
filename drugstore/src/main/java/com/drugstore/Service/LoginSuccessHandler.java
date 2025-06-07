package com.drugstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.drugstore.Model.User;
import com.drugstore.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String username = authentication.getName();

        Optional<User> userOpt = userRepository.findByUsername(username);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String email = user.getEmail();

            if (email != null && email.contains("@")) {
                notificationService.sendNotification(
                    email,
                    "Login Notification",
                    "Hello " + user.getFullName() + ", you logged in at " + LocalDateTime.now()
                );
            }
        }

        response.sendRedirect("/dashboard");
    }
}
