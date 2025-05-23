package com.example.zvotespringboot.Controllers;

import com.example.zvotespringboot.Models.PollModel;
import com.example.zvotespringboot.Services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zvote")
public class PollController {

    @Autowired
    private PollService pollService;

    // POST /zvote/addpoll
    @PostMapping("/addpoll")
    public ResponseEntity<?> addPoll(@RequestBody PollModel poll) {
        try {
            PollModel createdPoll = pollService.addPoll(poll);
            return ResponseEntity.ok(createdPoll);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Server error: " + e.getMessage());
        }
    }

    // GET /zvote/getallpolls
    @GetMapping("/getallpolls")
    public ResponseEntity<List<PollModel>> getAllPolls() {
        return ResponseEntity.ok(pollService.getAllPolls());
    }

    // GET /zvote/getpoll/{title}
    @GetMapping("/getpoll/{title}")
    public ResponseEntity<?> getPollByTitle(@PathVariable String title) {
        PollModel poll = pollService.getPollByTitle(title);
        return poll != null ? ResponseEntity.ok(poll)
                : ResponseEntity.notFound().build();
    }

    // PUT /zvote/updatepoll
    @PutMapping("/updatepoll")
    public ResponseEntity<?> updatePoll(@RequestBody PollModel poll) {
        try {
            PollModel updatedPoll = pollService.updatePoll(poll);
            return ResponseEntity.ok(updatedPoll);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE /zvote/deletepoll/{poll_ID}
    @DeleteMapping("/deletepoll/{poll_ID}")
    public ResponseEntity<?> deletePoll(@PathVariable int poll_ID) {
        try {
            pollService.deletePoll(poll_ID);
            return ResponseEntity.ok("Poll deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
