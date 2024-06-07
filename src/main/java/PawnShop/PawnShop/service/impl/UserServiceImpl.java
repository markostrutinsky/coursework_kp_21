
package PawnShop.PawnShop.service.impl;

import PawnShop.PawnShop.exception.UserAlreadyExistsException;
import PawnShop.PawnShop.model.security.User;
import PawnShop.PawnShop.repository.UserRepository;
import PawnShop.PawnShop.service.UserService;
import PawnShop.PawnShop.validation.Registrar;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
@Qualifier("userserviceImpl")
public class UserServiceImpl implements UserService, Registrar {

    private final UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getEmail())) {
            throw new UserAlreadyExistsException("User with specified email or username already exists!");
        }
        if(user.getPassword() == null) {
            throw new NullPointerException("Password cannot be null");
        }
        return userRepository.save(user);
    }

    @Override
    public User authenticateUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

