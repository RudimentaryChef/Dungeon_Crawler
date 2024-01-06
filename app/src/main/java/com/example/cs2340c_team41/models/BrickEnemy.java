package com.example.cs2340c_team41.models;

import com.example.cs2340c_team41.R;
/**
 * The Brick Enemy class represents the Brick Emeny
 * that extends from the Enemy class.
 * It has a sprite, speed, and location.
 */
public class BrickEnemy extends Enemy {
    /*
     * Variables for BrickEnemy
     */
    private Integer sprite;
    private int speed;
    private double x;
    private double y;

    /**
     * Initializes a BrickEnemy object.
     *
     * @param x the current x-location of the BrickEnemy
     * @param y the current y-location of the BrickEnemy
     * @param endingY the ending y-location of the BrickEnemy
     * @param damage the damage that the BrickEnemy does
     */
    public BrickEnemy(double x, double y, double endingY, int damage) {
        //Sets default speed to 7 an creates a Brick Enemy with damage, and location.
        super(R.drawable.enemy_3, 7, x, y, endingY, damage, 1.3);
    }


    /**
     * Returns the integer Sprite of the BrickEnemy.
     *
     * @return the Integer representing the sprite of the BrickEnemy object.
     */
    public Integer getSprite() {
        return sprite;
    }

    /**
     * Returns the speed of the BrickEnemy.
     *
     * @return the int speed of the BrickEnemy object.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the Sprite integer of the BrickEnemy.
     *
     * @param sprite the sprite integer of the BrickEnemy.
     */
    public void setSprite(Integer sprite) {
        this.sprite = sprite;
    }

    /**
     * Sets the sprite speed of the BrickEnemy.
     *
     * @param speed the speed int of the BrickEnemy.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}