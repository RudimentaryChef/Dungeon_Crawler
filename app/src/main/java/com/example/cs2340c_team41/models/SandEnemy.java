package com.example.cs2340c_team41.models;

import com.example.cs2340c_team41.R;

/**
 * The SandEnemy class represents an enemy character in the game with specific attributes
 * like speed and a sprite image. It extends the Enemy class.
 */
public class SandEnemy extends Enemy {
    private Integer sprite;
    private int speed;
    private double x;
    private double y;

    /**
     * Constructs a new SandEnemy with specified coordinates, ending Y position, and damage.
     *
     * @param x The initial X position of the SandEnemy.
     * @param y The initial Y position of the SandEnemy.
     * @param endingY The Y position where the SandEnemy is headed.
     * @param damage The amount of damage this SandEnemy can inflict.
     */
    public SandEnemy(double x, double y, double endingY, int damage) {
        super(R.drawable.enemy_4, 5, x, y, endingY, damage, 1.6);
    }

    // Getters

    /**
     * Returns the sprite ID associated with this SandEnemy.
     *
     * @return An Integer representing the resource ID of the sprite.
     */
    public Integer getSprite() {
        return sprite;
    }

    /**
     * Returns the speed of this SandEnemy.
     *
     * @return The speed as an integer.
     */
    public int getSpeed() {
        return speed;
    }

    // Setters

    /**
     * Sets the sprite for this SandEnemy.
     *
     * @param sprite An Integer representing the resource ID of the new sprite.
     */
    public void setSprite(Integer sprite) {
        this.sprite = sprite;
    }

    /**
     * Sets the speed for this SandEnemy.
     *
     * @param speed The new speed value as an integer.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
