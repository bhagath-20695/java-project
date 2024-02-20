import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PinballGame extends JPanel implements ActionListener {
    private int ballX = 160, ballY = 100, ballDeltaX = 2, ballDeltaY = 3;
    private int paddleX = 150;
    private final int paddleWidth = 100;
    private final int paddleHeight = 10;
    private boolean leftPressed = false, rightPressed = false;
    private final int WIDTH = 400, HEIGHT = 300;
    private final Timer timer;

    public PinballGame() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        timer = new Timer(10, this);
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
            }
        });

        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 10, 10);
        g.fillRect(paddleX, HEIGHT - 30, paddleWidth, paddleHeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballX += ballDeltaX;
        ballY += ballDeltaY;

        if (ballX < 0 || ballX > WIDTH - 10) ballDeltaX *= -1;
        if (ballY < 0) ballDeltaY *= -1;

        if (ballY > HEIGHT - 40 && ballX > paddleX && ballX < paddleX + paddleWidth) {
            ballDeltaY *= -1;
        } else if (ballY > HEIGHT) {
            ballX = 160;
            ballY = 100;
        }

        if (leftPressed && paddleX > 0) paddleX -= 5;
        if (rightPressed && paddleX < WIDTH - paddleWidth) paddleX += 5;

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pinball Game");
        PinballGame game = new PinballGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
