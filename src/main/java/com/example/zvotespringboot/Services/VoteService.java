package com.example.zvotespringboot.Services;

import com.example.zvotespringboot.Models.VoteModel;
import com.example.zvotespringboot.Repositories.CandidateRepository;
import com.example.zvotespringboot.Repositories.PollRepository;
import com.example.zvotespringboot.Repositories.UserRepository;
import com.example.zvotespringboot.Repositories.VoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public boolean addVote(VoteModel vote) {
        boolean hasVoted = voteRepository.existsByUser_IDAndPoll_ID(
                vote.getUser().getUser_ID(),
                vote.getPoll().getPoll_ID()
        ).isPresent();

        if (hasVoted) {
            return false;  // Prevent duplicate voting
        }

        try {
            // Handle abstention vote
            if (vote.getCandidate() != null && vote.getCandidate().getCandidate_ID() == 0) {
                vote.setCandidate(null);
                vote.setBlank(true);
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
    @Transactional
    public boolean deleteVote(int user_ID, int poll_ID) {
        Optional<VoteModel> voteOptional = voteRepository.findByUser_IDAndPoll_ID(user_ID, poll_ID);
        if (voteOptional.isPresent()) {
            voteRepository.deleteByUser_IDAndPoll_ID(user_ID, poll_ID);
            return true;
        } else {
            return false;
        }
    }

    // Delete vote poll ID
    @Transactional
    public boolean deleteVotesByPoll(int poll_ID) {
        List<VoteModel> votes = voteRepository.findByPoll_ID(poll_ID);
        if (!votes.isEmpty()) {
            voteRepository.deleteAll(votes);
            return true;
        } else {
            return false;
        }
    }

    // Delete vote candidate ID
    @Transactional
    public boolean deleteVotesByCandidate(int candidate_ID) {
        List<VoteModel> votes = voteRepository.findByCandidate_ID(candidate_ID);
        if (!votes.isEmpty()) {
            voteRepository.deleteAll(votes);
            return true;
        } else {
            return false;
        }
    }

    // Check if a user has voted in a specific poll
    public boolean hasUserVoted(int userID, int pollID) {
        Optional<VoteModel> result = voteRepository.existsByUser_IDAndPoll_ID(userID, pollID);
        return result.isPresent();
    }
}
