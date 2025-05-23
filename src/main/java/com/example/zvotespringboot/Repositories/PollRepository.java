package com.example.zvotespringboot.Repositories;

import com.example.zvotespringboot.Models.PollModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<PollModel, Integer> {

    // Check if a poll with the given title already exists
    boolean existsByTitle(String title);
}
