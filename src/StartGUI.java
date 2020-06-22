import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartGUI {
    JButton nGameButton;
    JButton pauseButton;
    JButton restartButton;
    JButton rulesButton;
    JButton exitButton;
    JPanel gamePanel;
    GameField newGame;

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(376,499));
        frame.setLayout(new BorderLayout());
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(300,306));

        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(500,100));
        nGameButton = new JButton("New Game");
        nGameButton.setPreferredSize(new Dimension(100, 50));
        nGameButton.addActionListener(new NewGameListener());
        menuPanel.add(nGameButton);
        nGameButton.setVisible(true);

        pauseButton = new JButton("Pause");
        pauseButton.setPreferredSize(new Dimension(100, 50));
        pauseButton.addActionListener(new PauseListener());
        menuPanel.add(pauseButton);
        pauseButton.setVisible(false);

        restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(100, 50));
        restartButton.addActionListener(new RestartListener());
        menuPanel.add(restartButton);
        restartButton.setVisible(false);


        rulesButton = new JButton("Rules");
        rulesButton.setPreferredSize(new Dimension(100, 50));
        rulesButton.addActionListener(new RulesListener());
        menuPanel.add(rulesButton);
        rulesButton.setVisible(true);

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(100, 50));
        exitButton.addActionListener(new ExitListenerButton());
        menuPanel.add(exitButton);
        exitButton.setVisible(true);

        frame.add(gamePanel, BorderLayout.NORTH);
        frame.add(menuPanel, BorderLayout.SOUTH);
        frame.addKeyListener(new ExitListener());
        frame.setFocusable(true);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        StartGUI gui = new StartGUI();
        gui.createAndShowGUI();
    }

    class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            nGameButton.setVisible(false);
            newGame = new GameField();
            gamePanel.add(newGame);
            newGame.requestFocus();
            pauseButton.setVisible(true);
            restartButton.setVisible(true);
        }
    }

    class RestartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            newGame.restart();
            newGame.requestFocus();
        }
    }

    class PauseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(pauseButton.getText().equals("Pause")) {
                pauseButton.setText("Continue");
                newGame.gamePause();
            } else {
                if(pauseButton.getText().equals("Continue")) {
                    pauseButton.setText("Pause");
                    newGame.gamePause();
                    newGame.requestFocus();
                }
            }
        }
    }

    class RulesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ExitListenerButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new ExitAction();
        }
    }
    class ExitListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) { }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                new ExitAction();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) { }
    }
}