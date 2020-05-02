import javax.swing.*;
import java.awt.*;

public class StartGUI {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500,500));
        frame.add(new GamePanel());
        frame.addKeyListener(new ExitAction());
        frame.setFocusable(true);

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
