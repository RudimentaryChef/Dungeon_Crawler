package com.example.cs2340c_team41.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Represents a leaderboard for tracking attempts.
 * This class is a singleton, ensuring only one instance of it exists.
 */
public class Leaderboard {
    //Private Variables for Leaderboard
    private static Leaderboard leaderboard;
    private List<Attempt> topAttempts;
    private Attempt latestAttempt;

    /**
     * Private constructor for the singleton pattern.
     * Initializes the list of top attempts.
     */
    private Leaderboard() {
        topAttempts = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of the Leaderboard.
     * If it doesn't exist, it's created.
     *
     * @return the singleton Leaderboard instance
     */
    public static Leaderboard getInstance() {
        if (leaderboard == null) {
            leaderboard = new Leaderboard();
        }
        return leaderboard;
    }

    /**
     * Returns the current top attempts.
     *
     * @return a list of top attempts
     */
    public List<Attempt> getTopAttempts() {
        return topAttempts;
    }

    /**
     * Adds a new attempt to the leaderboard.
     * The leaderboard is sorted in descending order based on score.
     * Only the top 5 attempts are kept.
     *
     * @param newAttempt the new attempt to add
     */
    public void addAttempt(Attempt newAttempt) {
        if (!topAttempts.contains(newAttempt)) {
            this.latestAttempt = newAttempt;
            topAttempts.add(newAttempt);
            Collections.sort(topAttempts, Collections.reverseOrder());
            // Sort in descending order based on score
            // Keep only the top 5 attempts
            if (topAttempts.size() > 5) {
                topAttempts.subList(5, topAttempts.size()).clear();
            }
        }
    }

    /**
     * Generates a string representation of the leaderboard.
     *
     * @return a string representing the current state of the leaderboard
     */
    public String getLeaderboard() {
        StringBuilder leaderboardText = new StringBuilder();
        /*
         * Makes a list of attempts called top attempts
         */
        List<Attempt> topAttempts = Leaderboard.getInstance().getTopAttempts();
        //Formats leaderboards
        for (Attempt attempt : topAttempts) {
            leaderboardText.append(attempt.getName()).append("   ")
                    .append(attempt.getScore()).append("   ")
                    .append(attempt.getTime()).append("\n");
        }

        return leaderboardText.toString();
    }

    /**
     * Retrieves a string representation of the latest attempt.
     *
     * @return a string detailing the latest attempt
     */
    public String getLatestAttempt() {
        StringBuilder res = new StringBuilder();
        res.append(this.latestAttempt.getName()).append("   ")
                .append(this.latestAttempt.getScore()).append("   ")
                .append(this.latestAttempt.getTime()).append("\n");
        return res.toString();
    }

    /**
     * Resets the singleton leaderboard instance.
     * This method is used to clear the leaderboard and start afresh.
     */
    public void resetLeaderboard() {
        leaderboard = null;
    }
}
