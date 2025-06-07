package com.drugstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();

    @Autowired
    private JavaMailSender mailSender;

    public String generateOTP(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpStorage.put(email, otp);
        return otp;
    }

    public void sendOTPEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp + "\n\nIt is valid for 5 minutes.");

        mailSender.send(message);
    }

    public boolean verifyOTP(String email, String otp) {
        String storedOTP = otpStorage.get(email);
        if (storedOTP != null && storedOTP.equals(otp)) {
            otpStorage.remove(email); // clear OTP after success
            return true;
        }
        return false;
    }

    public void clearOTP(String email) {
        otpStorage.remove(email);
    }

	
}
