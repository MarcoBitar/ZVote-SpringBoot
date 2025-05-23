package com.example.zvotespringboot.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
@IdClass(VoteID.class)
public class VoteModel {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_ID")
    private UserModel user;

    @Id
    @ManyToOne
    @JoinColumn(name = "poll_ID")
    private PollModel poll;

    private LocalDateTime timestamp;

    @Column(name = "blank", nullable = false)
    private boolean blank;

    @ManyToOne
    @JoinColumn(name = "candidate_ID")
    private CandidateModel candidate;


    public VoteModel() {
        super();
    }


    // Constructor to initialize a VoteModel instance
    public VoteModel(int user_ID, int poll_ID, boolean blank, int candidate_ID) {
        setUser_ID(user_ID);
        setPoll_ID(poll_ID);
        setBlank(blank);
        setCandidate_ID(candidate_ID);
    }


    // Getter for user_ID
    public int getUser_ID() {
        return user != null ? user.getUser_ID() : 0;
    }


    // Setter for user_ID
    public void setUser_ID(int user_ID) {
        if (this.user == null) {
            this.user = new UserModel();
        }
        this.user.setUser_ID(user_ID);
    }


    // Getter for poll_ID
    public int getPoll_ID() {
        return poll != null ? poll.getPoll_ID() : 0;
    }


    // Setter for poll_ID
    public void setPoll_ID(int poll_ID) {
        if (this.poll == null) {
            this.poll = new PollModel();
        }
        this.poll.setPoll_ID(poll_ID);
    }


    // Getter for timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    // Setter for timestamp
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    // Getter for blank (represents abstention or blank vote flag)
    public boolean getBlank() {
        return blank;
    }


    // Setter for blank
    public void setBlank(boolean blank) {
        this.blank = blank;
    }


    // Getter for candidate_ID
    public int getCandidate_ID() {
        return candidate != null ? candidate.getCandidate_ID() : 0;
    }


    // Setter for candidate_ID
    public void setCandidate_ID(Integer candidate_ID) {
        if (this.candidate == null) {
            this.candidate = new CandidateModel();
        }
        this.candidate.setCandidate_ID(candidate_ID);
    }


    // toString method to provide a string representation of the VoteModel object
    @Override
    public String toString() {
        return "user_ID = " + user.getUser_ID() +
                ", poll_ID = " + poll.getPoll_ID() +
                ", timestamp = " + timestamp +
                ", blank = " + blank +
                ", candidate_ID = " + candidate.getCandidate_ID();
    }
}
