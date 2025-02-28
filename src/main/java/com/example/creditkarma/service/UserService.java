package com.example.creditkarma.service;

import com.example.creditkarma.model.User;
import com.example.creditkarma.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
