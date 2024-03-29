package com.example.cs2340c_team41.models;

import java.util.Random;
/*
* Enemy factory class. This is the factory class, that we will use to create enemies.
* Possibly, make an interface factory for each enemy type.
 */
public class EnemyFactory {
    /*
    * Adds all variables for enemy factory
     */
    private Random random;

    private double treeX;
    private double treeY;
    private double brickX;
    private double brickY;
    private double sandX;
    private double sandY;
    private double swordX;
    private double swordY;
    private double treeEndingY;
    private double brickEndingY;
    private double sandEndingY;
    private double swordEndingY;
    private int damage;
    //Sets damage, and bounds for all the enemies
    public EnemyFactory(double xBounds, double yBounds, int difficulty) {
        this.random = new Random();
        if (difficulty == 0) {
            damage = 5;
        } else if (difficulty == 1) {
            damage = 10;
        } else if (difficulty == 2) {
            damage = 15;
        }

        // Assigning random positions for each enemy type
        treeX = 300 + (xBounds - 300) * random.nextDouble();
        treeY = 400 + (yBounds - 800) * random.nextDouble();
        treeEndingY = Math.min(treeY + (yBounds - 200) * random.nextDouble(), yBounds - 100);

        brickX = 300 + (xBounds - 300) * random.nextDouble();
        brickY = 400 + (yBounds - 800) * random.nextDouble();
        brickEndingY = Math.min(brickY + (yBounds - 200) * random.nextDouble(), yBounds - 100);

        sandX = 300 + (xBounds - 300) * random.nextDouble();
        sandY = 400 + (yBounds - 800) * random.nextDouble();
        sandEndingY = Math.min(sandY + (yBounds - 200) * random.nextDouble(), yBounds - 100);

        swordX = 300 + (xBounds - 300) * random.nextDouble();
        swordY = 400 + (yBounds - 800) * random.nextDouble();
        swordEndingY = Math.min(swordY + (yBounds - 200) * random.nextDouble(), yBounds - 100);
    }

    /**
     * Getter for enemy
     * @param enemyType is the type of enemy
     * @return an enemy
     */
    public Enemy getEnemy(String enemyType) {
        //If there is no enemy then it returns null
        if (enemyType == null) {
            return null;
        }
        //Returns the enemy based on enemy requested to create.
        if (enemyType.equalsIgnoreCase("TREE")) {
            return new TreeEnemy(treeX, treeY, treeEndingY, damage);
        } else if (enemyType.equalsIgnoreCase("BRICK")) {
            return new BrickEnemy(brickX, brickY, brickEndingY, damage);
        } else if (enemyType.equalsIgnoreCase("SAND")) {
            return new SandEnemy(sandX, sandY, sandEndingY, damage);
        } else if (enemyType.equalsIgnoreCase("SWORD")) {
            return new SwordEnemy(swordX, swordY, swordEndingY, damage);
        }
        return null;
    }
}

