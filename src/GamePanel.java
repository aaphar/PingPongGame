import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image; // for to extend runnable interface
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    public GamePanel() {
        // calling newPaddle method
        newPaddles();
        // calling newBall method
        newBall();
        // instantiate  score class
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL()); // pass in our inner class
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void newBall() {
        random = new Random();

        /**
         * ball start center of the window
         */

        // x, y, ballWidth, ballHeight -> for perfect circle
        ball = new Ball(GAME_WIDTH / 2 - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER), BALL_DIAMETER, BALL_DIAMETER);

    }

    public void newPaddles() {
        // first argument is 0, so it will be very left of the panel
        paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        // second paddle will be right of the panel
        paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 2);

    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        //draw paddle
        paddle1.draw(g);
        paddle2.draw(g);

        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void move() {
        // for to make move of each component move smooth
        // call each of individual move method after each iteration of game loop

        paddle1.move();
        paddle2.move();

        ball.move();
    }

    public void checkCollision() {
        /**
         * bounce ball off top & window edges and paddles
         */
        // for top & window edges
        if (ball.y <= 0) {
            // ball going to move opposite direction
            ball.setYDirection(-ball.yVelocity);
        }
        if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
            // ball going to move opposite direction
            ball.setYDirection(-ball.yVelocity);
        }

        // for paddle1 which is in the right
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity); // turn it into positive number
            ball.xVelocity++; // this is optional for move difficulty of game

            // check for yVelocity -> this is also optional for diffficulty
            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                // if ball is not intersecting with paddles then decrease upward velocity
                ball.yVelocity--;
            }

            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        // for paddle2 which is in the left
        if (ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity); // turn it into positive number
            ball.xVelocity++; // this is optional for move difficulty of game

            // check for yVelocity -> this is also optional for diffficulty
            if (ball.yVelocity > 0) {
                ball.yVelocity++;
            } else {
                // if ball is not intersecting with paddles then decrease upward velocity
                ball.yVelocity--;
            }

            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }


        /**
         * stops paddles at window edges
         */
        // for paddle 1
        if (paddle1.y <= 0) {
            paddle1.y = 0;
        }
        if (paddle1.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        // for paddle 2
        if (paddle2.y <= 0) {
            paddle2.y = 0;
        }
        if (paddle2.y >= GAME_HEIGHT - PADDLE_HEIGHT) {
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
        }

        /**
         * give player 1 point and create new paddles & ball
         */
        // player2(left one) got score
        if (ball.x < 0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: " + score.player2);
        }
        // player1(right one) got score
        if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: " + score.player1);
        }
    }

    public void run() {
        // basic game loop

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks; // nano-seconds
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                // if we press any key delta become greater than 1
                // and game will be beginning and we will call move method

                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    /**
     * inner class called Action Listener
     */
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            // move paddles
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyRelease(KeyEvent e) {
            paddle1.keyRelease(e);
            paddle2.keyRelease(e);
        }
    }
}
