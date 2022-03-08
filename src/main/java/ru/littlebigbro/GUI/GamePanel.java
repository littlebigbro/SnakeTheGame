package main.java.ru.littlebigbro.GUI;

import main.java.ru.littlebigbro.Engines.GameEngine;
import main.java.ru.littlebigbro.Interfaces.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener, GameConstants {

    private GameEngine game;
    private int gameScore;

    public void start() {
        game = new GameEngine(this);
        game.start();
        gameScore = 0;
        setPreferredSize(new Dimension(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS));
        setBackground(Color.BLACK);
        addKeyListener(game.getFieldKeyListener());
        requestFocus();
    }

    public void pause() {
        game.pause();
        //отображение окна паузы
        requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.init();
        gameScore = game.getScore();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        if (game != null) {
            game.paint(g, this);
        }
    }

    public int getScore() {
        return gameScore;
    }

    public int getTimerDelay() {
        return game.getDelay();
    }

    public boolean isPaused() {
        return game.isPaused();
    }
}