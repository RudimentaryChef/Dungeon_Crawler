package com.example.cs2340c_team41.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.cs2340c_team41.viewmodels.PlayerViewModel;

import java.util.Random;

/**
 * The SwordPowerUp class is a decorator class adding additional functionality to the PowerUp class.
 * SwordPowerUp represents a PowerUp that increases a sword's effectiveness.
 */
public class SwordPowerUp extends PowerUpDecorator {
    private Random random;
    private Integer sprite;
    private double x;
    private double y;
    private boolean exists;

    /**
     * Initializes an instance of SwordPowerUp.
     * 
     * @param decoratedPowerUp initializes the PowerUp, updating player stats
     * @param xBounds the x-range in which the PowerUp takes place
     * @param yBounds the y-range in which the PowerUp takes place
     * @param sprite the sprite that receives the PowerUp
     */
    public SwordPowerUp(PowerUp decoratedPowerUp, double xBounds, double yBounds, Integer sprite) {
        super(decoratedPowerUp);
        this.random = new Random();
        this.x = 200 + (xBounds - 200) * random.nextDouble();
        this.y = 200 + (yBounds - 200) * random.nextDouble();
        this.sprite = sprite;
        this.exists = true;
    }
    //updates player attributes
    @Override
    public void updatePlayerAttributes(PlayerViewModel playerViewModel) {
        super.updatePlayerAttributes(playerViewModel);
        // Additional logic to increase player's strength
        playerViewModel.equipSword();
    }

    /**
     * Draws the power up on screen
     * @param context the context for the power-up
     * @param canvas the context for the canvas
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
     * Checks if the player is in the same location as the sword power up.
     *
     * @param playerX the x position of the player.
     * @param playerY the y position of the player.
     *
     * @return boolean of whether player made contact.
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
     * takes the power up off screen
     */
    public void disappear() {
        this.exists = false;
    }

    /**
     * checks if powerUP exists
     * @return whether or not powerUp exists
     */
    public boolean doesExist() {
        return exists;
    }
}

