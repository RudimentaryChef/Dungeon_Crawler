package com.example.cs2340c_team41.models;

import com.example.cs2340c_team41.viewmodels.PlayerViewModel;

/**
 * A class that represents an implementation of PowerUp that has no special attributes.
 */
public class BasicPowerUp implements PowerUp {

    /**
     * Adds 5 hp to the player.
     *
     * @param playerViewModel the playerViewModel which stores the data and hp of the player.
     */
    @Override
    public void updatePlayerAttributes(PlayerViewModel playerViewModel) {
        // Basic power-up logic (if any)
        playerViewModel.updateHealth(5);
    }
}
