package com.example.zvotespringboot.Controllers;

import com.example.zvotespringboot.Models.VoteModel;
import com.example.zvotespringboot.Services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zvote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // POST /zvote/addvote
    @PostMapping("/addvote")
    public ResponseEntity<?> addVote(@RequestBody VoteModel vote) {
        boolean success = voteService.addVote(vote);
        if (success) {
            return ResponseEntity.ok("Vote added successfully");
        } else {
            return ResponseEntity.badRequest().body("User has already voted or error occurred.");
        }
    }

    // GET /zvote/getallvotes
    @GetMapping("/getallvotes")
    public ResponseEntity<List<VoteModel>> getAllVotes() {
        return ResponseEntity.ok(voteService.getAllVotes());
    }

    // GET /zvote/getvotesbypoll/{pollID}
    @GetMapping("/getvotesbypoll/{pollID}")
    public ResponseEntity<List<VoteModel>> getVotesByPollID(@PathVariable int pollID) {
        return ResponseEntity.ok(voteService.getVotesByPollID(pollID));
    }

    // GET /zvote/getvotesbycandidate/{candidateID}
    @GetMapping("/getvotesbycandidate/{candidateID}")
    public ResponseEntity<List<VoteModel>> getVotesByCandidateID(@PathVariable int candidateID) {
        return ResponseEntity.ok(voteService.getVotesByCandidateID(candidateID));
    }

    // DELETE /zvote/deletevote/{userID}/{pollID}
    @DeleteMapping("/deletevote/{userID}/{pollID}")
    public ResponseEntity<?> deleteVote(@PathVariable int userID, @PathVariable int pollID) {
        boolean deleted = voteService.deleteVote(userID, pollID);
        if (deleted) {
            return ResponseEntity.ok("Vote deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Vote not found.");
        }
    }

    // DELETE /zvote/deletevotesbypoll/{pollID}
    @DeleteMapping("/deletevotesbypoll/{pollID}")
    public ResponseEntity<?> deleteVotesByPoll(@PathVariable int pollID) {
        boolean deleted = voteService.deleteVotesByPoll(pollID);
        if (deleted) {
            return ResponseEntity.ok("Vote deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Vote not found.");
        }
    }

    // DELETE /zvote/deletevotesbycandidate/{candidateID}
    @DeleteMapping("/deletevotesbycandidate/{candidateID}")
    public ResponseEntity<?> deleteVotesByCandidate(@PathVariable int candidateID) {
        boolean deleted = voteService.deleteVotesByCandidate(candidateID);
        if (deleted) {
            return ResponseEntity.ok("Vote deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Vote not found.");
        }
    }

    // GET /zvote/hasuservoted/{userID}/{pollID}
    @GetMapping("/hasuservoted/{userID}/{pollID}")
    public ResponseEntity<Boolean> hasUserVoted(@PathVariable int userID, @PathVariable int pollID) {
        return ResponseEntity.ok(voteService.hasUserVoted(userID, pollID));
    }
}
