package main.java.ru.littlebigbro.Engines;

import main.java.ru.littlebigbro.Enums.ImagePath;
import main.java.ru.littlebigbro.Extra.Point;
import main.java.ru.littlebigbro.GUI.FieldKeyListener;
import main.java.ru.littlebigbro.GUI.GamePanel;
import main.java.ru.littlebigbro.GameElements.Apple;
import main.java.ru.littlebigbro.GameElements.Snake;
import main.java.ru.littlebigbro.Interfaces.Engine;
import main.java.ru.littlebigbro.Interfaces.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

//Класс содержащий в себе игровую логику
public class GameEngine extends Component implements Engine, GameConstants {
    private static final int delay = 300;
    private int snakeHeadX;
    private int snakeHeadY;
    private boolean isPaused = false;
    private boolean inGame = true;
    private Timer timer;
    private Snake snake;
    private Apple greenApple;
    private Apple redApple;
    private Apple yellowApple;
    private GamePanel gamePanel;
    private int score = 0;

    public GameEngine(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void start() {
        loadGameObjects();
        timer = new Timer(delay, gamePanel);
        timer.start();
    }

    private void loadGameObjects() {
        snake = new Snake();
        snake.create();

        greenApple = new Apple(ImagePath.GREEN_APPLE.getPath(), 10, 50);
        greenApple.create();

        //координаты яблока по-умолчанию [0,0], оно создается но его не видно на игровом поле
        redApple = new Apple(ImagePath.RED_APPLE.getPath(), 100, 10);
//        redApple.create(new Point(-10, 0));
        redApple.create(new Point(50, 20));

        yellowApple = new Apple(ImagePath.YELLOW_APPLE.getPath(), -50, 20);
//        yellowApple.create(new Point(-20, 0));
        yellowApple.create(new Point(70, 10));
    }

    public void pause() {
//todo
    }

    private void checkApple() {
        //TODO: create apple not in snake :)
        double snakeSize = snake.getSize();
        Point greenApplePoint = greenApple.getCoordinates().get(0);
        if (snakeHeadX == greenApplePoint.getX() && snakeHeadY == greenApplePoint.getY()) {
            snakeSize++;
            snake.setSize((int) snakeSize);
            score += greenApple.getScore();
            greenApple.remove();
            greenApple.create();
        }
//        if (steps % 5 == 0 && steps != 0 && snake.getSize() > 4) {
//            GameCube.shot(setChanceCompareSnakeSize(redApple));
//            if (snake.getSize() % 2 == 0 && !redApple.isExist()) {
//                if (redCube.shot() > 4) {
//                    redApple.create(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS, snake.getPoints());
//                    redApple.setTimeOfExist(calculateAppleTimeOfExistance(snake, redApple, 3)); // задаю время существования
//                    redCube.setDoubleChanceThrow(false);
//                } else {
//                    //при втором броске вероятность выпадения красного яблока увеличивается
//                    if (redCube.getDoubleChanceThrow() && redCube.doubleChanceThrow() <= 6) {
//                        redApple.create(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS, snake.getPoints());
//                        redApple.setTimeOfExist(calculateAppleTimeOfExistance(snake, redApple, 3));
//                        redCube.setDoubleChanceThrow(false);
//                    } else {
//                        redCube.setDoubleChanceThrow(true);
//                    }
//                }
//            }
//            yellowCube.setChance(setChanceCompareSnakeSize(yellowApple));
//            if (snake.getSnakeSize() % 2 == 1 && !yellowApple.isExist()) {
//                if (yellowCube.shot() >= 5) {
//                    yellowApple.create(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS, snake.getPoints());
//                    yellowApple.setTimeOfExist(calculateAppleTimeOfExistance(snake, redApple, 6));
//                }
//            }
//            steps = 0;
//        }
//        if (snakeHeadX == redApple.getX() && snakeHeadY == redApple.getY()){
//            if (snakeSize / 2 >= 3){
//                snakeSize = snakeSize/2;
//            }
//            snake.setSize((int) Math.ceil(snakeSize));
//            gameScore += redApple.getDeafaultScore();
//            redApple.setTimeOfExist(0);
//            redApple.delete();
//        }
//        if (snakeHeadX == yellowApple.getX() && snakeHeadY == yellowApple.getY()){
//            snakeSize = snakeSize * 2;
//            snake.setSize((int)snakeSize);
//            gameScore += yellowApple.getDeafaultScore();
//            yellowApple.delete();
//        }
//        if (redApple.isExist()){
//            if (redApple.getTimeOfExist() > 0){
//                int tempTime = redApple.getTimeOfExist();
//                redApple.setTimeOfExist(--tempTime);
//            }
//            if (redApple.getTimeOfExist() <= 0){
//                redApple.delete();
//            }
//        }
//        if (yellowApple.isExist()){
//            if (yellowApple.getTimeOfExist() > 0){
//                int tempTime = yellowApple.getTimeOfExist();
//                yellowApple.setTimeOfExist(--tempTime);
//            }
//            if (yellowApple.getTimeOfExist() <= 0){
//                yellowApple.delete();
//            }
//        }
//        steps++;
    }

    private int setChanceCompareSnakeSize(Apple apple) {
        int chance = 0;
        if (snake.getSize() > 5 && snake.getSize() < 10) {
            if (apple.equals(redApple))
                chance = 20;
            if (apple.equals(yellowApple))
                chance = 100;
        }
        if (snake.getSize() >= 10 && snake.getSize() < 15) {
            if (apple.equals(redApple))
                chance = 40;
            if (apple.equals(yellowApple))
                chance = 80;
        }
        if (snake.getSize() >= 15 && snake.getSize() < 20) {
            if (apple.equals(redApple))
                chance = 60;
            if (apple.equals(yellowApple))
                chance = 60;
        }
        if (snake.getSize() >= 20 && snake.getSize() < 25) {
            if (apple.equals(redApple))
                chance = 80;
            if (apple.equals(yellowApple))
                chance = 40;
        }
        if (snake.getSize() >= 25) {
            if (apple.equals(redApple))
                chance = 100;
            if (apple.equals(yellowApple))
                chance = 20;
        }
        return chance;
    }

    private int calculateAppleTimeOfExistance(Snake snake, Apple apple, int factor) {
        Point applePoint = apple.getCoordinates().get(0);
        int dX = Math.abs(snake.getPointX(0) - applePoint.getX());
        int dY = Math.abs(snake.getPointY(0) - applePoint.getY());
        return factor * (dX + dY) / 10;
    }

    private void checkCollisions() {
        for (int i = snake.getCoordinates().size() - 1; i > 0; i--) {
            if (i > 2 && snakeHeadX == snake.getPointX(i - 1) && snakeHeadY == snake.getPointY(i - 1)) {
                inGame = false;
            }
        }
        if ((snakeHeadX == FIELD_SIZE_IN_PIXELS && snake.getDirection().equals(RIGHT)) ||
                (snakeHeadX < 0 && snake.getDirection().equals(LEFT)) ||
                (snakeHeadY < 0 && snake.getDirection().equals(UP)) ||
                (snakeHeadY == FIELD_SIZE_IN_PIXELS && snake.getDirection().equals(DOWN))) {
            inGame = false;
        }
    }

    private void setTimerDelay(int snakeSize, int score) {
        int newDelay = delay;
        if ((snakeSize >= 8 && snakeSize < 13) || score >= 100) {
            newDelay = (int) (delay * 0.9); //360
        }
        if (snake.getSize() >= 13 && snake.getSize() < 18 || score >= 300) {
            newDelay = (int) (delay * 0.7); //280
        }
        if (snake.getSize() >= 18 && snake.getSize() < 23 || score >= 500) {
            newDelay = (int) (delay * 0.5); //200
        }
        if (snake.getSize() >= 23 && snake.getSize() < 28 || score >= 700) {
            newDelay = (int) (delay * 0.4); //150
        }
        if (snake.getSize() >= 28 && snake.getSize() < 33 || score >= 950) {
            newDelay = (int) (delay * 0.25); //100
        }
        if (snake.getSize() >= 33 && snake.getSize() < 38 || score >= 1000) {
            newDelay = (int) (delay * 0.1875); //75
        }
        if (snake.getSize() >= 38 || score >= 1200) {
            newDelay = (int) (delay * 0.125); //50
        }
        timer.setDelay(newDelay);
    }

    public int getScore() {
        return score;
    }

    public int getDelay() {
        return delay;
    }

    public void paint(Graphics g, GamePanel gamePanel) {
        GraphicEngine graphicEngine = new GraphicEngine(g, gamePanel);
        graphicEngine.drawFieldWithCells(ImagePath.FIELD_CELL.getPath(), FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS);
        if (inGame) {
            if (snake.getDirection().equals(STOP)) {
                String message = "Pause";
                graphicEngine.setStringColor(Color.white);
                graphicEngine.drawMessageString(message, gamePanel, FIELD_SIZE_IN_PIXELS);
            } else {
                graphicEngine.drawApple(greenApple);
                if (redApple.isExist()) {
                    graphicEngine.drawApple(redApple);
                }
                if (yellowApple.isExist()) {
                    graphicEngine.drawApple(yellowApple);
                }
                graphicEngine.drawSnake(snake);
            }
        } else {
            timer.stop();
            snake.setDirection(STOP);
            String message = "Game Over";
            graphicEngine.setStringColor(Color.orange);
            graphicEngine.drawMessageString(message, gamePanel, FIELD_SIZE_IN_PIXELS);
        }
    }

    public KeyAdapter getFieldKeyListener() {
        return new FieldKeyListener(snake, isPaused);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void init() {
        if (!snake.getDirection().equals(STOP)) {
            snake.move();
            snakeHeadX = snake.getPointX(0);
            snakeHeadY = snake.getPointY(0);
            checkApple();
            setTimerDelay(snake.getSize(), score);
            checkCollisions();
        }
    }
}
