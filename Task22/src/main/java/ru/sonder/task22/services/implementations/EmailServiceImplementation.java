package ru.sonder.task22.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.sonder.task22.services.EmailService;

@Service
@RequiredArgsConstructor
public class EmailServiceImplementation implements EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Async
    @Override
    public void sendEmail(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo("mralexey1@mail.ru");
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
