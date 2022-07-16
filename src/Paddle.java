import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    private int id;
    int yVelocity;
    int speed = 10;

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    // if someone types W on their keyboard
                    // paddle will move up

                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    // if someone types S on their keyboard
                    // paddle will move down

                    setYDirection(speed);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    // if someone presses UP button on their keyboard
                    // paddle will move up

                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    // if someone presses DOWN on their keyboard
                    // paddle will move down

                    setYDirection(speed);
                    move();
                }
                break;
        }
    }

    public void keyRelease(KeyEvent e) {
        switch (id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    // if someone types W on their keyboard
                    // paddle will move up

                    // if we keep parameter speed, we will move forever
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    // if someone types S on their keyboard
                    // paddle will move down

                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    // if someone presses UP button on their keyboard
                    // paddle will move up

                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    // if someone presses DOWN on their keyboard
                    // paddle will move down

                    setYDirection(0);
                    move();
                }
                break;
        }
    }

    /**
     * only moving up and down, that is why we only need yDirection
     *
     * @param yDirection
     */
    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void move() {
        y = y + yVelocity;
    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.red);
        }

        g.fillRect(x, y, width, height);
    }
}
