package com.example.zvotespringboot.Services;

import com.example.zvotespringboot.Models.PollModel;
import com.example.zvotespringboot.Repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    // Add a new poll (throws if title exists)
    public PollModel addPoll(PollModel poll) {
        if (pollRepository.existsByTitle(poll.getTitle())) {
            throw new IllegalArgumentException("Poll title already exists. Please choose another title.");
        }

        // Set default values for votes and abstentions
        poll.setNbOfVotes(0);
        poll.setNbOfAbstentions(0);

        try {
            return pollRepository.save(poll);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error creating poll", e);
        }
    }

    // Get all polls
    public List<PollModel> getAllPolls() {
        return pollRepository.findAll();
    }

    // Get poll by poll id
    public PollModel getPollByPoll_ID(int poll_ID) {
        return pollRepository.findById(poll_ID).orElse(null);
    }

    // Update a poll
    public PollModel updatePoll(PollModel updatedPoll) {
        Optional<PollModel> optionalPoll = pollRepository.findById(updatedPoll.getPoll_ID());
        if (optionalPoll.isPresent()) {
            PollModel existingPoll = optionalPoll.get();
            existingPoll.setTitle(updatedPoll.getTitle());
            existingPoll.setDescription(updatedPoll.getDescription());
            existingPoll.setStart_date(updatedPoll.getStart_date());
            existingPoll.setEnd_date(updatedPoll.getEnd_date());
            existingPoll.setNbOfVotes(updatedPoll.getNbOfVotes());
            existingPoll.setNbOfAbstentions(updatedPoll.getNbOfAbstentions());
            return pollRepository.save(existingPoll);
        } else {
            throw new IllegalArgumentException("Poll not found.");
        }
    }

    // Delete a poll
    public void deletePoll(int poll_ID) {
        if (!pollRepository.existsById(poll_ID)) {
            throw new IllegalArgumentException("No poll found with this ID.");
        }
        pollRepository.deleteById(poll_ID);
    }
}
