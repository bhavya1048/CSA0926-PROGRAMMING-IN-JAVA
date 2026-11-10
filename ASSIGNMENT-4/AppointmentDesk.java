import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AppointmentDesk extends JFrame {
    JTextField patient = new JTextField(12);
    JComboBox<String> dept = new JComboBox<>(
        new String[]{"Cardiology", "Neurology", "Orthopedics"});
    JComboBox<String> doctor = new JComboBox<>();
    JComboBox<String> time = new JComboBox<>(
        new String[]{"10 AM", "11 AM", "2 PM", "3 PM"});
    JLabel status = new JLabel("Enter appointment details");

    Map<String, String[]> doctors = new HashMap<>();
    boolean booking = false;

    AppointmentDesk() {
        setTitle("Hospital Appointment Desk");
        setLayout(new GridLayout(6, 2, 5, 5));

        doctors.put("Cardiology",
            new String[]{"Dr. Arun", "Dr. Kumar"});
        doctors.put("Neurology",
            new String[]{"Dr. Ravi", "Dr. Priya"});
        doctors.put("Orthopedics",
            new String[]{"Dr. Mani", "Dr. Suresh"});

        add(new JLabel("Patient ID:"));
        add(patient);
        add(new JLabel("Department:"));
        add(dept);
        add(new JLabel("Doctor:"));
        add(doctor);
        add(new JLabel("Time Slot:"));
        add(time);

        JButton book = new JButton("Book Appointment");
        add(book);
        add(status);

        dept.addActionListener(e -> updateDoctors());
        book.addActionListener(e -> bookAppointment());

        updateDoctors();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (booking) {
                    int ans = JOptionPane.showConfirmDialog(
                        AppointmentDesk.this,
                        "Booking is in progress. Close?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION);

                    if (ans == JOptionPane.YES_OPTION)
                        dispose();
                } else {
                    dispose();
                }
            }
        });

        setSize(400, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    void updateDoctors() {
        doctor.removeAllItems();

        String d = (String) dept.getSelectedItem();

        for (String name : doctors.get(d))
            doctor.addItem(name);
    }

    void bookAppointment() {
        if (patient.getText().trim().isEmpty()) {
            status.setText("Enter Patient ID");
            return;
        }

        booking = true;

        status.setText("Appointment Booked Successfully");

        JOptionPane.showMessageDialog(this,
            "Patient ID: " + patient.getText()
            + "\nDoctor: " + doctor.getSelectedItem()
            + "\nTime: " + time.getSelectedItem());

        booking = false;
    }

    public static void main(String[] args) {
        new AppointmentDesk();
    }
}