import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

public class SnakeGame extends JFrame {
    private LinkedList<Point> snake = new LinkedList<>();
    private int speed = 10;
    private double angle;

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.GREEN);
            for (Point p : snake) {
                g.fillRect(p.x, p.y, 10, 10);
            }
        }
    }

    public SnakeGame() {
        setTitle("Змейка");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GamePanel panel = new GamePanel();
        add(panel);

        angle = new Random().nextDouble() * 2 * Math.PI;
        for (int i = 0; i < 5; i++) {
            snake.add(new Point(200 + i * 10, 200));
        }

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = snake.getFirst().x + (int) (speed * Math.cos(angle));
                int y = snake.getFirst().y + (int) (speed * Math.sin(angle));
                if (x + 10 > panel.getWidth() || x < 0) {
                    angle = Math.PI - angle;
                }
                if (y + 10 > panel.getHeight() || y < 0) {
                    angle = -angle;
                }
                snake.addFirst(new Point(x, y));
                snake.removeLast();
                panel.repaint();
            }
        });
        timer.start();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnakeGame());
    }
}