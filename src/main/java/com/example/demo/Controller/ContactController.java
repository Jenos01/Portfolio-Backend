package com.example.demo.Controller;


import com.example.demo.Service.ContactService;
import com.example.demo.dto.ContactRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<String> handleContact(@RequestBody ContactRequest request) {
        try{
            contactService.sendContactMail(request);
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Failed to send message");
        }
    }
}
