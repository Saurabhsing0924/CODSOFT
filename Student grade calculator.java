import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculatorGUI extends JFrame {
    private JTextField numSubjectsField;
    private JTextField[] marksFields;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;

    public StudentGradeCalculatorGUI() {
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the frame on the screen
        setLayout(new BorderLayout());

        // Panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        numSubjectsField = new JTextField(10);
        inputPanel.add(new JLabel("Number of Subjects:"));
        inputPanel.add(numSubjectsField);

        // Add labels and text fields for marks input
        marksFields = new JTextField[10];  // Assuming maximum 10 subjects
        for (int i = 0; i < 10; i++) {
            marksFields[i] = new JTextField(10);
            inputPanel.add(new JLabel("Marks for Subject " + (i + 1) + ":"));
            inputPanel.add(marksFields[i]);
        }

        // Panel for output components
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(0, 1, 10, 10));

        totalMarksLabel = new JLabel("Total Marks: ");
        outputPanel.add(totalMarksLabel);

        averagePercentageLabel = new JLabel("Average Percentage: ");
        outputPanel.add(averagePercentageLabel);

        gradeLabel = new JLabel("Grade: ");
        outputPanel.add(gradeLabel);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton calculateButton = new JButton("Calculate Grades");
        buttonPanel.add(calculateButton);

        // Add panels to the main frame
        add(inputPanel, BorderLayout.NORTH);
        add(outputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // ActionListener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });
    }

    private void calculateGrades() {
        try {
            int numSubjects = Integer.parseInt(numSubjectsField.getText());

            if (numSubjects < 1 || numSubjects > 10) {
                JOptionPane.showMessageDialog(this, "Number of subjects should be between 1 and 10.");
                return;
            }

            int totalMarks = 0;
            for (int i = 0; i < numSubjects; i++) {
                int marks = Integer.parseInt(marksFields[i].getText());
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Marks should be between 0 and 100.");
                    return;
                }
                totalMarks += marks;
            }

            double averagePercentage = (double) totalMarks / numSubjects;

            String grade;
            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C";
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
            gradeLabel.setText("Grade: " + grade);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentGradeCalculatorGUI calculator = new StudentGradeCalculatorGUI();
                calculator.setVisible(true);
            }
        });
    }
}
