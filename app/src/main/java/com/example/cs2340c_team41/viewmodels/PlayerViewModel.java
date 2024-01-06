package com.example.cs2340c_team41.viewmodels;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.cs2340c_team41.models.Bounds;
import com.example.cs2340c_team41.R;
import com.example.cs2340c_team41.models.Player;
import androidx.lifecycle.ViewModel;

import java.util.HashMap;

public class PlayerViewModel extends ViewModel {
    private Player player;
    private boolean weaponEquipped;

    public PlayerViewModel(String playerName, int playerHealth, double xPosition, double yPosition,
                           int score, String sprite, int speed) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("sprite_1", R.drawable.sprite_1);
        map.put("sprite_2", R.drawable.sprite_2);
        map.put("sprite_3", R.drawable.sprite_3);
        this.player = Player.getInstance(playerName, playerHealth, xPosition, yPosition, score,
                map.get(sprite), speed);
        this.weaponEquipped = false;
    }


    public void draw(Context context, Canvas canvas) {
        Bitmap sprite = BitmapFactory.decodeResource(context.getResources(),
                player.getPlayerSprite());
        int width = sprite.getWidth();
        int height = sprite.getHeight();
        int newWidth = 200;
        sprite = Bitmap.createScaledBitmap(
                sprite, newWidth, height * newWidth / width, false);
        canvas.drawBitmap(sprite, (float) player.getX() - (float) sprite.getWidth() / 2,
                (float) player.getY() - (float) sprite.getHeight() / 2, null);
    }

    /**
     * sets up the sprite
     * @param playerSprite is the player sprite
     */
    public void setSprite(String playerSprite) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("sprite_1", R.drawable.sprite_1);
        map.put("sprite_2", R.drawable.sprite_2);
        map.put("sprite_3", R.drawable.sprite_3);
        this.player.setPlayerSprite(map.get(playerSprite));
    }

    /**
     * positions the player
     * @param xPosition x axis position of player
     * @param yPosition y axis position of player
     */
    public void positionPlayer(double xPosition, double yPosition) {
        this.player.setXLoc(xPosition);
        this.player.setYLoc(yPosition);
    }

    public void pushBackLeft() {
        for (int i = 0; i < 15; i++) {
            moveLeft();
        }
    }

    public void moveUp() {
        player.setYLoc(player.getY() - player.getSpeed());
    }

    public void moveDown() {
        player.setYLoc(player.getY() + player.getSpeed());
    }

    public void moveLeft() {
        player.setXLoc(player.getX() - player.getSpeed());
    }

    public void moveRight() {
        player.setXLoc(player.getX() + player.getSpeed());
    }

    /**
     * Checks if the player is within bounds and handles if it is out of bounds.
     *
     * @param x the x bounds
     * @param y the y bounds
     * @return the Bounds representing the side of the screen the player is in
     */
    public Bounds checkBounds(float x, float y) {
        if (player.getX() < 100) {
            moveRight();
            return Bounds.LEFT_EDGE;
        }
        if (player.getY() < 100) {
            moveDown();
        }
        if (player.getX() > x) {
            moveLeft();
            return Bounds.RIGHT_EDGE;
        }
        if (player.getY() > y) {
            moveUp();
        }
        return Bounds.INSIDE;
    }

    public void enterLeft() {
        player.setXLoc(100);
    }

    public void enterRight(float x) {
        player.setXLoc(x);
    }

    public double getX() {
        return player.getX();
    }

    public double getY() {
        return player.getY();
    }

    public Integer getSprite() {
        return player.getPlayerSprite();
    }

    public void updateHealth(int hp) {
        player.setHealth(player.getHealth() + hp);
    }

    public int getHealth() {
        return player.getHealth();
    }

    public void increaseSpeed() {
        // logic to increase speed
        player.setSpeed(player.getSpeed() + 10);
    }

    public void increaseHealth() {
        // logic to increase health
        player.setHealth(player.getHealth() + 10);
    }

    public void increaseStrength() {
        // logic to increase strength
        player.setStrength(player.getStrength() + 10);
    }

    /**
     * Updates the sprite depending on if a sword is equipped to the player.
     */
    public void equipSword() {
        // logic to equip sword
        if (player.getPlayerSprite() == R.drawable.sprite_1) {
            player.setPlayerSprite(R.drawable.sword_sprite_1);
        } else if (player.getPlayerSprite() == R.drawable.sprite_2) {
            player.setPlayerSprite(R.drawable.sword_sprite_2);
        } else if (player.getPlayerSprite() == R.drawable.sprite_3) {
            player.setPlayerSprite(R.drawable.sword_sprite_3);
        }
        this.weaponEquipped = true;
    }

    /**
     * Returns the status of the sword being equipped.
     *
     * @return boolean representing if the sword has been equipped.
     */
    public boolean isWeaponEquipped() {
        return this.weaponEquipped;
    }

}
