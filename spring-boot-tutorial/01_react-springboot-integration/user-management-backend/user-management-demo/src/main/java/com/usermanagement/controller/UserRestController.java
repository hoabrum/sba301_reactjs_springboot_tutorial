package com.usermanagement.controller;

import com.usermanagement.entity.User;
import com.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> fetchAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstName("John");
        user1.setLastName("Smith");
        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Jane");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("User Request: " + user);
        return ResponseEntity.ok(userRepository.save(user));
    }

}
