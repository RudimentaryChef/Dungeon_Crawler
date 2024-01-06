package com.example.cs2340c_team41.models;

import java.util.Objects;
/*
* This is the comparable class known as Attempt
* which stores a name score and time
* which can be compared to other attempts.
 */

/**
 * Represents an attempt with a name, score, and time.
 * This class allows for comparing attempts based on their score.
 */
public class Attempt implements Comparable<Attempt> {
    private String name;
    private int score;
    private String time;

    /**
     * Constructs a new Attempt with the specified name, score, and time.
     *
     * @param name  the name of the attempt
     * @param score the score achieved in the attempt
     * @param time  the time of the attempt
     */
    public Attempt(String name, int score, String time) {
        this.name = name;
        this.score = score;
        this.time = time;
    }

    /**
     * Returns the name of this attempt.
     *
     * @return the name of the attempt
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the score of this attempt.
     *
     * @return the score of the attempt
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the time of this attempt.
     *
     * @return the time of the attempt
     */
    public String getTime() {
        return time;
    }

    /**
     * Compares this attempt with another attempt based on their scores.
     * Attempts are ordered in descending order of their scores.
     *
     * @param other the Attempt to be compared
     * @return a negative integer, zero, or a positive integer as this attempt
     *         is less than, equal to, or greater than the specified attempt
     */
    @Override
    public int compareTo(Attempt other) {
        // Compare attempts based on score (in descending order)
        if (this.score < other.score) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Here, the equality is based solely on the time of the attempt.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attempt attempt = (Attempt) o;
        return Objects.equals(time, attempt.time); // Only compare time for equality
    }
}