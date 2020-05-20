import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartGUI {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(576,599));
        frame.setLayout(new BorderLayout());
        frame.add(new Score(), BorderLayout.NORTH);
        frame.add(new GamePanel(), BorderLayout.CENTER);
        JPanel menuPanel = new JPanel();
        JPanel menuPanel1 = new JPanel();
        JPanel menuPanel2 = new JPanel();
        JPanel menuPanel3 = new JPanel();
        menuPanel.setPreferredSize(new Dimension(500,100));
        menuPanel.setVisible(true);
        menuPanel1.setPreferredSize(new Dimension(500,100));
        menuPanel1.setVisible(true);
        menuPanel2.setPreferredSize(new Dimension(100,499));
        menuPanel2.setVisible(true);
        menuPanel3.setPreferredSize(new Dimension(100,499));
        menuPanel3.setVisible(true);
        frame.add(menuPanel, BorderLayout.SOUTH);
        frame.add(menuPanel1, BorderLayout.NORTH);
        frame.add(menuPanel2, BorderLayout.EAST);
        frame.add(menuPanel3, BorderLayout.WEST);
        frame.addKeyListener(new ExitAction());
        //frame.setFocusable(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
