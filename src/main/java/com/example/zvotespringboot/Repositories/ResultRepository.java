package com.example.zvotespringboot.Repositories;

import com.example.zvotespringboot.Models.CandidateModel;
import com.example.zvotespringboot.Models.ResultModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<ResultModel, Integer> {

    // Retrieves all result entries for a given poll ID
    @Query("SELECT r FROM ResultModel r WHERE r.poll.poll_ID = :poll_ID")
    List<ResultModel> findByPoll_ID(@Param("poll_ID") int poll_ID);

    // Retrieves all result entries for a given candidate ID
    @Query("SELECT r FROM ResultModel r WHERE r.candidate.candidate_ID = :candidate_ID")
    List<ResultModel> findByCandidate_ID(@Param("candidate_ID") int candidate_ID);

    // Retrieves a specific result entry by poll ID and candidate ID
    @Query("SELECT r FROM ResultModel r WHERE r.poll.poll_ID = :poll_ID AND r.candidate.candidate_ID = :candidate_ID")
    Optional<ResultModel> findByPoll_IDAndCandidate_ID(@Param("poll_ID") int poll_ID, @Param("candidate_ID") int candidate_ID);

    // Calculates the total number of votes casted for a specific poll
    @Query("SELECT SUM(r.votes_casted) FROM ResultModel r WHERE r.poll.poll_ID = :poll_ID")
    Integer getTotalVotesForPoll_ID(@Param("poll_ID") int poll_ID);

    // Retrieves all candidates who have results (i.e., received votes) in a specific poll
    @Query("SELECT c FROM CandidateModel c JOIN ResultModel r ON c.candidate_ID = r.candidate.candidate_ID " +
            "WHERE r.poll.poll_ID = :poll_ID")
    List<CandidateModel> getCandidatesWithVotesByPoll_ID(@Param("poll_ID") int poll_ID);

    // Retrieves candidate winner in a poll
    @Query("SELECT c FROM CandidateModel c JOIN ResultModel r ON c.candidate_ID = r.candidate.candidate_ID " +
            "WHERE r.poll.poll_ID = :poll_ID ORDER BY r.votes_casted DESC LIMIT 1")
    Optional<CandidateModel> getWinnerByPoll_ID(@Param("poll_ID") int poll_ID);
}
