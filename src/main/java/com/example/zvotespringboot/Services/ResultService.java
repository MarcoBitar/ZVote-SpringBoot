package com.example.zvotespringboot.Services;

import com.example.zvotespringboot.Models.CandidateModel;
import com.example.zvotespringboot.Models.PollModel;
import com.example.zvotespringboot.Models.ResultModel;
import com.example.zvotespringboot.Repositories.ResultRepository;
import com.example.zvotespringboot.Repositories.PollRepository;
import com.example.zvotespringboot.Repositories.CandidateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public ResultModel addResult(ResultModel result) {
        if (result.getRegistration_date() == null) {
            result.setRegistration_date(new java.sql.Date(System.currentTimeMillis()));
        }

        int pollId = result.getPoll_ID();
        int candidateId = result.getCandidate_ID();

        PollModel poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found with ID: " + pollId));
        CandidateModel candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + candidateId));

        result.setPoll(poll);
        result.setCandidate(candidate);

        return resultRepository.save(result);
    }

    // Read All
    public List<ResultModel> getAllResults() {
        return resultRepository.findAll();
    }

    // Read by ID
    public Optional<ResultModel> getResultByID(int result_ID) {
        return resultRepository.findById(result_ID);
    }

    // Read by Poll + Candidate ID
    public Optional<ResultModel> getResultByPollAndCandidateID(int poll_ID, int candidate_ID) {
        return resultRepository.findByPoll_IDAndCandidate_ID(poll_ID, candidate_ID);
    }

    // Read all by Poll ID
    public List<ResultModel> getResultsByPollID(int poll_ID) {
        return resultRepository.findByPoll_ID(poll_ID);
    }

    // Read all by Candidate ID
    public List<ResultModel> getResultsByCandidateID(int candidate_ID) {
        return resultRepository.findByCandidate_ID(candidate_ID);
    }

    // Update
    public ResultModel updateResult(ResultModel updatedResult) {
        Optional<ResultModel> optionalResult = resultRepository.findById(updatedResult.getResult_ID());
        if (optionalResult.isPresent()) {
            ResultModel existing = optionalResult.get();
            existing.setRegistration_date(updatedResult.getRegistration_date());
            existing.setVotes_casted(updatedResult.getVotes_casted());
            existing.setWithdrawal_date(updatedResult.getWithdrawal_date());
            return resultRepository.save(existing);
        } else {
            throw new RuntimeException("Result not found with ID: " + updatedResult.getResult_ID());
        }
    }

    // Delete
    public void deleteResult(int result_ID) {
        if (!resultRepository.existsById(result_ID)) {
            throw new RuntimeException("No result found with ID: " + result_ID);
        }
        resultRepository.deleteById(result_ID);
    }

    // Get total votes for a poll
    public int getTotalVotesForPoll(int poll_ID) {
        Integer totalVotes = resultRepository.getTotalVotesForPoll_ID(poll_ID);
        return totalVotes != null ? totalVotes : 0;
    }

    // Calculate percentage
    public static double getVotePercentage(int candidateVotes, int totalVotes) {
        if (totalVotes == 0) return 0.0;
        return (double) candidateVotes / totalVotes;
    }

    // Get candidates with vote count and percentage
    public List<CandidateModel> getCandidatesWithVotesByPollID(int poll_ID) {
        int totalVotes = getTotalVotesForPoll(poll_ID);
        List<CandidateModel> candidates = resultRepository.getCandidatesWithVotesByPoll_ID(poll_ID);

        for (CandidateModel candidate : candidates) {
            Optional<ResultModel> result = resultRepository.findByPoll_IDAndCandidate_ID(poll_ID, candidate.getCandidate_ID());
            result.ifPresent(r -> {
                candidate.setVoteCount(r.getVotes_casted());
                candidate.setVotePercentage(getVotePercentage(r.getVotes_casted(), totalVotes));
            });
        }

        return candidates;
    }

    // Get poll winner
    public CandidateModel getWinnerByPollID(int poll_ID) {
        return resultRepository.getWinnerByPoll_ID(poll_ID).orElse(null);
    }
}