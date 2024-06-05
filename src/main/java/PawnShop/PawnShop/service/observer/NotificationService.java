package PawnShop.PawnShop.service.observer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private List<Notification> observers = new ArrayList<>();

    public void addObserver(Notification observer) {
        observers.add(observer);
    }

    public void removeObserver(Notification observer) {
        observers.remove(observer);
    }

    public void notify(String subject, String body) {
        for (Notification observer : observers) {
            observer.sendNotification(subject, body);
        }
    }
}
