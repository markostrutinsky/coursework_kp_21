package PawnShop.PawnShop;

import PawnShop.PawnShop.model.Role;
import PawnShop.PawnShop.model.User;
import PawnShop.PawnShop.repository.RoleRepository;
import PawnShop.PawnShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Seed roles
        for (String roleName : List.of("ROLE_USER", "ROLE_ADMIN")) {
            if (roleRepository.findByName(roleName).isEmpty()) {
                roleRepository.save(new Role(roleName));
            }
        }
        roleRepository.flush();

        // Seed default admin only once
        if (userRepository.findByEmail("admin@pawnshop.com").isEmpty()) {
            Role userRole  = roleRepository.findByName("ROLE_USER").orElseThrow();
            Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseThrow();

            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("System");
            admin.setEmail("admin@pawnshop.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRoles(List.of(userRole, adminRole));
            userRepository.save(admin);
            System.out.println(">>> Default admin created: admin@pawnshop.com / admin123");
        }
        System.out.println(">>> Roles initialized");
    }
}
