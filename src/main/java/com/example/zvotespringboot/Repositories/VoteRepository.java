package com.example.zvotespringboot.Repositories;

import com.example.zvotespringboot.Models.VoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<VoteModel, Integer> {

    // Check if a user has already voted in a specific poll
    @Query("SELECT v FROM VoteModel v WHERE v.user.user_ID = :user_ID AND v.poll.poll_ID = :poll_ID")
    Optional<VoteModel> existsByUser_IDAndPoll_ID(int user_ID, int poll_ID);

    // Find all votes by poll ID
    @Query("SELECT v FROM VoteModel v WHERE v.poll.poll_ID = :poll_ID")
    List<VoteModel> findByPoll_ID(int poll_ID);

    // Find all votes by candidate ID
    @Query("SELECT v FROM VoteModel v WHERE v.candidate.candidate_ID = :candidate_ID")
    List<VoteModel> findByCandidate_ID(int candidate_ID);

    // Find a vote by user ID and poll ID (useful for deletion or verification)
    @Query("SELECT v FROM VoteModel v WHERE v.user.user_ID = :user_ID AND v.poll.poll_ID = :poll_ID")
    Optional<VoteModel> findByUser_IDAndPoll_ID(int user_ID, int poll_ID);

    // Delete a vote by user ID and poll ID
    @Modifying
    @Query("DELETE FROM VoteModel v WHERE v.user.user_ID = :user_ID AND v.poll.poll_ID = :poll_ID")
    void deleteByUser_IDAndPoll_ID(int user_ID, int poll_ID);
}
