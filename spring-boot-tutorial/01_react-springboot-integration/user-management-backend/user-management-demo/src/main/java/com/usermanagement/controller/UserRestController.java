package com.usermanagement.controller;

import com.usermanagement.entity.User;
import com.usermanagement.exception.RecordNotFoundException;
import com.usermanagement.repository.UserRepository;
import org.hibernate.dialect.lock.OptimisticForceIncrementLockingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<?> fetchAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> fetchUserById(@PathVariable long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user")
    public ResponseEntity<?> fetchUserByEmail(@RequestParam("email") String email) {
        User user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        System.out.println("User Request: " + user);
        if(user.getFirstName() != null && user.getFirstName().length() < 3) {
            return new ResponseEntity<>("Firstname must be greater than 3 characters", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new RecordNotFoundException("User with id " + id + " not found");
        }
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new RecordNotFoundException("User with id " + id + " not found");
        }
        userRepository.delete(userOptional.get());
        return new ResponseEntity<>("User with id " + id + " deleted", HttpStatus.OK);
    }

}
