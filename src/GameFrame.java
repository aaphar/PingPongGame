import javax.swing.*;
import java.awt.*;

/**
 * GameFrame is basically windows frame that has the
 * minimize button maximized the X button.
 * Frame is just houses our panel
 */
public class GameFrame extends JFrame {
    GamePanel panel;

    public GameFrame() {
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when we hit X button it will exit frame
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null); // it is going to appear in the middle of the screen
    }
}
