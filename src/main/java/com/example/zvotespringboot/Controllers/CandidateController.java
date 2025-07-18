package com.example.zvotespringboot.Controllers;

import com.example.zvotespringboot.Models.CandidateModel;
import com.example.zvotespringboot.Services.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zvote")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    // POST /zvote/candidates
    @PostMapping("/candidates")
    public ResponseEntity<?> addCandidate(@RequestBody CandidateModel candidate) {
        try {
            CandidateModel created = candidateService.addCandidate(candidate);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /zvote/candidates
    @GetMapping("/candidates")
    public ResponseEntity<List<CandidateModel>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    // GET /zvote/candidates/{id}
    @GetMapping("/candidates/{id}")
    public ResponseEntity<?> getCandidateByID(@PathVariable int id) {
        return candidateService.getCandidateByID(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("No candidate found with this ID"));
    }

    // GET /zvote/candidatesbypoll/{pollID}
    @GetMapping("/candidatesbypoll/{pollID}")
    public ResponseEntity<List<CandidateModel>> getCandidatesByPollID(@PathVariable int pollID) {
        return ResponseEntity.ok(candidateService.getCandidatesByPollID(pollID));
    }

    // PUT /zvote/candidates
    @PutMapping("/candidates")
    public ResponseEntity<?> updateCandidate(@RequestBody CandidateModel candidate) {
        try {
            CandidateModel updated = candidateService.updateCandidate(candidate);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /zvote/candidates/{candidateId}
    @DeleteMapping("/candidates/{candidateId}")
    public ResponseEntity<?> deleteCandidate(@PathVariable int candidateId) {
        try {
            candidateService.deleteCandidate(candidateId);
            return ResponseEntity.ok("Candidate deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /zvote/candidates/{name}
    @GetMapping("/candidates/check/{name}")
    public ResponseEntity<Boolean> isNameTaken(@PathVariable String name) {
        return ResponseEntity.ok(candidateService.isNameTaken(name));
    }
}
