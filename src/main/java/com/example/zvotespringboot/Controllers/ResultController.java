package com.example.zvotespringboot.Controllers;

import com.example.zvotespringboot.Models.CandidateModel;
import com.example.zvotespringboot.Models.ResultModel;
import com.example.zvotespringboot.Services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/zvote")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // POST /zvote/addresult
    @PostMapping("/addresult")
    public ResponseEntity<?> addResult(@RequestBody ResultModel result) {
        try {
            ResultModel created = resultService.addResult(result);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /zvote/getallresults
    @GetMapping("/getallresults")
    public ResponseEntity<List<ResultModel>> getAllResults() {
        return ResponseEntity.ok(resultService.getAllResults());
    }

    // GET /zvote/getresult/{id}
    @GetMapping("/getresult/{id}")
    public ResponseEntity<?> getResultByID(@PathVariable int id) {
        Optional<ResultModel> result = resultService.getResultByID(id);
        return result.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No Result Found"));
    }

    // GET /zvote/getresultbypollcandidate/{pollID}/{candidateID}
    @GetMapping("/getresultbypollcandidate/{pollID}/{candidateID}")
    public ResponseEntity<?> getResultByPollAndCandidate(@PathVariable int pollID, @PathVariable int candidateID) {
        Optional<ResultModel> result = resultService.getResultByPollAndCandidateID(pollID, candidateID);
        return result.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No Result Found"));
    }

    // GET /zvote/getresultsbypoll/{pollID}
    @GetMapping("/getresultsbypoll/{pollID}")
    public ResponseEntity<List<ResultModel>> getResultsByPollID(@PathVariable int pollID) {
        return ResponseEntity.ok(resultService.getResultsByPollID(pollID));
    }

    // GET /zvote/getresultsbycandidate/{candidateID}
    @GetMapping("/getresultsbycandidate/{candidateID}")
    public ResponseEntity<List<ResultModel>> getResultsByCandidateID(@PathVariable int candidateID) {
        return ResponseEntity.ok(resultService.getResultsByCandidateID(candidateID));
    }

    // PUT /zvote/updateresult
    @PutMapping("/updateresult")
    public ResponseEntity<?> updateResult(@RequestBody ResultModel result) {
        try {
            ResultModel updated = resultService.updateResult(result);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /zvote/deleteresult/{id}
    @DeleteMapping("/deleteresult/{id}")
    public ResponseEntity<?> deleteResult(@PathVariable int id) {
        try {
            resultService.deleteResult(id);
            return ResponseEntity.ok("Result deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET /zvote/totalvotes/{pollID}
    @GetMapping("/totalvotes/{pollID}")
    public ResponseEntity<Integer> getTotalVotesForPoll(@PathVariable int pollID) {
        return ResponseEntity.ok(resultService.getTotalVotesForPoll(pollID));
    }

    // GET /zvote/votepercentage/{candidatevotes}/{totalvotes}
    @GetMapping("/votepercentage/{candidatevotes}/{totalvotes}")
    public ResponseEntity<Double> getVotePercentage(@PathVariable int candidatevotes, @PathVariable int totalvotes) {
        return ResponseEntity.ok(getVotePercentage(candidatevotes, totalvotes).getBody());
    }

    // GET /zvote/candidateswithvotes/{pollID}
    @GetMapping("/candidateswithvotes/{pollID}")
    public ResponseEntity<List<CandidateModel>> getCandidatesWithVotesByPollID(@PathVariable int pollID) {
        return ResponseEntity.ok(resultService.getCandidatesWithVotesByPollID(pollID));
    }

    // GET /zvote/winner/{pollID}
    @GetMapping("/winner/{pollID}")
    public ResponseEntity<?> getWinnerByPollID(@PathVariable int pollID) {
        CandidateModel winner = resultService.getWinnerByPollID(pollID);
        return winner != null ? ResponseEntity.ok(winner) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Result not found.");
    }
}
