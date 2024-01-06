package com.example.cs2340c_team41.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.cs2340c_team41.viewmodels.PlayerViewModel;

import java.util.Random;

public class HealthPowerUp extends PowerUpDecorator {
    /*
     * PowerUp that uses the decorator pattern
     */
    // Class Variables
    private Random random;
    private Integer sprite;
    private double x;
    private double y;
    private boolean exists;

    /**
     * Constructs a HealthPowerUp object.
     *
     * @param decoratedPowerUp The PowerUp object being decorated with health attributes.
     * @param xBounds The x-coordinate bounds within which the power-up can be placed.
     * @param yBounds The y-coordinate bounds within which the power-up can be placed.
     * @param sprite The resource ID of the sprite representing the power-up.
     */
    public HealthPowerUp(PowerUp decoratedPowerUp, double xBounds, double yBounds, Integer sprite) {
        super(decoratedPowerUp);
        this.random = new Random();
        this.x = 200 + (xBounds - 200) * random.nextDouble();
        this.y = 200 + (yBounds - 200) * random.nextDouble();
        this.sprite = sprite;
        exists = true;
    }

    /**
     * Updates the player's attributes when they acquire this power-up.
     *
     * @param playerViewModel The view model representing the player's state.
     */
    @Override
    public void updatePlayerAttributes(PlayerViewModel playerViewModel) {
        super.updatePlayerAttributes(playerViewModel);
        // Additional logic to increase player's health
        playerViewModel.increaseHealth();
    }

    /**
     * Draws the power-up on the provided canvas.
     *
     * @param context The context used to access resources.
     * @param canvas The canvas on which the power-up is to be drawn.
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
     * Checks if the player has made contact with the power-up.
     *
     * @param playerX The x-coordinate of the player.
     * @param playerY The y-coordinate of the player.
     * @return true if there is contact, otherwise false.
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
     * Causes the power-up to disappear.
     */
    public void disappear() {
        this.exists = false;
    }

    /**
     * Checks if the power-up still exists in the game world.
     *
     * @return true if the power-up exists, otherwise false.
     */
    public boolean doesExist() {
        return exists;
    }
}
