package main.java.ru.littlebigbro.GameElements;

import main.java.ru.littlebigbro.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameENGmk1 extends JPanel implements ActionListener, GameElement {
    private final static int FIELD_SIZE_IN_PIXELS = 300;
    private final static int SEGMENT_SIZE_IN_PIXELS = 10;
    private final static int SEGMENTS_COUNT = FIELD_SIZE_IN_PIXELS/SEGMENT_SIZE_IN_PIXELS;
    private Snake snake;
    private int snakeHeadX;
    private int snakeHeadY;
    private Apple greenApple;
    private Apple redApple;
    private Apple yellowApple;

    final private int delay = 300;
    private Timer timer;
    private final String RIGHT = "right";
    private final String LEFT = "left";
    private final String UP = "up";
    private final String DOWN = "down";
    private final String STOP = "stop";
    private String currentDirection = RIGHT;
    private boolean gamePaused = false;
    private int gameScore;
    public boolean inGame = true;

    private Point gameFieldPoint;
    private ArrayList<Point> gameFieldPointsList = new ArrayList<Point>();
    private int pointCounter = 0;
    private int steps = 0;

    public GameENGmk1(){
        gameScore = 0;
        setPreferredSize(new Dimension(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS));
        setBackground(Color.BLACK);
        loadGameObjects();
        timer = new Timer(delay,this);
        timer.start();
        addKeyListener(new FieldKeyListener());
        requestFocus();
    }

    public void restart(){
        currentDirection = RIGHT;
        gamePaused = false;
        inGame = true;
        gameFieldPointsList = new ArrayList<Point>();
        pointCounter = 0;
        steps = 0;
        setPreferredSize(new Dimension(FIELD_SIZE_IN_PIXELS, FIELD_SIZE_IN_PIXELS));
        setBackground(Color.BLACK);
        loadGameObjects();
        for (int i = 0; i < SEGMENTS_COUNT; i++){
            for (int j = 0; j < SEGMENTS_COUNT; j++){
                gameFieldPoint = new Point();
                gameFieldPoint.setLocation(i * 10,j * 10);
                gameFieldPointsList.add(pointCounter,gameFieldPoint);
                pointCounter++;
            }
        }
        timer.stop();
        timer = new Timer(delay,this);
        timer.start();
        gameScore = 0;
        addKeyListener(new FieldKeyListener());
        requestFocus();
        setFocusable(true);
    }

    public int getScore(){
        return gameScore;
    }

    public int getTimerDelay(){
        return delay;
    }

    private void loadGameObjects(){
        snake = new Snake();
        snake.setDirection(currentDirection);
        snake.draw();

        greenApple = new Apple(ImagePath.GREEN_APPLE.getPath(), 10, 50);
        //Может наложиться на другие яблоки
        greenApple.create(Utils.getRandomPoint(SEGMENTS_COUNT, SEGMENTS_COUNT), snake.getPoints());

        //координаты яблока по-умолчанию [0,0], оно создается но его не видно на игровом поле
        redApple = new Apple(ImagePath.RED_APPLE.getPath(), 100, 10);
        //redApple.create(new Point(-10, 0), null);
        redApple.create(new Point(50, 20), snake.getPoints());

        yellowApple = new Apple(ImagePath.YELLOW_APPLE.getPath(), -50, 20);
//        yellowApple.create(new Point(-20, 0), null);
        yellowApple.create(new Point(70, 10), snake.getPoints());
    }

    private int setChanceCompareSnakeSize(Apple apple){
        int chance = 0;
        if (snake.getSnakeSize() > 5 && snake.getSnakeSize() < 10){
            if (apple.equals(redApple))
                chance = 20;
            if (apple.equals(yellowApple))
                chance = 100;
        }
        if (snake.getSnakeSize() >= 10 && snake.getSnakeSize() < 15){
            if (apple.equals(redApple))
                chance = 40;
            if (apple.equals(yellowApple))
                chance = 80;
        }
        if (snake.getSnakeSize() >= 15 && snake.getSnakeSize() < 20){
            if (apple.equals(redApple))
                chance = 60;
            if (apple.equals(yellowApple))
                chance = 60;
        }
        if (snake.getSnakeSize() >= 20 && snake.getSnakeSize() < 25){
            if (apple.equals(redApple))
                chance = 80;
            if (apple.equals(yellowApple))
                chance = 40;
        }
        if (snake.getSnakeSize() >= 25){
            if (apple.equals(redApple))
                chance = 100;
            if (apple.equals(yellowApple))
                chance = 20;
        }
        return chance;
    }

    private int calculateAppleTimeOfExistance(Snake snake, Apple apple, int factor){
        int dX = Math.abs(snake.getPointX(0) - apple.getCoordinates().getX());
        int dY = Math.abs(snake.getPointY(0) - apple.getCoordinates().getY());
        return factor * ( dX + dY) / 10;
    }

    private void checkApple(){
        double snakeSize = snake.getSnakeSize();
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
        for (int i = snake.getSnakeSize(); i > 0; i--) {
            if (i > 2 && snakeHeadX == snake.getPointX(i-1) && snakeHeadY == snake.getPointY(i-1)) {
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

        GraphicEngine graphicEngine = new GraphicEngine(g,this);
        Cell cell = new Cell();
        cell.setImage(ImagePath.FIELD_CELL.getPath());
        super.paintComponent(graphicEngine.drawFieldWithCells(cell, FIELD_SIZE_IN_PIXELS,FIELD_SIZE_IN_PIXELS));
/*        if (inGame){
            if (snake.getDirection().equals(STOP)){
                String str = "Pause";
                Font f = new Font("Arial",Font.BOLD, 20);
                FontMetrics fontMetrics = this.getFontMetrics(f);
                int strWidth = fontMetrics.stringWidth(str);
                g.setColor(Color.white);
                g.setFont(f);
                g.drawString(str, (FIELD_SIZE_IN_PIXELS - strWidth)/2,(FIELD_SIZE_IN_PIXELS + f.getSize())/2);
            } else {

                graphicEngine.drawApple(greenApple);
                if (redApple.isExist()){
                    graphicEngine.drawApple(redApple);
                }
                if (yellowApple.isExist()){
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
            String str = "Game Over";
            Font f = new Font("Arial",Font.BOLD, 20);
            FontMetrics fontMetrics = this.getFontMetrics(f);
            int strWidth = fontMetrics.stringWidth(str);
            g.setColor(Color.orange);
            g.setFont(f);
            g.drawString(str, (FIELD_SIZE_IN_PIXELS - strWidth)/2,(FIELD_SIZE_IN_PIXELS + f.getSize())/2);
        }*/
    }

    public void gamePause(){
        if (gamePaused){
            snake.setDirection(currentDirection);
            gamePaused = false;
        } else{
            snake.setDirection(STOP);
            gamePaused = true;
        }
    }
    public boolean isPaused(){
        return gamePaused;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!snake.getDirection().equals(STOP)){
            snake.move();
            snakeHeadX = snake.getPointX(0);
            snakeHeadY = snake.getPointY(0);
            checkApple();
            if ((snake.getSnakeSize() >= 8 && snake.getSnakeSize() < 13) || gameScore >= 100) {
                timer.setDelay((int) (delay * 0.9)); //360
            }
            if (snake.getSnakeSize() >= 13 && snake.getSnakeSize() < 18 || gameScore >= 300) {
                timer.setDelay((int) (delay * 0.7));//280
            }
            if (snake.getSnakeSize() >= 18 && snake.getSnakeSize() < 23  || gameScore >= 500) {
                timer.setDelay((int) (delay * 0.5));//200
            }
            if (snake.getSnakeSize() >= 23 && snake.getSnakeSize() < 28 || gameScore >= 700) {
                timer.setDelay((int) (delay * 0.4));//150
            }
            if (snake.getSnakeSize() >= 28 && snake.getSnakeSize() < 33 || gameScore >= 950) {
                timer.setDelay((int) (delay * 0.25));//100
            }
            if (snake.getSnakeSize() >= 33 && snake.getSnakeSize() < 38 || gameScore >= 1000) {
                timer.setDelay((int) (delay * 0.1875));//75
            }
            if (snake.getSnakeSize() >= 38  || gameScore >= 1200) {
                timer.setDelay((int) (delay * 0.125));//50
            }
            checkCollisions();
        }
        repaint();
    }

    @Override
    public void create(Point point, ArrayList<Point> restrictionList) {

    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (snake.getMoved() && !gamePaused){
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
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                gamePause();
            }
        }
    }
}