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
    List<ResultModel> findByPollID(int poll_ID);

    // Retrieves all result entries for a given candidate ID
    List<ResultModel> findByCandidateID(int candidate_ID);

    // Retrieves a specific result entry by poll ID and candidate ID
    Optional<ResultModel> findByPollIDAndCandidateID(int poll_ID, int candidate_ID);

    // Calculates the total number of votes casted for a specific poll
    @Query("SELECT SUM(r.votes_casted) FROM ResultModel r WHERE r.poll_ID = :poll_ID")
    Integer getTotalVotesForPoll(@Param("poll_ID") int poll_ID);

    // Retrieves all candidates who have results (i.e., received votes) in a specific poll
    @Query("SELECT c FROM CandidateModel c JOIN ResultModel r ON c.candidate_ID = r.candidate_ID " +
            "WHERE r.poll_ID = :poll_ID")
    List<CandidateModel> getCandidatesWithVotesByPollID(@Param("poll_ID") int poll_ID);

    // Retrieves candidate winner in a poll
    @Query("SELECT c FROM CandidateModel c JOIN ResultModel r ON c.candidate_ID = r.candidate_ID " +
            "WHERE r.poll_ID = :poll_ID ORDER BY r.votes_casted DESC")
    Optional<CandidateModel> getWinnerByPollID(@Param("poll_ID") int poll_ID);
}
