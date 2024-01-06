package com.example.cs2340c_team41.models;


import com.example.cs2340c_team41.R;


public class TreeEnemy extends Enemy {
    private Integer sprite;
    private int speed;
    private double x;
    private double y;

    /**
     * contains properties of the tree
     * @param x is the x location
     * @param y is the y location
     * @param endingY ending y-loc
     * @param damage damage that the enemy can do
     */
    public TreeEnemy(double x, double y, double endingY, int damage) {
        super(R.drawable.enemy_1, 3, x, y, endingY, damage, 1.1);
    }


    // Getters
    public Integer getSprite() {
        return sprite;
    }

    /**
     * gets the speed of the tree
     * @return the speed of tree
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the sprite integer of the Tree Enemy.
     *
     * @param sprite the sprite integer of the Tree Enemy.
     */
    // Setters
    public void setSprite(Integer sprite) {
        this.sprite = sprite;
    }

    /**
     * Sets the speed int of the Tree Enemy.
     *
     * @param speed the speed int of the Tree Enemy.
     */
    //sets the speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
