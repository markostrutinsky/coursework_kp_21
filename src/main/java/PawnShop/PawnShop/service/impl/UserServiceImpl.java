/*
package PawnShop.PawnShop.service.impl;

import PawnShop.PawnShop.exception.UserAlreadyExistsException;
import PawnShop.PawnShop.model.Role;
import PawnShop.PawnShop.model.User;
import PawnShop.PawnShop.repository.RoleRepository;
import PawnShop.PawnShop.repository.UserRepository;
import PawnShop.PawnShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User registerUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        if(user.getPassword() == null) {
            throw new NullPointerException("Password cannot be null");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(String email) {
        User theUser = getUser(email);
        if (theUser != null) {
            userRepository.deleteUserByEmail(email);
        }
        userRepository.deleteUserByEmail(email);
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
*/
