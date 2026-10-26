package com.springboot.config;

import com.springboot.entity.Category;
import com.springboot.entity.Product;
import com.springboot.entity.Role;
import com.springboot.entity.User;
import com.springboot.repository.CategoryRepository;
import com.springboot.repository.ProductRepository;
import com.springboot.repository.RoleRepository;
import com.springboot.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Seeds default roles, users and sample products at startup. Safe to re-run: existing rows are skipped.
 * Default accounts (development only):
 *   admin   / admin123   -> ADMIN, USER
 *   manager / manager123 -> MANAGER, USER
 *   user    / user123    -> USER
 */
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedData(RoleRepository roleRepository,
                               UserRepository userRepository,
                               ProductRepository productRepository,
                               CategoryRepository categoryRepository,
                               PasswordEncoder encoder) {
        return args -> {
            List.of("ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER").forEach(name -> {
                if (roleRepository.findByName(name).isEmpty()) {
                    roleRepository.save(new Role(null, name));
                }
            });

            createUserIfMissing(userRepository, roleRepository, encoder,
                    "admin", "admin123", "ROLE_ADMIN", "ROLE_USER");
            createUserIfMissing(userRepository, roleRepository, encoder,
                    "manager", "manager123", "ROLE_MANAGER", "ROLE_USER");
            createUserIfMissing(userRepository, roleRepository, encoder,
                    "user", "user123", "ROLE_USER");

            if (categoryRepository.count() == 0) {
                categoryRepository.saveAll(List.of(
                        new Category(null, "Computers", "Laptops and desktops"),
                        new Category(null, "Peripherals", "Mice, keyboards, monitors and other accessories"),
                        new Category(null, "Audio & Video", "Webcams, headsets and speakers")
                ));
            }

            if (productRepository.count() == 0) {
                Category computers = categoryRepository.findByName("Computers").orElse(null);
                Category peripherals = categoryRepository.findByName("Peripherals").orElse(null);
                Category av = categoryRepository.findByName("Audio & Video").orElse(null);
                productRepository.saveAll(List.of(
                        new Product(null, "Laptop", "14-inch laptop, 16GB RAM, 512GB SSD", 1299.99, 25, computers),
                        new Product(null, "Wireless Mouse", "Ergonomic 2.4GHz wireless mouse", 24.50, 150, peripherals),
                        new Product(null, "Mechanical Keyboard", "RGB backlit, blue switches", 89.90, 80, peripherals),
                        new Product(null, "27-inch Monitor", "QHD IPS display, 75Hz", 329.00, 40, peripherals),
                        new Product(null, "USB-C Hub", "7-in-1 hub with HDMI and card reader", 45.99, 200, peripherals),
                        new Product(null, "Webcam", "1080p webcam with built-in microphone", 59.00, 60, av),
                        new Product(null, "Headset", "Noise-cancelling over-ear headset", 119.00, 0, av)
                ));
            }
        };
    }

    private void createUserIfMissing(UserRepository userRepository, RoleRepository roleRepository,
                                     PasswordEncoder encoder, String username, String rawPassword,
                                     String... roleNames) {
        if (userRepository.existsByUsername(username)) {
            return;
        }
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            roles.add(roleRepository.findByName(roleName)
                    .orElseThrow(() -> new IllegalStateException("Role not found: " + roleName)));
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(rawPassword));
        user.setRoles(roles);
        userRepository.save(user);
    }
}
