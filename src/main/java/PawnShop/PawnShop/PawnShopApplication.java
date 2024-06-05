package PawnShop.PawnShop;

import PawnShop.PawnShop.service.observer.NotificationService;
import PawnShop.PawnShop.service.observer.impl.GmailNotification;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PawnShopApplication {


	public static void main(String[] args) {
		SpringApplication.run(PawnShopApplication.class, args);
	}

}
