package com.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.springboot.dto.AuthRequest;
import com.springboot.entity.Role;
import com.springboot.entity.User;
import com.springboot.repository.RoleRepository;
import com.springboot.repository.UserRepository;
import com.springboot.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@Tag(name = "Authentication", description = "Sign in and register users")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;

    @Operation(summary = "Sign in and receive a JWT")
    @PostMapping("/signin")
    public String authenticateUser(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        Set<String> requested = request.getRoles();
        if (requested == null || requested.isEmpty()) {
            requested = Set.of("user");
        }
        Set<Role> roles = new HashSet<>();
        for (String name : requested) {
            String roleName = switch (name.toLowerCase()) {
                case "admin" -> "ROLE_ADMIN";
                case "manager" -> "ROLE_MANAGER";
                case "user" -> "ROLE_USER";
                default -> null;
            };
            if (roleName == null) {
                return ResponseEntity.badRequest().body("Error: Role '" + name + "' is not found!");
            }
            roles.add(roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        }
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encoder.encode(request.getPassword()));
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully!");
    }
}
