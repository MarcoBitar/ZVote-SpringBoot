package com.example.zvotespringboot.Services;

import com.example.zvotespringboot.Models.UserModel;
import com.example.zvotespringboot.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.zvotespringboot.Models.UserModel.hashPassword;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Add new user (throws if username exists)
    public UserModel addUser(UserModel user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    // Get all users
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by username
    public UserModel getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    // Update user
    public UserModel updateUser(UserModel updatedUser) {
        Optional<UserModel> optionalUser = userRepository.findByUsername(updatedUser.getUsername());
        if (optionalUser.isPresent()) {
            UserModel existingUser = optionalUser.get();
            existingUser.setUser_email(updatedUser.getUser_email());
            existingUser.setUser_pass(updatedUser.getUser_pass());
            existingUser.setUser_photoID(updatedUser.getUser_photoID());
            existingUser.setPhoneNb(updatedUser.getPhoneNb());
            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("User not found.");
        }
    }

    // Delete user
    @Transactional
    public void deleteUser(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("No user found with the specified username.");
        }
        userRepository.deleteByUsername(username);
    }

    // Login check
    public boolean checkLogin(String username, String password) {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            String storedHash = optionalUser.get().getUser_pass();
            return storedHash.equals(hashPassword(password));
        }
        return false;
    }

}
