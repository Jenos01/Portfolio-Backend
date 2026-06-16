package com.example.demo.Service;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    //private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
    private final UserService userService;

    public void sendResetLink(String email) {
//        Users user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("No account found with this email"));


        Users user = userService.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("No account found with this email");
        }

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        user.setResetPasswordTokenExpiry(LocalDateTime.now().plusMinutes(30));
        userRepository.save(user);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("🚇 Metro · Reset Your Password");
        message.setText(
                "You requested a password reset.\n\n" +
                        "Click the link below to reset your password (valid for 30 minutes):\n\n" +
                        "If you didn't request this, ignore this email."
        );
        mailSender.send(message);
    }

    public void resetPassword(ResetPasswordRequest request) {
        Users user = userRepository.findByResetPasswordToken(request.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        if (user.getResetPasswordTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token has expired");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        user.setResetPasswordToken(null);
        user.setResetPasswordTokenExpiry(null);
        userRepository.save(user);
    }
}