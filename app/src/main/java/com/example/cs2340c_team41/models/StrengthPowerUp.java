package com.example.cs2340c_team41.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.cs2340c_team41.viewmodels.PlayerViewModel;

import java.util.Random;

/**
 * The StrengthPowerUp class represents a power-up that increases a player's strength.
 * It is a decorator for the PowerUp class, adding additional functionality.
 */
public class StrengthPowerUp extends PowerUpDecorator {
    private Random random;
    private Integer sprite;
    private double x;
    private double y;
    private boolean exists;

    /**
     * Initializes an instance of StrengthPowerUp.
     * @param decoratedPowerUp initializes the powerup to update player stats
     * @param xBounds the x-range of the powerup
     * @param yBounds the y-range of the powerup
     * @param sprite the sprite that receives the powerup
     */
    public StrengthPowerUp(PowerUp decoratedPowerUp, double xBounds, double yBounds,
                           Integer sprite) {
        super(decoratedPowerUp);
        this.random = new Random();
        this.x = 200 + (xBounds - 200) * random.nextDouble();
        this.y = 200 + (yBounds - 200) * random.nextDouble();
        this.sprite = sprite;
        this.exists = true;
    }

    /**
     * Updates the player's attributes by increasing the strength.
     * Delegates to the decorated PowerUp to perform its own update logic.
     *
     * @param playerViewModel The PlayerViewModel object representing the player's state.
     */
    @Override
    public void updatePlayerAttributes(PlayerViewModel playerViewModel) {
        super.updatePlayerAttributes(playerViewModel);
        // Additional logic to increase player's strength
        playerViewModel.increaseStrength();
    }

    /**
     * Draws the power-up on the given canvas.
     *
     * @param context The context used to access resources.
     * @param canvas The canvas on which to draw the power-up.
     */
    public void draw(Context context, Canvas canvas) {
        Bitmap sprite = BitmapFactory.decodeResource(context.getResources(),
                this.sprite);
        int width = sprite.getWidth();
        int height = sprite.getHeight();
        int newWidth = 200;
        sprite = Bitmap.createScaledBitmap(
                sprite, newWidth, height * newWidth / width, false);
        canvas.drawBitmap(sprite, (float) x - (float) sprite.getWidth() / 2,
                (float) y - (float) sprite.getHeight() / 2, null);
    }

    /**
     * Determines if the power-up has made contact with the player.
     *
     * @param playerX The x-coordinate of the player.
     * @param playerY The y-coordinate of the player.
     * @return true if contact is made, false otherwise.
     */
    public boolean madeContact(double playerX, double playerY) {
        if ((playerX + 50 >= x - 50
                && playerX - 50 <= x + 50)  && (playerY + 50 >= y - 50
                && playerY - 50 <= y + 50)) {
            return true;
        }
        return false;
    };

    /**
     * Marks the power-up as disappeared, indicating it should no longer be drawn or active.
     */
    public void disappear() {
        this.exists = false;
    }

    /**
     * Checks if the power-up still exists.
     *
     * @return true if the power-up exists, false otherwise.
     */
    public boolean doesExist() {
        return exists;
    }
}
