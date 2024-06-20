import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private BankAccount userAccount;

    private JLabel balanceLabel;
    private JTextField withdrawField;
    private JTextField depositField;

    public ATM(BankAccount account) {
        this.userAccount = account;

        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        initializeUI();

        // Display initial balance
        updateBalanceLabel();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel label1 = new JLabel("Current Balance:");
        balanceLabel = new JLabel();
        JLabel label2 = new JLabel("Withdraw Amount:");
        withdrawField = new JTextField();
        JLabel label3 = new JLabel("Deposit Amount:");
        depositField = new JTextField();

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBalanceLabel();
            }
        });

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = withdrawField.getText();
                if (!amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);
                    withdraw(amount);
                    withdrawField.setText("");
                    updateBalanceLabel();
                } else {
                    JOptionPane.showMessageDialog(ATM.this, "Please enter an amount to withdraw.");
                }
            }
        });

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountStr = depositField.getText();
                if (!amountStr.isEmpty()) {
                    double amount = Double.parseDouble(amountStr);
                    deposit(amount);
                    depositField.setText("");
                    updateBalanceLabel();
                } else {
                    JOptionPane.showMessageDialog(ATM.this, "Please enter an amount to deposit.");
                }
            }
        });

        panel.add(label1);
        panel.add(balanceLabel);
        panel.add(label2);
        panel.add(withdrawField);
        panel.add(label3);
        panel.add(depositField);
        panel.add(checkBalanceButton);
        panel.add(withdrawButton);
        panel.add(new JLabel()); // Placeholder
        panel.add(depositButton);

        add(panel, BorderLayout.CENTER);
    }

    private void updateBalanceLabel() {
        double balance = userAccount.getBalance();
        balanceLabel.setText("$" + balance);
    }

    private void withdraw(double amount) {
        boolean success = userAccount.withdraw(amount);
        if (success) {
            JOptionPane.showMessageDialog(this, "Withdrawal successful. Remaining balance: $" + userAccount.getBalance());
        } else {
            JOptionPane.showMessageDialog(this, "Insufficient funds. Withdrawal failed.");
        }
    }

    private void deposit(double amount) {
        userAccount.deposit(amount);
        JOptionPane.showMessageDialog(this, "Deposit successful. New balance: $" + userAccount.getBalance());
    }

    public static void main(String[] args) {
        // Example usage:
        BankAccount account = new BankAccount(1000); // Initial balance of $1000
        ATM atm = new ATM(account);
        atm.setVisible(true);
    }
}
