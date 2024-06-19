import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private Random random = new Random();
    private int generatedNumber;
    private JTextField guessField;
    private JLabel feedbackLabel;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame on the screen
        setLayout(new BorderLayout());

        generatedNumber = random.nextInt(100) + 1;

        JLabel promptLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(5);
        JButton guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(promptLabel);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new FlowLayout());
        feedbackPanel.add(feedbackLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(feedbackPanel, BorderLayout.CENTER);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void checkGuess() {
        String userInput = guessField.getText();
        try {
            int userGuess = Integer.parseInt(userInput);
            if (userGuess < 1 || userGuess > 100) {
                feedbackLabel.setText("Please enter a number between 1 and 100.");
            } else if (userGuess == generatedNumber) {
                feedbackLabel.setText("Congratulations! You guessed the correct number.");
                guessField.setEditable(false); // Disable guess field after correct guess
            } else if (userGuess < generatedNumber) {
                feedbackLabel.setText("Your guess is too low. Try again.");
            } else {
                feedbackLabel.setText("Your guess is too high. Try again.");
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a valid number.");
        }
        guessField.setText(""); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGuessingGameGUI game = new NumberGuessingGameGUI();
                game.setVisible(true);
            }
        });
    }
}
