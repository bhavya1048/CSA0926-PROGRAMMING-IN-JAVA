
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

class EventDAO {

    String url = "jdbc:mysql://localhost:3306/campus_events";
    String user = "root";
    String pass = "YOUR_MYSQL_PASSWORD";

    Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    void register(String id, String name, String event)
            throws Exception {

        String sql = "INSERT INTO registrations"
                + "(student_id, student_name, event_name)"
                + " VALUES (?, ?, ?)";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, event);
            ps.executeUpdate();
        }
    }

    List<Object[]> getRecords(String event, int limit, int offset)
            throws Exception {

        List<Object[]> rows = new ArrayList<>();

        String sql = "SELECT student_id, student_name, "
                + "event_name, reg_date FROM registrations "
                + "WHERE (? = 'All' OR event_name = ?) "
                + "ORDER BY id LIMIT ? OFFSET ?";

        try (Connection con = connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, event);
            ps.setString(2, event);
            ps.setInt(3, limit);
            ps.setInt(4, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new Object[]{
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getString("event_name"),
                        rs.getTimestamp("reg_date")
                    });
                }
            }
        }

        return rows;
    }
}

public class EventRegistrationApp extends JFrame {

    JTextField id = new JTextField(10);
    JTextField name = new JTextField(10);

    JComboBox<String> event = new JComboBox<>(
        new String[]{"Cultural Fest", "Sports Day",
                     "Technical Symposium"});

    JComboBox<String> filter = new JComboBox<>(
        new String[]{"All", "Cultural Fest", "Sports Day",
                     "Technical Symposium"});

    DefaultTableModel model = new DefaultTableModel(
        new String[]{"Student ID", "Name", "Event", "Date"}, 0);

    JTable table = new JTable(model);

    EventDAO dao = new EventDAO();

    int page = 0;
    final int PAGE_SIZE = 20;

    EventRegistrationApp() {

        setTitle("Campus Event Registration");
        setLayout(new BorderLayout());

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");

        menu.add(exit);
        bar.add(menu);
        setJMenuBar(bar);

        exit.addActionListener(e -> dispose());

        JPanel form = new JPanel(new FlowLayout());

        form.add(new JLabel("Student ID"));
        form.add(id);

        form.add(new JLabel("Student Name"));
        form.add(name);

        form.add(new JLabel("Event"));
        form.add(event);

        JButton register = new JButton("Register");
        form.add(register);

        add(form, BorderLayout.NORTH);

        JPanel bottom = new JPanel(new FlowLayout());

        bottom.add(new JLabel("Filter Event"));
        bottom.add(filter);

        JButton view = new JButton("View Records");
        JButton export = new JButton("Export CSV");
        JButton prev = new JButton("Previous");
        JButton next = new JButton("Next");

        bottom.add(view);
        bottom.add(export);
        bottom.add(prev);
        bottom.add(next);

        add(bottom, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        register.addActionListener(e -> registerStudent());

        view.addActionListener(e -> {
            page = 0;
            loadRecords();
        });

        prev.addActionListener(e -> {
            if (page > 0) {
                page--;
                loadRecords();
            }
        });

        next.addActionListener(e -> {
            page++;
            loadRecords();
        });

        filter.addActionListener(e -> page = 0);

        export.addActionListener(e -> exportCSV());

        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void registerStudent() {

        String sid = id.getText().trim();
        String sname = name.getText().trim();
        String ev = (String) event.getSelectedItem();

        if (sid.isEmpty() || sname.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "All fields are required");
            return;
        }

        new SwingWorker<Void, Void>() {

            protected Void doInBackground() throws Exception {
                dao.register(sid, sname, ev);
                return null;
            }

            protected void done() {
                try {
                    get();

                    JOptionPane.showMessageDialog(
                        EventRegistrationApp.this,
                        "Registration Successful");

                    id.setText("");
                    name.setText("");
                    loadRecords();

                } catch (Exception ex) {

                    Throwable cause = ex.getCause();

                    String msg = cause == null
                        ? ex.getMessage()
                        : cause.getMessage();

                    if (msg != null && msg.contains("Duplicate")) {
                        msg = "Student already registered for this event";
                    }

                    JOptionPane.showMessageDialog(
                        EventRegistrationApp.this,
                        "Registration Failed: " + msg);
                }
            }

        }.execute();
    }

    void loadRecords() {

        model.setRowCount(0);

        String selected = (String) filter.getSelectedItem();
        int currentOffset = page * PAGE_SIZE;

        new SwingWorker<List<Object[]>, Void>() {

            protected List<Object[]> doInBackground()
                    throws Exception {
                return dao.getRecords(
                    selected, PAGE_SIZE, currentOffset);
            }

            protected void done() {
                try {
                    for (Object[] row : get()) {
                        model.addRow(row);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        EventRegistrationApp.this,
                        "Unable to load records: "
                        + ex.getMessage());
                }
            }

        }.execute();
    }

    void exportCSV() {

        try (PrintWriter out =
                new PrintWriter(new FileWriter("registrations.csv"))) {

            for (int c = 0; c < model.getColumnCount(); c++) {
                out.print(model.getColumnName(c));
                if (c < model.getColumnCount() - 1)
                    out.print(",");
            }
            out.println();

            for (int r = 0; r < model.getRowCount(); r++) {
                for (int c = 0; c < model.getColumnCount(); c++) {
                    String value =
                        String.valueOf(model.getValueAt(r, c));

                    out.print("\"" +
                        value.replace("\"", "\"\"") + "\"");

                    if (c < model.getColumnCount() - 1)
                        out.print(",");
                }
                out.println();
            }

            JOptionPane.showMessageDialog(this,
                "CSV exported successfully");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Export failed: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new EventRegistrationApp());
    }
}
