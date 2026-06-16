package com.example.demo.Service;


import com.example.demo.dto.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final JavaMailSender mailSender;

    public void sendContactMail(ContactRequest request){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("aminboualiaminbouali@gmail.com");
        message.setSubject("New message from: " + request.getName());
        message.setText(
                "Name: " + request.getName() + "\n" +
                "Email: " + request.getEmail() + "\n\n" +
                "Message:\n" + request.getMessage()
        );

        message.setReplyTo(request.getEmail());

        mailSender.send(message);
    }

}
