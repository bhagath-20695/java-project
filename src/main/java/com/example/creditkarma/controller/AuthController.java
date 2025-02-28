package com.example.creditkarma.controller;

import com.example.creditkarma.model.User;
import com.example.creditkarma.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody User user) {
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));

        Map<String, String> response = new HashMap<>();

        if (existingUser.isPresent()) {
            response.put("message", "User already exists");
            return ResponseEntity.badRequest().body(response);
        }

        // ✅ Assign a unique `userId` when registering
        user.setUserId(UUID.randomUUID().toString().replace("-", "").substring(0, 8)); // Generates a short unique ID
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        response.put("message", "User registered successfully");
        response.put("userId", user.getUserId());  // ✅ Return `userId` in response
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        Map<String, String> response = new HashMap<>();

        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            response.put("message", "Login Successful");
            response.put("userId", existingUser.getUserId());  // ✅ Return user's `userId`
            return ResponseEntity.ok(response);
        }

        response.put("message", "Invalid Username or Password");
        return ResponseEntity.badRequest().body(response);
    }
}
