package com.example.zvotespringboot.Models;

import java.io.Serializable;
import java.util.Objects;

public class VoteID implements Serializable {

    // Composite key fields (must match the @Id fields in VoteModel)
    private int user;
    private int poll;


    // Default no-argument constructor (required by JPA)
    public VoteID() {}


    // Constructor with parameters to initialize composite key fields.
    public VoteID(int user_ID, int poll_ID) {
        this.user = user_ID;
        this.poll = poll_ID;
    }

    // Overrides equals method to compare VoteID objects by user and poll.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteID)) return false;
        VoteID that = (VoteID) o;
        return user == that.user && poll == that.poll;
    }


    // Overrides hashCode to ensure proper hashing behavior for composite key.
    @Override
    public int hashCode() {
        return Objects.hash(user, poll);
    }
}
