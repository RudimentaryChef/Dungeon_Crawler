package com.example.cs2340c_team41.models;

import com.example.cs2340c_team41.viewmodels.PlayerViewModel;

public class PowerUpDecorator implements PowerUp {
    protected PowerUp decoratedPowerUp;

    /**
     * Constructs a new PowerUpDecorator with a PowerUp.
     *
     * @param decoratedPowerUp The PowerUp that will be decorated.
     */
    public PowerUpDecorator(PowerUp decoratedPowerUp) {
        this.decoratedPowerUp = decoratedPowerUp;
    }

    @Override
    /**
     * updates the player attributes
     */
    public void updatePlayerAttributes(PlayerViewModel playerViewModel) {
        this.decoratedPowerUp.updatePlayerAttributes(playerViewModel);
    }
}