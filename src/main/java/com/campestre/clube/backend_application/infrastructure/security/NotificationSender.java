package com.campestre.clube.backend_application.infrastructure.security;

import com.campestre.clube.backend_application.core.adapter.NotificationGateway;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

@Component
public class NotificationSender implements NotificationGateway {
    private final JavaMailSender mailSender;

    public NotificationSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, false);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw INTERNAL_ERROR_SEND_EMAIL;
        }
    }
}
