package com.example.zvotespringboot.Models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = "polls")
public class PollModel {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int poll_ID;

    private String title;
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    private int nbOfVotes;
    private int nbOfAbstentions;

    private int admin_ID; // Foreign key to users table

    // Enum for poll status
    public enum Status {
        inactive, active, completed
    }

    // Default constructor
    public PollModel() {
        super();
    }


    // Constructor using setters
    public PollModel(String title, String description, Date start_date, Date end_date, int admin_ID) {
        setTitle(title);
        setDescription(description);
        setStart_date(start_date);
        setEnd_date(end_date);
        setStatus(Status.inactive); // Default status
        setNbOfVotes(0);
        setNbOfAbstentions(0);
        setAdmin_ID(admin_ID);
    }


    // Getter for poll id
    public int getPoll_ID() {
        return poll_ID;
    }


    // Setter for poll id
    public void setPoll_ID(int poll_ID) {
        this.poll_ID = poll_ID;
    }


    // Getter for title
    public String getTitle() {
        return title;
    }


    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }


    // Getter for description
    public String getDescription() {
        return description;
    }


    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }


    // Getter for start date
    public Date getStart_date() {
        return start_date;
    }


    // Setter for start date
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }


    // Getter for end date
    public Date getEnd_date() {
        return end_date;
    }


    // Setter for end date
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }


    // Getter for status
    public Status getStatus() {
        return status;
    }


    // Setter for status
    public void setStatus(Status status) {
        this.status = status;
    }


    // Getter for nb of votes
    public int getNbOfVotes() {
        return nbOfVotes;
    }


    // Setter for nb of votes
    public void setNbOfVotes(int nbOfVotes) {
        this.nbOfVotes = nbOfVotes;
    }


    // Getter for nb of abstentions
    public int getNbOfAbstentions() {
        return nbOfAbstentions;
    }


    // Setter for nb of abstentions
    public void setNbOfAbstentions(int nbOfAbstentions) {
        this.nbOfAbstentions = nbOfAbstentions;
    }


    // Getter for admin id
    public int getAdmin_ID() {
        return admin_ID;
    }


    // Setter for admin id
    public void setAdmin_ID(int admin_ID) {
        this.admin_ID = admin_ID;
    }


    // toString method to provide a string representation of the PollModel object
    @Override
    public String toString() {
        return "PollModel{" +
                "poll_ID=" + poll_ID +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", status=" + status +
                ", nbOfVotes=" + nbOfVotes +
                ", nbOfAbstentions=" + nbOfAbstentions +
                ", admin_ID=" + admin_ID +
                '}';
    }

}
