package com.example.zvotespringboot.Services;

import com.example.zvotespringboot.Models.CandidateModel;
import com.example.zvotespringboot.Repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    // Add a new candidate
    public CandidateModel addCandidate(CandidateModel candidate) {
        if (candidateRepository.existsByName(candidate.getName())) {
            throw new RuntimeException("A candidate with the same name already exists.");
        }
        return candidateRepository.save(candidate);
    }

    // Get all candidates
    public List<CandidateModel> getAllCandidates() {
        return candidateRepository.findAll();
    }

    // Get candidate by ID
    public Optional<CandidateModel> getCandidateByID(int candidate_ID) {
        return candidateRepository.findById(candidate_ID);
    }

    // Get candidates by Poll ID
    public List<CandidateModel> getCandidatesByPollID(int poll_ID) {
        return candidateRepository.findByPollID(poll_ID);
    }

    // Update candidate details
    public CandidateModel updateCandidate(CandidateModel candidate) {
        Optional<CandidateModel> optionalCandidate = candidateRepository.findById(candidate.getCandidate_ID());
        if (optionalCandidate.isPresent()) {
            CandidateModel existing = optionalCandidate.get();
            existing.setName(candidate.getName());
            existing.setPhoto(candidate.getPhoto());
            existing.setBio(candidate.getBio());
            return candidateRepository.save(existing);
        } else {
            throw new RuntimeException("Candidate not found with ID: " + candidate.getCandidate_ID());
        }
    }

    // Delete a candidate by ID
    public void deleteCandidate(int candidate_ID) {
        if (!candidateRepository.existsById(candidate_ID)) {
            throw new RuntimeException("No candidate found with this ID.");
        }
        candidateRepository.deleteById(candidate_ID);
    }

    // Check if name is already taken
    public boolean isNameTaken(String name) {
        return candidateRepository.existsByName(name);
    }
}
