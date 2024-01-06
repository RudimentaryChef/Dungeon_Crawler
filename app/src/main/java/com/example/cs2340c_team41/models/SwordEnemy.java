
package com.example.cs2340c_team41.models;

import com.example.cs2340c_team41.R;

/**
 * Represents an enemy character wielding a sword in a game.
 * This class extends the basic functionality of an Enemy,
 * including additional properties such as sprite and speed.
 */
public class SwordEnemy extends Enemy {
    private Integer sprite;
    private int speed;
    private double x;
    private double y;

    /**
     * initializes enemy
     * @param x position of enemy
     * @param y position of enemy
     * @param endingY ending of y loc
     * @param damage that the enemy deals
     */
    public SwordEnemy(double x, double y, double endingY, int damage) {
        super(R.drawable.enemy_2, 10, x, y, endingY, damage, 1.2);
    }

    /**
     * Returns the sprite of this enemy.
     *
     * @return The sprite as an Integer.
     */
    public Integer getSprite() {
        return sprite;
    }

    /**
     * Returns the speed of this enemy.
     *
     * @return The speed as an int.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the sprite for this enemy.
     *
     * @param sprite The sprite as an Integer.
     */
    public void setSprite(Integer sprite) {
        this.sprite = sprite;
    }

    /**
     * Sets the speed for this enemy.
     *
     * @param speed The speed as an int.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
