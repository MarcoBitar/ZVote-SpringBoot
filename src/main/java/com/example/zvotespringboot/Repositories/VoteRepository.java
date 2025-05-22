package com.example.zvotespringboot.Repositories;

import com.example.zvotespringboot.Models.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteModel, Integer> {

    // Check if a user has already voted in a specific poll
    boolean existsByUserIDAndPollID(int userID, int pollID);

    // Find all votes by poll ID
    List<VoteModel> findByPollID(int pollID);

    // Find all votes by candidate ID
    List<VoteModel> findByCandidateID(int candidateID);

    // Find a vote by user ID and poll ID (useful for deletion or verification)
    Optional<VoteModel> findByUserIDAndPollID(int userID, int pollID);

    // Delete a vote by user ID and poll ID
    void deleteByUserIDAndPollID(int userID, int pollID);
}
