package com.example.zvotespringboot.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.Arrays;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_ID;

    private String username;

    @Email
    private String user_email;

    private String user_pass;

    @Lob
    @Column(name = "user_photoID", columnDefinition = "MEDIUMBLOB")
    private byte[] user_photoID;

    private String phoneNb;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Enum for user role
    public enum Role {
        voter, admin
    }
    public UserModel() {
        super();
    }

    // Constructor to initialize a UserModel instance
    public UserModel(String username, String user_email, String user_pass, byte[] user_photoID, String phoneNb) {
        setUsername(username);
        setUser_email(user_email);
        setUser_pass(user_pass);
        setUser_photoID(user_photoID);
        setPhoneNb(phoneNb);
        setRole(Role.voter);  // Default role is set to "voter"
    }


    // Getter for user_ID
    public int getUser_ID() {
        return user_ID;
    }


    // Setter for user_ID
    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }


    // Getter for username
    public String getUsername() {
        return username;
    }


    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }


    // Getter for user_email
    public String getUser_email() {
        return user_email;
    }


    // Setter for user_email
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }


    // Getter for user_pass
    public String getUser_pass() {
        return user_pass;
    }


    // Setter for user_pass (applies password hashing)
    public void setUser_pass(String user_pass) {
        if (user_pass != null && !user_pass.matches("^[a-fA-F0-9]{64}$")) {
            this.user_pass = hashPassword(user_pass);
        } else {
            this.user_pass = user_pass;
        }
    }


    // Getter for user_photoID (returns user photo as a byte array)
    public byte[] getUser_photoID() {
        return user_photoID;
    }


    // Setter for user_photoID (assigns default photo if none is provided)
    public void setUser_photoID(byte[] user_photoID) {
        if (user_photoID != null) {
            this.user_photoID = user_photoID;
        } else {
            try {
                // Use a default photo path and convert it to a byte array
                Path defaultPhotoPath = Path.of("\uD83D\uDC64");  // Unicode character path placeholder
                this.user_photoID = Files.readAllBytes(defaultPhotoPath);
            } catch (IOException e) {
                e.printStackTrace();
                this.user_photoID = new byte[0];  // Fallback to an empty byte array on error
            }
        }
    }


    // Getter for phoneNb
    public String getPhoneNb() {
        return phoneNb;
    }


    // Setter for phoneNb
    public void setPhoneNb(String phoneNb) {
        this.phoneNb = phoneNb;
    }


    // Getter for role
    public Role getRole() {
        return role;
    }


    // Setter for role
    public void setRole(Role role) {
        this.role = role;
    }


    // Static method to hash a password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");  // Create MessageDigest for SHA-256
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convert the hashed bytes to a simple hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();  // Return the hashed password as a hex string
        } catch (Exception e) {
            return null;  // Return null in case of error
        }
    }

    // toString method to provide a string representation of the UserModel object
    @Override
    public String toString() {
        return "user_ID = " + user_ID +
                ", username = " + username +
                ", user_email = " + user_email +
                ", user_pass = " + user_pass +
                ", user_photoID = " + Arrays.toString(user_photoID) +
                ", phoneNb = " + phoneNb +
                ", role = " + role;
    }
}
