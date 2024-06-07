package PawnShop.PawnShop.configuration;

import PawnShop.PawnShop.service.observer.NotificationService;
import PawnShop.PawnShop.service.observer.impl.GmailNotification;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    private final NotificationService notificationService;
    private final GmailNotification gmailNotification;
    @Autowired
    public MailConfig(NotificationService notificationService, GmailNotification gmailNotification) {
        this.notificationService = notificationService;
        this.gmailNotification = gmailNotification;
    }

    @PostConstruct
    public void init() {
        notificationService.addObserver(gmailNotification);
    }
}
