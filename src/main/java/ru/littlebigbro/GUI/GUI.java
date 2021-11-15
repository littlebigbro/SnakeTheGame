package main.java.ru.littlebigbro.GUI;

import main.java.ru.littlebigbro.GUI.Buttons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private static final String SNAKE = "Snake";

    private static NewGameButton newGameButton;
    private static PauseButton pauseButton;
    private static RestartButton restartButton;
    private static RulesButton rulesButton;
    private static ExitButton exitButton;
    private static JPanel gamePanel;
    private static JLabel scoreLabel;
    private static JLabel scoreTextLabel;
    private static GamePanel newGame;

    public static void init() {
        JFrame frame = new JFrame(SNAKE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(316, 450));

        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(300, 300));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(300, 100));
        menuPanel.setLayout(new GridBagLayout());

        newGameButton = new NewGameButton();
        menuPanel.add(newGameButton, newGameButton.getGridBagConstraints());

        pauseButton = new PauseButton();
        menuPanel.add(pauseButton, pauseButton.getGridBagConstraints());

        restartButton = new RestartButton();
        menuPanel.add(restartButton, restartButton.getGridBagConstraints());

        rulesButton = new RulesButton();
        menuPanel.add(rulesButton, rulesButton.getGridBagConstraints());

        exitButton = new ExitButton();
        menuPanel.add(exitButton, exitButton.getGridBagConstraints());

        scoreLabel = new JLabel("0");
        menuPanel.add(scoreLabel,
                new GridBagConstraints(5, 0, 1, 1, 1, 1,
                        GridBagConstraints.CENTER,
                        GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
        scoreLabel.setVisible(false);

        scoreTextLabel = new JLabel("Score:", SwingConstants.RIGHT);
        menuPanel.add(scoreTextLabel,
                new GridBagConstraints(4, 0, 1, 1, 1, 1,
                        GridBagConstraints.EAST,
                        GridBagConstraints.HORIZONTAL,
                        new Insets(0, 0, 0, 0), 0, 0));
        scoreTextLabel.setVisible(false);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(gamePanel);
        frame.add(menuPanel);
        frame.addKeyListener(new ExitActionListener());
        frame.setFocusable(true);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void startNewGameAction() {
        newGame = new GamePanel();
        gamePanel.add(newGame);
        newGame.requestFocus();
        pauseButton.setVisible(true);
        restartButton.setVisible(true);
        Timer timer = new Timer(newGame.getTimerDelay(), new ScoreListener());
        timer.start();
        scoreLabel.setVisible(true);
        scoreTextLabel.setVisible(true);
    }

    public static void pauseAction() {
        if (newGame != null && !newGame.isPaused()) {
            pauseButton.changeTextOfPauseButton();
            newGame.pause();
            newGame.requestFocus();
        }
    }

    public static void restartAction() {
        newGame.restart();
        newGame.requestFocus();
    }

    public static void exitAction() {
        if (newGame != null && !newGame.isPaused()) {
            pauseAction();
        }
        int resDlg = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (resDlg == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    static class ScoreListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            scoreLabel.setText(String.valueOf(newGame.getScore()));
            pauseButton.changeTextOfPauseButton();
        }
    }
}