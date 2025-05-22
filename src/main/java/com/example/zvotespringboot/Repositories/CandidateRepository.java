package com.example.zvotespringboot.Repositories;

import com.example.zvotespringboot.Models.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateModel, Integer> {

    // Find a candidate by name (for duplicate check)
    Optional<CandidateModel> findByName(String name);

    // Check if name is already taken
    boolean existsByName(String name);

    // Find all candidates linked to a poll via the result table
    @Query("SELECT c FROM CandidateModel c JOIN ResultModel r ON c.candidate_ID = r.candidate_ID WHERE r.poll_ID = :poll_ID")
    List<CandidateModel> findByPollID(@Param("poll_ID") int poll_ID);
}
