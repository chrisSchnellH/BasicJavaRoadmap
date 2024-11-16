package com.example.DartsInventory.service;

import com.example.DartsInventory.dto.MailBody;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSimpleMessage(MailBody mailbody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailbody.to());
        message.setFrom(""); // enter your email (same email as in application.yml)
        message.setSubject(mailbody.subject());
        message.setText(mailbody.text());

        javaMailSender.send(message);
    }
}
