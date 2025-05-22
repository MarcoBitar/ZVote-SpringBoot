package com.example.zvotespringboot.Models;

import java.io.Serializable;
import java.util.Objects;

public class VoteID implements Serializable {
    private int user_ID;
    private int poll_ID;

    public VoteID() {}

    public VoteID(int user_ID, int poll_ID) {
        this.user_ID = user_ID;
        this.poll_ID = poll_ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteID)) return false;
        VoteID that = (VoteID) o;
        return user_ID == that.user_ID && poll_ID == that.poll_ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_ID, poll_ID);
    }
}
