package PawnShop.PawnShop.service.observer.impl;

import PawnShop.PawnShop.service.observer.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class GmailNotification implements Notification {

    private JavaMailSender javaMailSender;

    @Autowired
    public GmailNotification(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendNotification(String subject, String body) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("markostrutinsky@gmail.com");
        mail.setSubject(subject);
        mail.setText(body);
        javaMailSender.send(mail);
    }
}
