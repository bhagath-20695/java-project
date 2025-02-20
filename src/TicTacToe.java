import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe implements ActionListener {
    private final JFrame frame = new JFrame("Tic Tac Toe");
    private final JButton[] buttons = new JButton[9];
    private boolean playerX = true;

    public TicTacToe() {
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            frame.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().isEmpty()) {
            buttonClicked.setText(playerX ? "X" : "O");
            playerX = !playerX;
        }

        checkWinner();
    }

    private void checkWinner() {
        int[][] winPatterns = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Columns
                {0, 4, 8}, {2, 4, 6}              // Diagonals
        };

        for (int[] pattern : winPatterns) {
            if (buttons[pattern[0]].getText().equals(buttons[pattern[1]].getText()) &&
                    buttons[pattern[1]].getText().equals(buttons[pattern[2]].getText()) &&
                    !buttons[pattern[0]].getText().isEmpty()) {
                showGameOverDialog("Player " + buttons[pattern[0]].getText() + " Wins!");
                return;
            }
        }

        // Check for a Draw (if all buttons are filled)
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                draw = false;
                break;
            }
        }

        if (draw) {
            showGameOverDialog("It's a Draw!");
        }
    }

    private void showGameOverDialog(String message) {
        int response = JOptionPane.showConfirmDialog(frame, message + "\nDo you want to play again?", "Game Over",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            frame.dispose(); // Close the game
        }
    }

    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        playerX = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
