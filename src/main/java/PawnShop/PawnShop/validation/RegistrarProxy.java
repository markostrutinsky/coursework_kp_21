package PawnShop.PawnShop.validation;

import PawnShop.PawnShop.exception.ValidationException;
import PawnShop.PawnShop.model.security.User;
import PawnShop.PawnShop.repository.UserRepository;
import PawnShop.PawnShop.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RegistrarProxy implements Registrar {
    @Qualifier("userServiceImpl")
    private final UserServiceImpl userService;
    private final List<Registrar> registrars;

    @Override
    public User registerUser(User user) {
        if (isValid(user)) {
            for (Registrar registrar : registrars) {
                if (registrar instanceof UserServiceImpl) {
                    return userService.registerUser(user);
                }
            }
        }
        throw new ValidationException("Invalid user");
    }
    private boolean isValid(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return false;
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
