package com.example.zvotespringboot.Controllers;

import com.example.zvotespringboot.Models.UserModel;
import com.example.zvotespringboot.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zvote")
public class UserController {

    @Autowired
    private UserService userService;

    // POST /zvote/users
    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody UserModel user) {
        try {
            UserModel createdUser = userService.addUser(user);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Server error: " + e.getMessage());
        }
    }

    // GET /zvote/users
    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /zvote/users/{username}
    @GetMapping("/users/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        UserModel user = userService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with username '" + username + "' not found.");
    }


    // GET /zvote/users/role/{username}
    @GetMapping("/users/role/{username}")
    public ResponseEntity<?> getUserByRole(@PathVariable String username) {
        UserModel user = userService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user.getRole())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with username '" + username + "' not found.");
    }


    // PUT /zvote/users
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@RequestBody UserModel updatedUser) {
        try {
            UserModel user = userService.updateUser(updatedUser);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /zvote/users/{username}
    @DeleteMapping("/users/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok("User deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // POST /zvote/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.checkLogin(username, password);
        return success ? ResponseEntity.ok("Login successful")
                : ResponseEntity.status(401).body("Invalid credentials");
    }
}
