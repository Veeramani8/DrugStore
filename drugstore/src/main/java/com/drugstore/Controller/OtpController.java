package com.drugstore.Controller;

import com.drugstore.Model.User;
import com.drugstore.Service.EmailService;
import com.drugstore.Service.OtpService;
import com.drugstore.Service.UserService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user, HttpSession session, Model model) {
        if (userService.isEmailExists(user.getEmail())) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }

        String otp = otpService.generateOTP(user.getEmail());
        
        emailService.sendOTP(user.getEmail(), otp);

        session.setAttribute("tempUser", user);
        session.setAttribute("otpEmail", user.getEmail());

        return "redirect:/verify-otp";
    }

    @GetMapping("/verify-otp")
    public String showOTPPage() {
        return "verify-otp";
    }

    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam("otp") String otp, HttpSession session, Model model) {
        String email = (String) session.getAttribute("otpEmail");
        User user = (User) session.getAttribute("tempUser");

        if (email == null || user == null) {
            return "redirect:/register";
        }

        if (otpService.verifyOTP(email, otp)) {
        	 user.setIsactive(true);
            userService.saveRegisteredUser(user);
            otpService.clearOTP(email);
            session.removeAttribute("otpEmail");
            session.removeAttribute("tempUser");
            model.addAttribute("message", "Registration successful! You can now log in.");
            return "login";
        } else {
            model.addAttribute("error", "Invalid OTP. Please try again.");
            return "verify-otp";
        }
    }
}
