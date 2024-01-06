package com.example.cs2340c_team41.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import java.util.Random;

import com.example.cs2340c_team41.viewmodels.PlayerViewModel;

/**
 * SpeedPowerUp is a decorator for PowerUp that enhances the speed attribute of a player.
 * It is positioned randomly within specified bounds and represented by a sprite.
 */
public class SpeedPowerUp extends PowerUpDecorator {
    private Random random;
    private Integer sprite;
    private double x;
    private double y;
    private boolean exists;

    /**
     * Constructs a SpeedPowerUp object with specified bounds and sprite.
     *
     * @param decoratedPowerUp The base PowerUp to be decorated.
     * @param xBounds          The horizontal boundary for the power-up's random position.
     * @param yBounds          The vertical boundary for the power-up's random position.
     * @param sprite           The resource ID of the sprite representing the power-up.
     */
    public SpeedPowerUp(PowerUp decoratedPowerUp, double xBounds, double yBounds, Integer sprite) {
        super(decoratedPowerUp);
        this.random = new Random();
        this.x = 200 + (xBounds - 200) * random.nextDouble();
        this.y = 200 + (yBounds - 200) * random.nextDouble();
        this.sprite = sprite;
        this.exists = true;
    }

    /**
     * Updates the player's attributes by increasing speed, in addition to the base power-up's
     * effects.
     *
     * @param playerViewModel The player's ViewModel to be updated.
     */
    @Override
    public void updatePlayerAttributes(PlayerViewModel playerViewModel) {
        super.updatePlayerAttributes(playerViewModel);
        // Additional logic to increase player's speed
        playerViewModel.increaseSpeed();
    }

    /**
     * Draws the power-up on the canvas using its sprite.
     *
     * @param context The context used to access resources.
     * @param canvas  The canvas on which to draw the power-up.
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
     * @param playerX The player's horizontal position.
     * @param playerY The player's vertical position.
     * @return true if the player is in contact with the power-up, false otherwise.
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
     * Makes the power-up disappear.
     */
    public void disappear() {
        this.exists = false;
    }

    /**
     * Checks if the power-up currently exists in the game.
     *
     * @return true if the power-up exists, false otherwise.
     */
    public boolean doesExist() {
        return exists;
    }
}
