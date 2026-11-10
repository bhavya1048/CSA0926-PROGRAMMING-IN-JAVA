import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LibrarySearch extends JFrame {
    JTextField search = new JTextField(15);
    JComboBox<String> category = new JComboBox<>(
        new String[]{"All", "Java", "Python", "C"});
    JTextArea results = new JTextArea();
    JLabel status = new JLabel("Ready");

    LibrarySearch() {
        setTitle("Campus Library Search");
        setLayout(new BorderLayout(5, 5));

        JLabel title = new JLabel(
            "CAMPUS LIBRARY", JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel top = new JPanel(new FlowLayout());
        top.add(search);
        top.add(category);

        JButton find = new JButton("Search");
        top.add(find);

        JPanel north = new JPanel(new BorderLayout());
        north.add(title, BorderLayout.NORTH);
        north.add(top, BorderLayout.SOUTH);
        add(north, BorderLayout.NORTH);

        results.setEditable(false);
        add(new JScrollPane(results), BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout());
        JButton prev = new JButton("Prev");
        JButton next = new JButton("Next");
        JButton reserve = new JButton("Reserve");

        buttons.add(prev);
        buttons.add(next);
        buttons.add(reserve);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(buttons, BorderLayout.CENTER);
        bottom.add(status, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);

        find.addActionListener(e -> {
            String key = search.getText().trim();

            if (key.isEmpty()) {
                status.setText("Enter book name");
            } else {
                results.setText("Book Found: " + key
                    + "\nCategory: " + category.getSelectedItem());
                status.setText("Search completed");
            }
        });

        prev.addActionListener(e -> status.setText("Previous page"));
        next.addActionListener(e -> status.setText("Next page"));
        reserve.addActionListener(e -> status.setText("Book reserved"));

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LibrarySearch();
    }
}