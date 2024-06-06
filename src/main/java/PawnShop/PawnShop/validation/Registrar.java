package PawnShop.PawnShop.validation;

import PawnShop.PawnShop.model.security.User;
import org.springframework.stereotype.Component;

@Component
public interface Registrar {
    User registerUser(User user);
}
