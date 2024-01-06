package com.example.cs2340c_team41.models;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
/*
*   This is the enemy superclass,
*   Enemies have sprites, speed, damage, multipliers, and locations (both starting and current)
*   They also have a direction to move in
*
 */
public class Enemy {
    private Integer sprite;
    private int speed;
    private int damage;
    private double damageMultiplier;
    private double x;
    private double startingY;
    private double endingY;
    private double y;
    private Direction direction;
    private boolean exists;

    //Initializes an Enemy
    public Enemy(Integer sprite, int speed, double x, double y, double endingY, int damage,
                 double damageMultiplier) {
        this.sprite = sprite;
        this.speed = speed;
        this.x = x;
        this.startingY = y;
        this.endingY = endingY;
        this.damage = damage;
        this.damageMultiplier = damageMultiplier;
        this.y = y;
        direction = Direction.DOWN;
        this.exists = true;
    }
    //notifies if contact happens. Observer method.
    public boolean notify(double playerX, double playerY, Context context, Integer sprite) {
        if (!this.exists) {
            return false;
        }
        Bitmap enemySprite = BitmapFactory.decodeResource(context.getResources(),
                this.sprite);
        Bitmap playerSprite = BitmapFactory.decodeResource(context.getResources(),
                sprite);
        if ((playerX + 50 >= x - 50
                && playerX - 50 <= x + 50)  && (playerY + 50 >= y - 50
                && playerY - 50 <= y + 50)) {
            return true;
        }
        return false;
    };
    public double attack() {
        if (!this.exists) {
            return 0.0;
        }
        return damage * damageMultiplier;
    }
    //draw's the sprite onto screen
    public void draw(Context context, Canvas canvas) {
        if (!this.exists) {
            return;
        }
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
    //moves the sprite automatically and checks bounds
    public void move() {
        checkBounds();
        if (direction == Direction.DOWN) {
            y += speed;
        } else if (direction == Direction.UP) {
            y -= speed;
        }
    }

    /**
     * Makes sure that the Enemy is within the bounds of the screen.
     *
     */
    public void checkBounds() {
        if (y > endingY) {
            y -= speed;
            direction = Direction.UP;
        } else if (y < startingY) {
            y += speed;
            direction = Direction.DOWN;
        }
    }

    /**
     * Retrieves the sprite of the Enemy.
     *
     * @return the sprite integer of the Enemy.
     */
    // Getter for sprite
    public Integer getSprite() {
        return sprite;
    }

    /**
     * Sets the sprite integer of the Enemy.
     *
     * @param sprite the sprite integer of the Enemy.
     */
    // Setter for sprite
    public void setSprite(Integer sprite) {
        this.sprite = sprite;
    }

    /**
     * Retrieves the speed of the Enemy.
     *
     * @return the speed int of the Enemy.
     */
    // Getter for speed
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed int of the Enemy.
     *
     * @param speed the speed int of the Enemy.
     */
    // Setter for speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Retrieves the double x location of the Enemy.
     *
     * @return x the location double of the Enemy.
     */
    // Getter for x
    public double getX() {
        return x;
    }

    /**
     * Sets the double x location of the Enemy.
     *
     * @param x the double x location of the Enemy.
     */
    // Setter for x
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the double y location of the Enemy.
     *
     * @return y the double y location of the Enemy.
     */
    // Getter for y
    public double getY() {
        return y;
    }

    /**
     * Sets the double y location of the Enemy.
     *
     * @param y the double y location of the Enemy.
     */
    // Setter for y
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Causes the enemy to disappear.
     */
    public void disappear() {
        this.exists = false;
    }
}
