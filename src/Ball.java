import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 2;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        // for X direction
        int randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection * initialSpeed);

        // for Y direction
        int randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * initialSpeed);
    }

    /**
     * ball moving randomly
     *
     * @param randomXDirection set it with instance of Random class
     */
    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    /**
     * ball moving randomly
     *
     * @param randomYDirection set it with instance of Random class
     */
    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, height, width); //height=weight=BALL_DIAMETER
    }
}
