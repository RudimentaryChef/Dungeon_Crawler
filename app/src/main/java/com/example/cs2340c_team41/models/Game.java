package com.example.cs2340c_team41.models;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.cs2340c_team41.R;
import com.example.cs2340c_team41.viewmodels.PlayerViewModel;
import com.example.cs2340c_team41.views.EndScreenActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that contains the controls, display, and high-level logic of the dungeon crawler game.
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private int tileNumber = 0;
    private Integer tileset;
    private GameButton upButton;
    private Direction direction = Direction.NO_DIRECTION;
    private GameButton downButton;
    private GameButton rightButton;
    private GameButton leftButton;
    private GameLoop gameLoop;
    private Context context;
    private PlayerViewModel playerViewModel;
    private String name;
    private int score;
    private Leaderboard leaderboard;
    private EnemyFactory factory;
    private int difficulty;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private HealthPowerUp powerUp1;
    private SpeedPowerUp powerUp2;
    private StrengthPowerUp powerUp3;
    private SwordPowerUp powerUp4;
    private int gainedScore;

    public Game(Context context, String sprite, String name, int hp, int difficulty) {
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        this.difficulty = difficulty;
        gameLoop = new GameLoop(this, surfaceHolder);
        this.name = name;

        this.leaderboard = Leaderboard.getInstance();

        this.playerViewModel =
                new PlayerViewModel(name, hp,
                        (float) getWidth(), (float) getHeight(),
                        100, sprite, 10);

        playerViewModel.setSprite(sprite);
        int buttonWidth;
        int buttonHeight;
        int newWidth = 200;
        this.gainedScore = 0;

        Bitmap upButton = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.up_arrow);
        buttonWidth = upButton.getWidth();
        buttonHeight = upButton.getHeight();
        this.upButton = new GameButton(Bitmap.createScaledBitmap(
                upButton, newWidth, buttonWidth * newWidth / buttonHeight, false));

        Bitmap rightButton = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.right_arrow);
        buttonWidth = rightButton.getWidth();
        buttonHeight = rightButton.getHeight();
        this.rightButton = new GameButton(Bitmap.createScaledBitmap(
                rightButton, newWidth, buttonWidth * newWidth / buttonHeight, false));

        Bitmap downButton = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.down_arrow);
        buttonWidth = downButton.getWidth();
        buttonHeight = downButton.getHeight();
        this.downButton = new GameButton(Bitmap.createScaledBitmap(
                downButton, newWidth, buttonWidth * newWidth / buttonHeight, false));

        Bitmap leftButton = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.left_arrow);
        buttonWidth = leftButton.getWidth();
        buttonHeight = leftButton.getHeight();
        this.leftButton = new GameButton(Bitmap.createScaledBitmap(
                leftButton, newWidth, buttonWidth * newWidth / buttonHeight, false));
        this.tileset = R.drawable.tile1;
        setFocusable(true);
    }

    /**
     * Updates the button variables based on a motion event.
     *
     * @param event The motion event.
     * @return boolean representing success of motion event.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // map keypad functionality
            if (isButtonClicked(upButton, event)) {
                direction = Direction.UP;
                return true;
            }
            if (isButtonClicked(downButton, event)) {
                direction = Direction.DOWN;
                return true;
            }
            if (isButtonClicked(leftButton, event)) {
                direction = Direction.LEFT;
                return true;
            }
            if (isButtonClicked(rightButton, event)) {
                direction = Direction.RIGHT;
                return true;
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            direction = Direction.NO_DIRECTION;
        }

        return super.onTouchEvent(event);
    }

    /**
     * Initializes a surface with enemies, powerups, and a gameloop.
     *
     * @param holder The SurfaceHolder whose surface is being created.
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop.startLoop();
        playerViewModel.positionPlayer((float) 100,
                (float) getHeight() / 2);
        factory = new EnemyFactory(getWidth(), getHeight(), difficulty);
        enemy1 = factory.getEnemy("BRICK");
        enemy2 = factory.getEnemy("SWORD");
        enemy3 = factory.getEnemy("SAND");
        enemy4 = factory.getEnemy("TREE");
        powerUp1 = new HealthPowerUp(new BasicPowerUp(), getWidth(), getHeight(),
                R.drawable.health_powerup);
        powerUp2 = new SpeedPowerUp(new BasicPowerUp(), getWidth(), getHeight(),
                R.drawable.speed_powerup);
        powerUp3 = new StrengthPowerUp(new BasicPowerUp(), getWidth(), getHeight(),
                R.drawable.strength_powerup);
        powerUp4 = new SwordPowerUp(new BasicPowerUp(), getWidth(), getHeight(),
                R.drawable.weapon);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawBackground(canvas);
        drawScore(canvas);
        drawHP(canvas);
        playerViewModel.draw(this.context, canvas);
        enemy1.draw(this.context, canvas);
        enemy2.draw(this.context, canvas);
        if (powerUp1.doesExist()) {
            powerUp1.draw(this.context, canvas);
        }
        if (powerUp2.doesExist()) {
            powerUp2.draw(this.context, canvas);
        }
        if (powerUp3.doesExist()) {
            powerUp3.draw(this.context, canvas);
        }
        if (powerUp4.doesExist()) {
            powerUp4.draw(this.context, canvas);
        }
        drawKeyPad(canvas, getWidth() - 300, getHeight() - 300);
    }

    public void drawScore(Canvas canvas) {
        this.score = 100 - (int) gameLoop.getElapsedTime() + this.gainedScore;
        String score = Integer.toString(this.score);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setTypeface(ResourcesCompat.getFont(context, R.font.press_start_2p));
        paint.setTextSize(100);
        paint.setColor(color);
        paint.setTextAlign(Paint.Align.CENTER);
        int xPos = 3 * canvas.getWidth() / 4;
        canvas.drawText("Score", xPos, 200, paint);
        paint.setTextSize(70);
        canvas.drawText(score, xPos, 300, paint);
    }

    public void drawHP(Canvas canvas) {
        String score = Integer.toString(playerViewModel.getHealth());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setTypeface(ResourcesCompat.getFont(context, R.font.press_start_2p));
        paint.setTextSize(100);
        paint.setColor(color);
        paint.setTextAlign(Paint.Align.CENTER);
        int xPos = canvas.getWidth() / 4;
        canvas.drawText("HP", xPos, 200, paint);
        paint.setTextSize(70);
        canvas.drawText(score, xPos, 300, paint);
    }

    public void drawBackground(Canvas canvas) {
        Bitmap tile = BitmapFactory.decodeResource(getResources(), this.tileset);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(tile, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        canvas.drawRect(0, 0,  getWidth(), getHeight(), paint);
    }

    public void update() {
        this.score = calculateScore();

        // maintain a certain direction if the button remains pressed
        maintainDirection(direction);

        Bounds bounds = playerViewModel.checkBounds(getWidth() - 100,
                getHeight() - 100);
        if (bounds == Bounds.RIGHT_EDGE) {
            if (tileNumber == 2) {
                goToEndScreen(1);
                return;
            }
            tileNumber = (tileNumber + 1) % 3;
            playerViewModel.enterLeft();
        } else if (bounds == Bounds.LEFT_EDGE && tileNumber != 0) {
            tileNumber = (tileNumber - 1) % 3;
            playerViewModel.enterRight(getWidth() - 100);
        }

        if (tileNumber == 0) {
            this.tileset = R.drawable.tile1;
        } else if (tileNumber == 1) {
            this.tileset = R.drawable.tile2;
        } else {
            this.tileset = R.drawable.tile3;
        }
        if (this.score <= 0 || playerViewModel.getHealth() <= 0) {
            goToEndScreen(0);
        }
        boolean attacked;
        enemy1.move();
        attacked = enemy1.notify(playerViewModel.getX(), playerViewModel.getY(), context,
                playerViewModel.getSprite());
        if (attacked) {
            playerViewModel.updateHealth((-1) * (int) enemy1.attack());
            playerViewModel.pushBackLeft();
            if (playerViewModel.isWeaponEquipped()) {
                this.gainedScore += 20;
                enemy1.disappear();
            }
        }
        enemy2.move();
        attacked = enemy2.notify(playerViewModel.getX(), playerViewModel.getY(), context,
                playerViewModel.getSprite());
        if (attacked) {
            playerViewModel.updateHealth((-1) * (int) enemy2.attack());
            playerViewModel.pushBackLeft();
            if (playerViewModel.isWeaponEquipped()) {
                this.gainedScore += 20;
                enemy2.disappear();
            }
        }

        if (powerUp1.madeContact(playerViewModel.getX(), playerViewModel.getY())
                && powerUp1.doesExist()) {
            powerUp1.updatePlayerAttributes(playerViewModel);
            powerUp1.disappear();
        }
        if (powerUp2.madeContact(playerViewModel.getX(), playerViewModel.getY())
                && powerUp2.doesExist()) {
            powerUp2.updatePlayerAttributes(playerViewModel);
            powerUp2.disappear();
        }
        if (powerUp3.madeContact(playerViewModel.getX(), playerViewModel.getY())
                && powerUp3.doesExist()) {
            powerUp3.updatePlayerAttributes(playerViewModel);
            powerUp3.disappear();
        }
        if (powerUp4.madeContact(playerViewModel.getX(), playerViewModel.getY())
                && powerUp4.doesExist()) {
            powerUp4.updatePlayerAttributes(playerViewModel);
            powerUp4.disappear();
        }


    }

    public int calculateScore() {
        return 100 - (int) gameLoop.getElapsedTime();
    }

    public void goToEndScreen(int gameStatus) {
        gameLoop.stopLoop();
        Intent intent = new Intent(this.getContext(), EndScreenActivity.class);
        Attempt newAttempt = new Attempt(this.name, this.score, getCurrentTime());
        leaderboard.addAttempt(newAttempt);
        intent.putExtra("status", gameStatus);
        context.startActivity(intent);
    }

    public String getCurrentTime() {
        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mma");

        // Get the current date and time
        Date currentDate = new Date();

        // Format the date and time in the desired format
        String formattedTime = dateFormat.format(currentDate);
        return formattedTime;
    }

    public boolean isButtonClicked(GameButton button, MotionEvent event) {
        int buttonWidth = button.getWidth();
        int buttonHeight = button.getHeight();
        double topLeftX = button.getButtonX();
        double topLeftY = button.getButtonY();
        double bottomRightX = topLeftX + buttonWidth;
        double bottomRightY = topLeftY + buttonHeight;

        return (event.getX() > topLeftX && event.getX() < bottomRightX)
                && (event.getY() > topLeftY && event.getY() < bottomRightY);
    }

    public void drawKeyPad(Canvas canvas, float x, float y) {
        upButton.draw(canvas, x, y - 150);
        downButton.draw(canvas, x, y + 150);
        rightButton.draw(canvas, x + 150, y);
        leftButton.draw(canvas, x - 150, y);
    }

    public void maintainDirection(Direction direction) {
        if (direction == Direction.UP) {
            playerViewModel.moveUp();
        } else if (direction == Direction.DOWN) {
            playerViewModel.moveDown();
        } else if (direction == Direction.LEFT) {
            playerViewModel.moveLeft();
        } else if (direction == Direction.RIGHT) {
            playerViewModel.moveRight();
        }
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        Typeface customTypeface = ResourcesCompat.getFont(context, R.font.press_start_2p);
        paint.setTypeface(customTypeface);
        paint.setTextSize(100);
        paint.setColor(color);
        canvas.drawText("UPS: " + averageUPS, 100, 300, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.white);
        paint.setTypeface(ResourcesCompat.getFont(context, R.font.press_start_2p));
        paint.setTextSize(100);
        paint.setColor(color);
        canvas.drawText("FPS: " + averageFPS, 100, 500, paint);
    }

}
