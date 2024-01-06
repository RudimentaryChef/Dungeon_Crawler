package com.example.cs2340c_team41.models;

/**
 * Represents the player instance using a singleton format.
 */
public class Player {
    /*
     * All variables for Player
     */
    private String name;
    private int health;
    private double xPosition;
    private double yPosition;
    private int score;
    private Integer playerSprite;
    private static volatile Player instance;
    private int speed;
    private int strength;

    /**
     * Player constructor with various fields about the state of the Player.
     *
     * @param name the name of the Player
     * @param health the health of the player
     * @param xPosition the location (on the x-axis) of the player
     * @param yPosition the location (on the y-axis) of the player
     * @param score the score of the player
     * @param playerSprite an Integer corresponding to an image of the player sprite
     * @param speed speed of the player
     */
    private Player(String name, int health, double xPosition, double yPosition, int score, Integer
            playerSprite, int speed) {
        //default constructor
        this.name = name;
        this.health = health;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.score = score;
        this.playerSprite = playerSprite;
        this.speed = speed;
        this.strength = speed;
    }

    /**
     * Singleton getInstance class to get the one Player instance, instantiating the player if
     * the instance is null.
     *
     * @param name name of the the Player
     * @param health health of the Player
     * @param xPosition the location (on the x-axis) of the player
     * @param yPosition the location (on the y-axis) of the player
     * @param score the score of the player
     * @param playerSprite an Integer corresponding to the image for the player sprite
     * @param speed speed of the player
     * @return instance of the player
     */
    public static Player getInstance(String name, int health, double xPosition, double yPosition,
                                     int score, Integer playerSprite, int speed) {
        //Makes player a Singleton
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) {
                    instance = new Player(name, health, xPosition, yPosition, score,
                            playerSprite, speed);
                }
            }
        }
        return instance;
    }

    /**
     * Retrieves the name of the Player.
     *
     * @return the name of the Player instance.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the current health of the Player.
     *
     * @return the health of the singleton Player instance
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Retrieves the x-axis of the Player.
     *
     * @return the location on the x-axis of the singleton player instance.
     */
    public double getX() {
        return this.xPosition;
    }

    /**
     * Retrieves the y-axis location of the Player.
     *
     * @return the location on the y-axis of the singleton player instance.
     */
    public double getY() {
        return this.yPosition;
    }

    /**
     * Retrieves the score of the Player.
     *
     * @return the score of the singleton player instance.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Retrieves the player sprite of the Player.
     *
     * @return the Integer representation of the singleton player sprite.
     */
    public Integer getPlayerSprite() {
        return this.playerSprite;
    }
    public int getSpeed() {
        return this.speed;
    }
    public int getStrength() {
        return this.strength;
    };

    /**
     * Sets the name field of the Player.
     *
     * @param name the name the singleton is updated to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the health of the player.
     *
     * @param health the health the singleton is updated to
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Sets the x-location of the Player.
     *
     * @param x the x-axis coordinate the singleton is updated to
     */
    public void setXLoc(double x) {
        this.xPosition = x;
    }

    /**
     * Sets the y-location of the Player.
     *
     * @param y the y-axis coordinate the singleton is updated to
     */
    public void setYLoc(double y) {
        this.yPosition = y;
    }

    /**
     * Sets the score of the Player.
     *
     * @param score the score the singleton is updated to
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the playerSprite of the Player.
     *
     * @param playerSprite the Integer representation of the image the sprite is updated to.
     */
    public void setPlayerSprite(Integer playerSprite) {
        this.playerSprite = playerSprite;
    }

    /**
     * Sets the strength of the Player.
     *
     * @param strength the Integer value of the strength of the player.
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
