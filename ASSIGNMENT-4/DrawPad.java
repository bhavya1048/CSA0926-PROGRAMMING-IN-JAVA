import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawPad extends Frame {
    private ArrayList<Point> points = new ArrayList<>();
    private Label status = new Label("Clicks: 0");

    DrawPad() {
        super("Drawing Pad");
        add(status, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                points.add(e.getPoint());
                status.setText("Clicks: " + points.size());
                repaint();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(400, 300);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Point p : points)
            g.fillOval(p.x - 3, p.y - 3, 6, 6);
    }

    public static void main(String[] args) {
        new DrawPad().setVisible(true);
    }
}