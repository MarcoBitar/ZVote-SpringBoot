package com.example.zvotespringboot.Models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "result")
public class ResultModel {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int result_ID;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date registration_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date withdrawal_date;

    private int votes_casted;

    @ManyToOne
    @JoinColumn(name = "poll_ID", nullable = false)
    private PollModel poll;

    @ManyToOne
    @JoinColumn(name = "candidate_ID", nullable = false)
    private CandidateModel candidate;


    // Default constructor
    public ResultModel() {
        super();
    }


    // Constructor using setters
    public ResultModel(Date registration_date, int candidate_ID, int poll_ID) {
        setRegistration_date(registration_date);
        setWithdrawal_date(null);  // Default withdrawal date is set to null
        setCandidate_ID(candidate_ID);
        setPoll_ID(poll_ID);
    }


    // Getter for result_ID
    public int getResult_ID() {
        return result_ID;
    }


    // Setter for result_ID
    public void setResult_ID(int result_ID) {
        this.result_ID = result_ID;
    }


    // Getter for registration_date
    public Date getRegistration_date() {
        return registration_date;
    }


    // Setter for registration_date
    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }


    // Getter for votes_casted
    public int getVotes_casted() {
        return votes_casted;
    }


    // Setter for votes_casted
    public void setVotes_casted(int votes_casted) {
        this.votes_casted = votes_casted;
    }


    // Getter for withdrawal_date
    public Date getWithdrawal_date() {
        return withdrawal_date;
    }


    // Setter for withdrawal_date
    public void setWithdrawal_date(Date withdrawal_date) {
        this.withdrawal_date = withdrawal_date;
    }


    // Getter for candidate id
    public int getCandidate_ID() {
        return candidate != null ? candidate.getCandidate_ID() : 0;
    }


    // Setter for candidate id
    public void setCandidate_ID(int candidate_ID) {
        if (this.candidate == null) {
            this.candidate = new CandidateModel();
        }
        this.candidate.setCandidate_ID(candidate_ID);
    }


    // Getter for poll id
    public int getPoll_ID() {
        return poll != null ? poll.getPoll_ID() : 0;
    }


    // Setter for poll id
    public void setPoll_ID(int poll_ID) {
        if (this.poll == null) {
            this.poll = new PollModel();
        }
        this.poll.setPoll_ID(poll_ID);
    }


    // Setter for poll
    public void setPoll(PollModel poll) {
        this.poll = poll;
    }


    public void setCandidate(CandidateModel candidate) {
        this.candidate = candidate;
    }


    // toString method to provide a string representation of the ResultModel object
    @Override
    public String toString() {
        return "result_ID = " + result_ID +
                ", registration_date = " + registration_date +
                ", votes_casted = " + votes_casted +
                ", withdrawal_date = " + withdrawal_date +
                ", candidate_ID = " + (candidate != null ? candidate.getCandidate_ID() : "null") +
                ", poll_ID = " + (poll != null ? poll.getPoll_ID() : "null");
    }
}
