package com.example.cs2340c_team41;

import org.junit.Before;
import org.junit.Test;

import com.example.cs2340c_team41.models.Bounds;
import com.example.cs2340c_team41.models.Leaderboard;
import com.example.cs2340c_team41.models.Attempt;
import com.example.cs2340c_team41.models.Player;
import com.example.cs2340c_team41.viewmodels.PlayerViewModel;

import static org.junit.Assert.assertEquals;

/**
 * This class contains unit tests for verifying the functionality of the game's leaderboard,
 * player attempts, and player model behavior.
 */
public class UnitTests {

    private Leaderboard leaderboard;
    private Attempt attempt1;
    private Attempt attempt2;
    private Attempt attempt3;
    private Attempt attempt4;
    private Attempt attempt5;
    private Player player;
    private PlayerViewModel playerViewModel;

    /**
     * Sets up the test environment and initializes test objects before each test is run.
     */
    @Before
    public void setUp() {
        leaderboard = Leaderboard.getInstance();

        attempt1 = new Attempt("Player1", 100, "4:55PM");
        attempt2 = new Attempt("Player2", 200, "5:30PM");
        attempt3 = new Attempt("Player3", 150, "4:45PM");
        attempt4 = new Attempt("Player4", 300, "6:00PM");
        attempt5 = new Attempt("Player5", 250, "5:45PM");

        player = Player.getInstance("John", 100, 10.0, 20.0, 0, 1, 10);
        playerViewModel = new PlayerViewModel("John", 100, 200, 200, 100, "sprite_1", 10);
    }

    /**
     * Tests the addition of a new attempt to the leaderboard.
     */
    @Test
    public void testAddAttempt() {
        leaderboard.resetLeaderboard();
        leaderboard = Leaderboard.getInstance();
        leaderboard.addAttempt(attempt1);
        assertEquals(attempt1, leaderboard.getTopAttempts().get(0));

        leaderboard.addAttempt(attempt2);
        assertEquals(attempt2, leaderboard.getTopAttempts().get(0));
        assertEquals(attempt1, leaderboard.getTopAttempts().get(1));
    }

    /**
     * Tests retrieving the formatted leaderboard as a String.
     */
    @Test
    public void testGetLeaderboard() {
        leaderboard.resetLeaderboard();
        leaderboard = Leaderboard.getInstance();
        leaderboard.addAttempt(attempt1);
        leaderboard.addAttempt(attempt2);
        leaderboard.addAttempt(attempt3);

        String expectedLeaderboard = "Player2   200   5:30PM\n"
                + "Player3   150   4:45PM\n"
                + "Player1   100   4:55PM\n";

        assertEquals(expectedLeaderboard, leaderboard.getLeaderboard());
    }

    /**
     * Tests retrieving the latest attempt as a formatted string.
     */
    @Test
    public void testGetLatestAttempt() {
        leaderboard.resetLeaderboard();
        leaderboard = Leaderboard.getInstance();
        leaderboard.addAttempt(attempt1);
        leaderboard.addAttempt(attempt2);

        String expectedLatestAttempt = "Player2   200   5:30PM\n";
        assertEquals(expectedLatestAttempt, leaderboard.getLatestAttempt());
    }

    /**
     * Tests retrieving the name of the player.
     */
    @Test
    public void testGetName() {
        assertEquals("John", player.getName());
    }

    /**
     * Tests retrieving the y value of the player.
     */
    @Test
    public void testGetY() {
        assertEquals(20.0, player.getY(), 0.001);
    }

    /**
     * Tests retrieving the score of the player.
     */
    @Test
    public void testGetScore() {
        assertEquals(0, player.getScore());
    }

    /**
     * Tests retrieving the sprite of the player.
     */
    @Test
    public void testGetPlayerSprite() {
        assertEquals(Integer.valueOf(1), player.getPlayerSprite());
    }

    /**
     * Tests setting the player's name.
     */
    @Test
    public void testSetName() {
        player.setName("Alice");
        assertEquals("Alice", player.getName());
    }

    /**
     * Tests setting the player's health.
     */
    @Test
    public void testSetHealth() {
        player.setHealth(80);
        assertEquals(80, player.getHealth());
    }

    /**
     * Tests setting the player's X location.
     */
    @Test
    public void testSetXLoc() {
        player.setXLoc(15.0);
        assertEquals(15.0, player.getX(), 0.001);
    }

    /**
     * Tests setting the player's Y location.
     */
    @Test
    public void testSetYLoc() {
        player.setYLoc(25.0);
        assertEquals(25.0, player.getY(), 0.001);
    }

    /**
     * Tests setting the player's score.
     */
    @Test
    public void testSetScore() {
        player.setScore(50);
        assertEquals(50, player.getScore());
    }

    /**
     * Tests setting the player's sprite.
     */
    @Test
    public void testSetPlayerSprite() {
        player.setPlayerSprite(2);
        assertEquals(Integer.valueOf(2), player.getPlayerSprite());
    }

    /**
     * Tests positioning the player at specific X and Y coordinates.
     */
    @Test
    public void testPositionPlayer() {
        // Test the positionPlayer method
        playerViewModel.positionPlayer(50.0, 60.0);
        assertEquals(50.0, playerViewModel.getX(), 0.0);
        assertEquals(60.0, playerViewModel.getY(), 0.0);
    }

    /**
     * Tests moving the player upwards by reducing the Y coordinate.
     */
    @Test
    public void testMoveUp() {
        // Test the moveUp method
        double initialY = playerViewModel.getY();
        playerViewModel.moveUp();
        assertEquals(initialY - 10, playerViewModel.getY(), 0.0);
    }

    /**
     * Tests moving the player downwards by increasing the Y coordinate.
     */
    @Test
    public void testMoveDown() {
        // Test the moveDown method
        double initialY = playerViewModel.getY();
        playerViewModel.moveDown();
        assertEquals(initialY + 10, playerViewModel.getY(), 0.0);
    }

    /**
     * Tests moving the player to the right by increasing the X coordinate.
     */
    @Test
    public void testMoveRight() {
        // Test the moveRight method
        double initialX = playerViewModel.getX();
        playerViewModel.moveRight();
        assertEquals(initialX + 10, playerViewModel.getX(), 0.0);
    }

    /**
     * Tests moving the player to the left by decreasing the X coordinate.
     */
    @Test
    public void testMoveLeft() {
        // Test the moveLeft method
        double initialX = playerViewModel.getX();
        playerViewModel.moveLeft();
        assertEquals(initialX - 10, playerViewModel.getX(), 0.0);
    }

    /**
     * Tests checking the player's position when at the left edge of the bounds.
     */
    @Test
    public void testCheckBoundsLeftEdge() {
        // Test the checkBounds method when the player is at the left edge
        int i = 0;
        while (i < 100) {
            playerViewModel.moveLeft();  // Force the player to be at the left edge
            i++;
        }
        Bounds status = playerViewModel.checkBounds(200, 200);
        assertEquals(Bounds.LEFT_EDGE, status);
    }

    /**
     * Tests the behavior when a player enters from the right side of the screen.
     */
    @Test
    public void testEnterRight() {
        // Test the enterRight method
        double newX = 1200.0;
        playerViewModel.enterRight((float) newX);
        assertEquals(newX, playerViewModel.getX(), 0.0);
    }

    /**
     * Tests the behavior when a player enters from the left side of the screen.
     */
    @Test
    public void testEnterLeft() {
        // Test the enterLeft method
        playerViewModel.enterLeft();
        assertEquals(100.0, playerViewModel.getX(), 0.0);
    }
}
