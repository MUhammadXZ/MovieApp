package com.example.movieapp.service;

import com.example.movieapp.dto.UserDto;
import com.example.movieapp.model.User;
import com.example.movieapp.repository.UserRepository;
import com.example.movieapp.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UserDto userDto) {
        // Check if the username already exists
        Optional<User> existingUser = userRepository.findByUsername(userDto.getUsername());
        if (existingUser.isPresent()) {
            throw new InvalidInputException("Username already exists");
        }

        // Create and save the new user
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Set default roles, you can modify this as per your application logic
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        if (userDto.getRoles() != null) {
            roles.addAll(userDto.getRoles());
        }
        newUser.setRoles(roles);

        userRepository.save(newUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidInputException("User not found"));
    }
}
