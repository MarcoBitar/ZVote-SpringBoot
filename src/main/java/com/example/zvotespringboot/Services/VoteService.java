package com.example.zvotespringboot.Services;

import com.example.zvotespringboot.Models.VoteModel;
import com.example.zvotespringboot.Repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    // Add a vote if the user hasn't already voted in the poll
    public boolean addVote(VoteModel vote) {
        boolean hasVoted = voteRepository.existsByUser_IDAndPoll_ID(vote.getUser_ID(), vote.getPoll_ID());

        if (hasVoted) {
            return false;  // Prevent duplicate voting
        }

        try {
            // If abstaining, set candidate_ID to null
            if (vote.getCandidate_ID() == 0) {
                vote.setCandidate_ID(null);
            }
            voteRepository.save(vote);
            return true;
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all votes
    public List<VoteModel> getAllVotes() {
        return voteRepository.findAll();
    }

    // Get votes by poll ID
    public List<VoteModel> getVotesByPollID(int poll_ID) {
        return voteRepository.findByPoll_ID(poll_ID);
    }

    // Get votes by candidate ID
    public List<VoteModel> getVotesByCandidateID(int candidate_ID) {
        return voteRepository.findByCandidate_ID(candidate_ID);
    }

    // Delete vote by user ID and poll ID
    public boolean deleteVote(int user_ID, int poll_ID) {
        Optional<VoteModel> voteOptional = voteRepository.findByUser_IDAndPoll_ID(user_ID, poll_ID);
        if (voteOptional.isPresent()) {
            voteRepository.deleteByUser_IDAndPoll_ID(user_ID, poll_ID);
            return true;
        } else {
            return false;
        }
    }

    // Check if a user has voted in a specific poll
    public boolean hasUserVoted(int userID, int pollID) {
        return voteRepository.existsByUser_IDAndPoll_ID(userID, pollID);
    }
}
