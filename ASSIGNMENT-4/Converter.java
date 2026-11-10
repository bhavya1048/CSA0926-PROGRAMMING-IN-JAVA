import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Converter extends JFrame {
    private JTextField amount = new JTextField(10);

    private JComboBox<String> target =
        new JComboBox<>(new String[]{"USD", "EUR"});

    private JLabel result = new JLabel(" ");

    private static double rate(String currency) {
        return currency.equals("USD") ? 0.012 : 0.011;
    }

    public Converter() {
        super("Currency Converter");
        setLayout(new FlowLayout());

        JButton convertButton = new JButton("Convert");

        add(new JLabel("INR"));
        add(amount);
        add(target);
        add(convertButton);
        add(result);

        ActionListener action = e -> convert();

        convertButton.addActionListener(action);
        amount.addActionListener(action);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 150);
        setVisible(true);
    }

    private void convert() {
        try {
            double value = Double.parseDouble(amount.getText());

            if (value < 0)
                throw new NumberFormatException();

            String currency = (String) target.getSelectedItem();
            double converted = value * rate(currency);

            String message = String.format(
                "%.2f %s", converted, currency);

            result.setText(message);

            JOptionPane.showMessageDialog(this,
                "Converted Amount: " + message);

        } catch (NumberFormatException e) {
            result.setText("Invalid amount");

            JOptionPane.showMessageDialog(this,
                "Invalid amount");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Converter());
    }
}