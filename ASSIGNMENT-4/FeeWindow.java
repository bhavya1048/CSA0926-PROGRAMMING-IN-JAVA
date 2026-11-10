import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FeeService {
    public String payFee(String regNo, String amountText) {
        try {
            if (regNo.trim().isEmpty())
                return "Enter Registration Number";

            double amount = Double.parseDouble(amountText);

            if (amount <= 0)
                return "Enter a valid amount";

            return "Payment Successful!\nAmount: Rs. " + amount;

        } catch (NumberFormatException e) {
            return "Invalid Amount";
        }
    }
}

public class FeeWindow extends JFrame {
    JTextField regNo = new JTextField(15);
    JTextField amount = new JTextField(15);
    JLabel result = new JLabel("Enter payment details");

    FeeService service = new FeeService();

    FeeWindow() {
        setTitle("Student Fee Payment");
        setLayout(new GridLayout(4, 2, 10, 10));

        JButton pay = new JButton("Pay");
        JButton clear = new JButton("Clear");
        JButton receipt = new JButton("Receipt");

        add(new JLabel("Registration Number:"));
        add(regNo);
        add(new JLabel("Amount:"));
        add(amount);
        add(pay);
        add(clear);
        add(receipt);
        add(result);

        pay.addActionListener(e -> {
            String message = service.payFee(
                regNo.getText(), amount.getText()
            );
            result.setText(message.replace("\n", " "));
            JOptionPane.showMessageDialog(this, message);
        });

        clear.addActionListener(e -> {
            regNo.setText("");
            amount.setText("");
            result.setText("Fields cleared");
        });

        receipt.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "Receipt for: " + regNo.getText()
                + "\nAmount: " + amount.getText())
        );

        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FeeWindow::new);
    }
}