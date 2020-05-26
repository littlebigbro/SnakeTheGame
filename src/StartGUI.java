import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGUI implements ActionListener {
    JButton nGame;
    JButton B1;
    JPanel gamePanel;
    JLabel label;
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(376,499));
        frame.setLayout(new BorderLayout());
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(360,360));


        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(500,100));
        nGame = new JButton("Новая игра");
        nGame.setPreferredSize(new Dimension(100, 50));
        nGame.addActionListener(this);
        B1 = new JButton("B1");
        B1.setPreferredSize(new Dimension(100, 50));
        B1.addActionListener(this);
        menuPanel.add(B1);
        B1.setVisible(false);
        label = new JLabel();
        menuPanel.add(nGame);
        menuPanel.add(label);
        menuPanel.setVisible(true);
        frame.add(gamePanel, BorderLayout.NORTH);
        frame.add(menuPanel, BorderLayout.SOUTH);
        frame.addKeyListener(new ExitAction());

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        StartGUI gui = new StartGUI();
        gui.createAndShowGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameField newGame = new GameField();
        gamePanel.add(newGame);
        newGame.requestFocus();
        nGame.setVisible(false);
        B1.setVisible(true);
        B1.setText("B2");
        label.setText(newGame.getGameScore());
        label.setVisible(true);

    }
}