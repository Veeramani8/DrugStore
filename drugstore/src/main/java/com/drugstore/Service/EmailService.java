package com.drugstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    public void sendOTP(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP - Drug Store Registration");
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
    }

   

     class OtpGenerator {
        private static final SecureRandom random = new SecureRandom();

        public static String generateOtp(int length) {
            String digits = "0123456789";
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < length; i++) {
                sb.append(digits.charAt(random.nextInt(digits.length())));
            }

            return sb.toString();
        }
     }
}
