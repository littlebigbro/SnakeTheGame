import jdk.internal.cmm.SystemResourcePressureImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameField extends JPanel implements ActionListener {
    private final static int SIZE = 300;
    private Snake snake;
    private int snakeHeadX;
    private int snakeHeadY;
    private Apples greenApple;
    private Apples redApple;
    private Apples yellowApple;
    private Image segmentIm;
    private Image snakeHeadIm;
    private Image greenAppleIm,redAppleIm,yellowAppleIm;
    private Image gameFieldIM;
    private ImageIcon gameFieldIcon;

    private int delay = 400;
    private Timer timer;
    private final String RIGHT = "right";
    private final String LEFT = "left";
    private final String UP = "up";
    private final String DOWN = "down";
    private final String STOP = "stop";
    private String currentDirection = RIGHT;
    private boolean escPressed = false;
    private int gameScore;
    private boolean inGame = true;
    private GameCube redCube;
    private GameCube yellowCube;
    private Point gameFieldPoint;
    private ArrayList<Point> gameFieldPointsList = new ArrayList<Point>();
    private int pointCounter = 0;
    private int steps = 0;

    public GameField(){
        setPreferredSize(new Dimension(SIZE,SIZE));
        setBackground(Color.BLACK);
        loadGameObjects();
        for (int i = 0; i < 36; i++){
            for (int j = 0; j < 36; j++){
                gameFieldPoint = new Point();
                gameFieldPoint.setLocation(i * 10,j * 10);
                gameFieldPointsList.add(pointCounter,gameFieldPoint);
                pointCounter++;
            }
        }
        timer = new Timer(delay,this);
        timer.start();
        gameScore = 0;
        addKeyListener(new FieldKeyListener());
        requestFocus();
        setFocusable(true);
    }

    private void loadGameObjects(){
        snake = new Snake();
        snake.setDirection(currentDirection);
        snake.draw();

        greenApple = new Apples();
        greenApple.create(SIZE,SIZE,snake.getPoints()); // может наложиться на другие яблоки
        greenApple.setScore(10);
        greenApple.appleIcon = new ImageIcon("pics/greenapple.png");

        redApple = new Apples();
        redApple.setScore(100);
        redApple.appleIcon = new ImageIcon("pics/redapple.png");

        yellowApple = new Apples();
        yellowApple.setScore(-50);
        yellowApple.appleIcon = new ImageIcon("pics/yellowapple.png");

        redCube = new GameCube();
        yellowCube = new GameCube();

        gameFieldIcon = new ImageIcon("pics/field3.png");

        loadImages();
    }

    private void loadImages(){
        greenAppleIm = greenApple.icon();
        redAppleIm = redApple.icon();
        yellowAppleIm = yellowApple.icon();
        segmentIm = snake.icon();
        snakeHeadIm = snake.head();
        gameFieldIM = gameFieldIcon.getImage();;
    }
//может сделать стримом?
    public String getGameScore(){
        return Integer.toString(gameScore);
    }

    private int setChanceCompareSnakeSize(Apples apple){
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

    private void checkApple(){
        double snakeSize = snake.getSnakeSize();
        if (snakeHeadX == greenApple.getX() && snakeHeadY == greenApple.getY()){
            snakeSize++;
            snake.setSnakeSize((int)snakeSize);
            gameScore += greenApple.getScore();
            greenApple.delete();
            greenApple.create(SIZE, SIZE, snake.getPoints());
        }
        if (steps % 5 == 0) {
            redCube.setChance(setChanceCompareSnakeSize(redApple));
            if (snake.getSnakeSize() % 2 == 0 && !redApple.isExist()) {
                if (redCube.shot() > 4) {
                    redApple.create(SIZE, SIZE, snake.getPoints());
                    redCube.setDoubleChanceThrow(false);
                } else {
                    if (redCube.getDoubleChanceThrow() && redCube.doubleChanceThrow() >= 8) { //при втором броске вероятность выпадения красного яблока увеличивается
                        redApple.create(SIZE, SIZE, snake.getPoints());
                        redCube.setDoubleChanceThrow(false);
                    } else {
                        redCube.setDoubleChanceThrow(true);
                    }
                }
            }
//            переписать
            yellowCube.setChance(setChanceCompareSnakeSize(yellowApple));
            if (snake.getSnakeSize() % 2 == 1 && !yellowApple.isExist()) {
                yellowApple.create(SIZE, SIZE, snake.getPoints());
            }
            steps = 0;
        }
        if (snakeHeadX == redApple.getX() && snakeHeadY == redApple.getY()){
            if (snakeSize / 2 >= 3){
                snakeSize = snakeSize/2;
            }
            snake.setSnakeSize((int) Math.ceil(snakeSize));
            gameScore += redApple.getScore();
            redApple.delete();
        }
        if (snakeHeadX == yellowApple.getX() && snakeHeadY == yellowApple.getY()){
            snakeSize = snakeSize * 2;
            snake.setSnakeSize((int)snakeSize);
            gameScore += yellowApple.getScore();
            yellowApple.delete();
        }
        steps++;
    }

    private void checkCollisions(){
        for (int i = snake.getSnakeSize(); i > 0; i--){
            if (i > 2 && snakeHeadX == snake.getPointX(i-1) && snakeHeadY == snake.getPointY(i-1)) {
                inGame = false;
            }
        }
        if ((snakeHeadX == SIZE && snake.getDirection() == RIGHT) ||
            (snakeHeadX < 0 && snake.getDirection() == LEFT)||
            (snakeHeadY < 0 && snake.getDirection() == UP) ||
            (snakeHeadY == SIZE && snake.getDirection() == DOWN)){
            inGame = false;
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame){
            for (int i = 0; i < pointCounter; i++) {
                g.drawImage(gameFieldIM,(int)gameFieldPointsList.get(i).getX(), (int)gameFieldPointsList.get(i).getY(), this);
            }
            if (snake.getDirection().equals(STOP)){
                String str = "Pause";
                Font f = new Font("Arial",Font.BOLD, 14);
                g.setColor(Color.white);
                g.setFont(f);
                g.drawString(str, SIZE/2-50,SIZE/2);
            } else {
                g.drawImage(greenAppleIm, greenApple.getX(), greenApple.getY(), this);
                if (redApple.isExist()){
                    g.drawImage(redAppleIm, redApple.getX(), redApple.getY(), this);
                }
                if (yellowApple.isExist()){
                    g.drawImage(yellowAppleIm, yellowApple.getX(), yellowApple.getY(), this);
                }
                g.drawImage(snakeHeadIm, snake.getPointX(0), snake.getPointY(0), this);
                for (int i = 1; i < snake.getSnakeSize(); i++) {
                    g.drawImage(segmentIm, snake.getPointX(i), snake.getPointY(i), this);
                }
            }
        } else {
            String str = "Game Over With final score: ";
            String score = Integer.toString(gameScore);
            Font f = new Font("Arial",Font.BOLD, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str + score, SIZE/2-100,SIZE/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!snake.getDirection().equals(STOP)){
            snake.move();
            snakeHeadX= snake.getPointX(0);
            snakeHeadY= snake.getPointY(0);
            checkApple();
            if (snake.getSnakeSize() >= 8 && snake.getSnakeSize() < 13) {
                timer.setDelay((int) (delay * 0.9)); //360
            }
            if (snake.getSnakeSize() >= 13 && snake.getSnakeSize() < 18) {
                timer.setDelay((int) (delay * 0.7));//280
            }
            if (snake.getSnakeSize() >= 18 && snake.getSnakeSize() < 23) {
                timer.setDelay((int) (delay * 0.5));//200
            }
            if (snake.getSnakeSize() >= 23 && snake.getSnakeSize() < 28) {
                timer.setDelay((int) (delay * 0.4));//150
            }
            if (snake.getSnakeSize() >= 28 && snake.getSnakeSize() < 33) {
                timer.setDelay((int) (delay * 0.25));//100
            }
            if (snake.getSnakeSize() >= 33 && snake.getSnakeSize() < 38) {
                timer.setDelay((int) (delay * 0.1875));//75
            }
            if (snake.getSnakeSize() >= 38) {
                timer.setDelay((int) (delay * 0.125));//50
            }
            checkCollisions();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (snake.getMoved() && !escPressed){
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
                if (escPressed){
                    snake.setDirection(currentDirection);
                    escPressed = false;
                } else{
                    snake.setDirection(STOP);
                    escPressed = true;
                }
            }
        }
    }
}