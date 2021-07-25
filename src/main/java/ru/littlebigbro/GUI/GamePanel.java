package main.java.ru.littlebigbro.GUI;

import main.java.ru.littlebigbro.Engines.GraphicEngine;
import main.java.ru.littlebigbro.Enums.ImagePath;
import main.java.ru.littlebigbro.Enums.MovementDirection;
import main.java.ru.littlebigbro.Extra.Point;
import main.java.ru.littlebigbro.Extra.Utils;
import main.java.ru.littlebigbro.GameElements.Apple;
import main.java.ru.littlebigbro.GameElements.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private static final int FIELD_SIZE_IN_PIXELS = 300;
    private static final int SEGMENT_SIZE_IN_PIXELS = 10;
    private static final int SEGMENTS_COUNT = FIELD_SIZE_IN_PIXELS / SEGMENT_SIZE_IN_PIXELS;
    private static final String RIGHT = MovementDirection.RIGHT.getDirection();
    private static final String LEFT = MovementDirection.LEFT.getDirection();
    private static final String UP = MovementDirection.UP.getDirection();
    private static final String DOWN = MovementDirection.DOWN.getDirection();
    private static final String STOP = MovementDirection.NO_DIRECTION.getDirection();

    private Snake snake;
    private int snakeHeadX;
    private int snakeHeadY;
    private Apple greenApple;
    private Apple redApple;
    private Apple yellowApple;

    final private int delay = 300;
    private Timer timer;
    private String currentDirection = RIGHT;
    private boolean gamePaused = false;
    private int gameScore;
    public boolean inGame = true;

    private Point gameFieldPoint;
    private ArrayList<Point> gameFieldPointsList = new ArrayList<>();
    private int pointCounter = 0;
    private int steps = 0;

    public GamePanel() {
        gameScore = 0;
        setPreferredSize(new Dimension(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS));
        setBackground(Color.BLACK);
        loadGameObjects();
        timer = new Timer(delay, this);
        timer.start();
        addKeyListener(new FieldKeyListener());
        requestFocus();
    }

    public void restart() {
        currentDirection = RIGHT;
        gamePaused = false;
        inGame = true;
        gameFieldPointsList = new ArrayList<>();
        pointCounter = 0;
        steps = 0;
        setPreferredSize(new Dimension(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS));
        setBackground(Color.BLACK);
        loadGameObjects();
        for (int i = 0; i < SEGMENTS_COUNT; i++) {
            for (int j = 0; j < SEGMENTS_COUNT; j++) {
                gameFieldPoint = new Point();
                gameFieldPoint.setLocation(i * 10, j * 10);
                gameFieldPointsList.add(pointCounter, gameFieldPoint);
                pointCounter++;
            }
        }
        timer.stop();
        timer = new Timer(delay, this);
        timer.start();
        gameScore = 0;
        addKeyListener(new FieldKeyListener());
        requestFocus();
        setFocusable(true);
    }

    public int getScore() {
        return gameScore;
    }

    public int getTimerDelay() {
        return delay;
    }

    private void loadGameObjects() {
        snake = new Snake();
        snake.setDirection(currentDirection);
        snake.draw();

        greenApple = new Apple(ImagePath.GREEN_APPLE.getPath(), 10, 50);
        //Может наложиться на другие яблоки
        greenApple.create(Utils.getRandomPoint(SEGMENTS_COUNT, SEGMENTS_COUNT), (ArrayList<Point>) snake.getPoints());

        //координаты яблока по-умолчанию [0,0], оно создается но его не видно на игровом поле
        redApple = new Apple(ImagePath.RED_APPLE.getPath(), 100, 10);
        //redApple.create(new Point(-10, 0), null);
        redApple.create(new Point(50, 20), (ArrayList<Point>) snake.getPoints());

        yellowApple = new Apple(ImagePath.YELLOW_APPLE.getPath(), -50, 20);
//        yellowApple.create(new Point(-20, 0), null);
        yellowApple.create(new Point(70, 10), (ArrayList<Point>) snake.getPoints());
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

//    private int calculateAppleTimeOfExistance(Snake snake, Apple apple, int factor){
//        int dX = Math.abs(snake.getPointX(0) - apple.getCoordinates().getX());
//        int dY = Math.abs(snake.getPointY(0) - apple.getCoordinates().getY());
//        return factor * ( dX + dY) / 10;
//    }

    private void checkApple() {
        double snakeSize = snake.getSize();
//        if (snakeHeadX == greenApple.getX() && snakeHeadY == greenApple.getY()){
//            snakeSize++;
//            snake.setSnakeSize((int)snakeSize);
//            gameScore += greenApple.getScore();
//            greenApple.remove();
//            greenApple.create(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS, snake.getPoints());
//        }
//        if (steps % 5 == 0 && steps != 0 && snake.getSnakeSize() > 4) {
//            GameCube.shot(setChanceCompareSnakeSize(redApple));
//            if (snake.getSnakeSize() % 2 == 0 && !redApple.isExist()) {
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
//            snake.setSnakeSize((int) Math.ceil(snakeSize));
//            gameScore += redApple.getDeafaultScore();
//            redApple.setTimeOfExist(0);
//            redApple.delete();
//        }
//        if (snakeHeadX == yellowApple.getX() && snakeHeadY == yellowApple.getY()){
//            snakeSize = snakeSize * 2;
//            snake.setSnakeSize((int)snakeSize);
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
        steps++;
    }

    private void checkCollisions() {
        for (int i = snake.getSize(); i > 0; i--) {
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

    protected void paintComponent(Graphics g) {
        GraphicEngine graphicEngine = new GraphicEngine(g, this);
        //super.paintComponent(g);
        graphicEngine.drawFieldWithCells(ImagePath.FIELD_CELL.getPath(), FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS);
        if (inGame) {
            if (snake.getDirection().equals(STOP)) {
                String message = "Pause";
                graphicEngine.setStringColor(Color.white);
                graphicEngine.drawMessageString(message, this, FIELD_SIZE_IN_PIXELS);
            } else {
                graphicEngine.drawApple(greenApple);
                if (redApple.isExist()) {
                    graphicEngine.drawApple(redApple);
                }
                if (yellowApple.isExist()) {
                    graphicEngine.drawApple(yellowApple);
                }
//                g.drawImage(snakeHeadIm, snake.getPointX(0), snake.getPointY(0), this);
//                for (int i = 1; i < snake.getSnakeSize(); i++) {
//                    g.drawImage(segmentIm, snake.getPointX(i), snake.getPointY(i), this);
//                }
            }
        } else {
            timer.stop();
            snake.setDirection(STOP);
            String message = "Game Over";
            graphicEngine.setStringColor(Color.orange);
            graphicEngine.drawMessageString(message, this, FIELD_SIZE_IN_PIXELS);

        }
    }

    public void gamePause() {
        if (gamePaused) {
            snake.setDirection(currentDirection);
            gamePaused = false;
        } else {
            snake.setDirection(STOP);
            gamePaused = true;
        }
    }

    public boolean isPaused() {
        return gamePaused;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!snake.getDirection().equals(STOP)) {
            snake.move();
            snakeHeadX = snake.getPointX(0);
            snakeHeadY = snake.getPointY(0);
            checkApple();
            if ((snake.getSize() >= 8 && snake.getSize() < 13) || gameScore >= 100) {
                timer.setDelay((int) (delay * 0.9)); //360
            }
            if (snake.getSize() >= 13 && snake.getSize() < 18 || gameScore >= 300) {
                timer.setDelay((int) (delay * 0.7));//280
            }
            if (snake.getSize() >= 18 && snake.getSize() < 23 || gameScore >= 500) {
                timer.setDelay((int) (delay * 0.5));//200
            }
            if (snake.getSize() >= 23 && snake.getSize() < 28 || gameScore >= 700) {
                timer.setDelay((int) (delay * 0.4));//150
            }
            if (snake.getSize() >= 28 && snake.getSize() < 33 || gameScore >= 950) {
                timer.setDelay((int) (delay * 0.25));//100
            }
            if (snake.getSize() >= 33 && snake.getSize() < 38 || gameScore >= 1000) {
                timer.setDelay((int) (delay * 0.1875));//75
            }
            if (snake.getSize() >= 38 || gameScore >= 1200) {
                timer.setDelay((int) (delay * 0.125));//50
            }
            checkCollisions();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (snake.getMoved() && !gamePaused) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT && !currentDirection.equals(RIGHT)) {
                    snake.setDirection(LEFT);
                    snake.setMoved(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && !currentDirection.equals(LEFT)) {
                    snake.setDirection(RIGHT);
                    snake.setMoved(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP && !currentDirection.equals(DOWN)) {
                    snake.setDirection(UP);
                    snake.setMoved(false);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && !currentDirection.equals(UP)) {
                    snake.setDirection(DOWN);
                    snake.setMoved(false);
                }
                currentDirection = snake.getDirection();
            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                gamePause();
            }
        }
    }
}