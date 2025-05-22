package com.example.zvotespringboot.Repositories;

import com.example.zvotespringboot.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    // Check if username already exists
    boolean existsByUsername(String username);

    // Find user by username
    Optional<UserModel> findByUsername(String username);

    // Delete user by username
    void deleteByUsername(String username);
}
