import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartGUI {
    JButton newGameButton;
    JButton pauseButton;
    JButton restartButton;
    JButton rulesButton;
    JButton exitButton;
    JPanel gamePanel;
    JLabel scoreLabel;
    JLabel scoreTextLabel;
    GameField newGame;

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(316,450));

        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(300,300));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(300,100));
        menuPanel.setLayout(new GridBagLayout());

        newGameButton = new JButton("New Game");
        newGameButton.setPreferredSize(new Dimension(90, 40));
        newGameButton.addActionListener(new NewGameListener());
        menuPanel.add(newGameButton, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        newGameButton.setVisible(true);

        pauseButton = new JButton("Pause");
        pauseButton.setPreferredSize(new Dimension(90, 40));
        pauseButton.addActionListener(new PauseListener());
        menuPanel.add(pauseButton, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        pauseButton.setVisible(false);

        restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(90, 40));
        restartButton.addActionListener(new RestartListener());
        menuPanel.add(restartButton, new GridBagConstraints(2, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        restartButton.setVisible(false);


        rulesButton = new JButton("Rules");
        rulesButton.setPreferredSize(new Dimension(90, 40));
        rulesButton.addActionListener(new RulesListener());
        menuPanel.add(rulesButton, new GridBagConstraints(4, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        rulesButton.setVisible(true);

        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(90, 40));
        exitButton.addActionListener(new ExitListenerButton());
        menuPanel.add(exitButton, new GridBagConstraints(0, 2, 6, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        exitButton.setVisible(true);

        scoreLabel = new JLabel("0");
        menuPanel.add(scoreLabel, new GridBagConstraints(5, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        scoreLabel.setVisible(false);

        scoreTextLabel = new JLabel("Score:", SwingConstants.RIGHT);
        menuPanel.add(scoreTextLabel, new GridBagConstraints(4, 0, 1, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0,0,0,0),0,0));
        scoreTextLabel.setVisible(false);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(gamePanel);
        frame.add(menuPanel);
        frame.addKeyListener(new ExitListener());
        frame.setFocusable(true);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    void textOfPauseButton(){
        if(pauseButton.getText().equals("Pause") && newGame.isPaused()) {
            pauseButton.setText("Continue");
        } else {
            if(pauseButton.getText().equals("Continue") && !newGame.isPaused()) {
                pauseButton.setText("Pause");
            }
        }
    }

    void ExitAction(){
        int resDlg = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (resDlg == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    class scoreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            scoreLabel.setText(String.valueOf(newGame.getScore()));
            textOfPauseButton();
        }
    }

    public static void main(String[] args) {
        StartGUI gui = new StartGUI();
        gui.createAndShowGUI();
    }

    class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            newGameButton.setVisible(false);
            newGame = new GameField();
            gamePanel.add(newGame);
            newGame.requestFocus();
            pauseButton.setVisible(true);
            restartButton.setVisible(true);
            Timer timer = new Timer(newGame.getTimerDelay(), new scoreListener());
            timer.start();
            scoreLabel.setVisible(true);
            scoreTextLabel.setVisible(true);
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
            textOfPauseButton();
            newGame.gamePause();
            newGame.requestFocus();
        }
    }

    class RulesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!newGameButton.isVisible()) {
                textOfPauseButton();
                newGame.gamePause();
            }
            String TITLE_message = "Rules";
            String[] rulesText = new String[]{
                    "Rules:",
                    "1) Eat green apple and you gain 10 points;",
                    "2) Eat red apple and you gain 100 points;",
                    "3) Eat yellow apple and you lose 50 points;",
                    "4) DO NOT hit walls or yourself!!!"
            };
            JOptionPane.showMessageDialog(null, rulesText, TITLE_message, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class ExitListenerButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ExitAction();
        }
    }

    class ExitListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) { }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                ExitAction();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) { }
    }
}