package main.java.ru.littlebigbro.GUI;

import main.java.ru.littlebigbro.GameElements.Snake;
import main.java.ru.littlebigbro.Interfaces.GameConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FieldKeyListener extends KeyAdapter implements GameConstants {

    private boolean gamePaused;
    private final Snake snake;
    private String currentDirection;

    public FieldKeyListener(Snake snake, Boolean gamePaused) {
        this.snake = snake;
        this.gamePaused = gamePaused;
        currentDirection = snake.getDirection();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (snake.getMoved() && !gamePaused) {
            if (keyCode == KeyEvent.VK_LEFT && !currentDirection.equals(RIGHT)) {
                snake.setDirection(LEFT);
            }
            if (keyCode == KeyEvent.VK_RIGHT && !currentDirection.equals(LEFT)) {
                snake.setDirection(RIGHT);
            }
            if (keyCode == KeyEvent.VK_UP && !currentDirection.equals(DOWN)) {
                snake.setDirection(UP);
            }
            if (keyCode == KeyEvent.VK_DOWN && !currentDirection.equals(UP)) {
                snake.setDirection(DOWN);
            }
            snake.setMoved(false);
            currentDirection = snake.getDirection();
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePause();
        }
    }

    private void gamePause() {
        if (gamePaused) {
            snake.setDirection(currentDirection);
            gamePaused = false;
        } else {
            snake.setDirection(STOP);
            gamePaused = true;
        }
    }
}
