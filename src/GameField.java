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
    private GameCube redCube;
    private GameCube yellowCube;
    private Point gameFieldPoint;
    private ArrayList<Point> gameFieldPointsList = new ArrayList<Point>();
    private int pointCounter = 0;
    private int steps = 0;

    public GameField(){
        gameScore = 0;
        setPreferredSize(new Dimension(SIZE,SIZE));
        setBackground(Color.BLACK);
        loadGameObjects();
        for (int i = 0; i < SIZE/10; i++){
            for (int j = 0; j < SIZE/10; j++){
                gameFieldPoint = new Point();
                gameFieldPoint.setLocation(i * 10,j * 10);
                gameFieldPointsList.add(pointCounter,gameFieldPoint);
                pointCounter++;
            }
        }
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
        setPreferredSize(new Dimension(SIZE,SIZE));
        setBackground(Color.BLACK);
        loadGameObjects();
        for (int i = 0; i < SIZE/10; i++){
            for (int j = 0; j < SIZE/10; j++){
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

        greenApple = new Apples();
        greenApple.create(SIZE,SIZE,snake.getPoints()); // может наложиться на другие яблоки
        greenApple.setScore(10);
        greenApple.appleIcon = new ImageIcon("pics/greenapple.png");

        redApple = new Apples();
        redApple.setScore(100);
        redApple.create(-2, 0); //координаты яблока по-умолчанию [0,0], оно создается но его не видно на игровом поле
        redApple.appleIcon = new ImageIcon("pics/redapple.png");

        yellowApple = new Apples();
        yellowApple.create(-1, 0);
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
        gameFieldIM = gameFieldIcon.getImage();
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

    private int calculateAppleTimeOfExistance(Snake snake, Apples apple, int factor){
        int dX = Math.abs(snake.getPointX(0) - apple.getX());
        int dY = Math.abs(snake.getPointY(0) - apple.getY());
        return factor * ( dX + dY) / 10;
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
        if (steps % 5 == 0 && steps != 0 && snake.getSnakeSize() > 4) {
            redCube.setChance(setChanceCompareSnakeSize(redApple));
            if (snake.getSnakeSize() % 2 == 0 && !redApple.isExist()) {
                if (redCube.shot() > 4) {
                    redApple.create(SIZE, SIZE, snake.getPoints());
                    redApple.setTimeOfExist(calculateAppleTimeOfExistance(snake, redApple, 3)); // задаю время существования
                    redCube.setDoubleChanceThrow(false);
                } else {
                    //при втором броске вероятность выпадения красного яблока увеличивается
                    if (redCube.getDoubleChanceThrow() && redCube.doubleChanceThrow() <= 6) {
                        redApple.create(SIZE, SIZE, snake.getPoints());
                        redApple.setTimeOfExist(calculateAppleTimeOfExistance(snake, redApple, 3));
                        redCube.setDoubleChanceThrow(false);
                    } else {
                        redCube.setDoubleChanceThrow(true);
                    }
                }
            }
            yellowCube.setChance(setChanceCompareSnakeSize(yellowApple));
            if (snake.getSnakeSize() % 2 == 1 && !yellowApple.isExist()) {
                if (yellowCube.shot() >= 5) {
                    yellowApple.create(SIZE, SIZE, snake.getPoints());
                    yellowApple.setTimeOfExist(calculateAppleTimeOfExistance(snake, redApple, 6));
                }
            }
            steps = 0;
        }
        if (snakeHeadX == redApple.getX() && snakeHeadY == redApple.getY()){
            if (snakeSize / 2 >= 3){
                snakeSize = snakeSize/2;
            }
            snake.setSnakeSize((int) Math.ceil(snakeSize));
            gameScore += redApple.getScore();
            redApple.setTimeOfExist(0);
            redApple.delete();
        }
        if (snakeHeadX == yellowApple.getX() && snakeHeadY == yellowApple.getY()){
            snakeSize = snakeSize * 2;
            snake.setSnakeSize((int)snakeSize);
            gameScore += yellowApple.getScore();
            yellowApple.delete();
        }
        if (redApple.isExist()){
            if (redApple.getTimeOfExist() > 0){
                int tempTime = redApple.getTimeOfExist();
                redApple.setTimeOfExist(--tempTime);
            }
            if (redApple.getTimeOfExist() <= 0){
                redApple.delete();
            }
        }
        if (yellowApple.isExist()){
            if (yellowApple.getTimeOfExist() > 0){
                int tempTime = yellowApple.getTimeOfExist();
                yellowApple.setTimeOfExist(--tempTime);
            }
            if (yellowApple.getTimeOfExist() <= 0){
                yellowApple.delete();
            }
        }
        steps++;
    }

    private void checkCollisions() {
        for (int i = snake.getSnakeSize(); i > 0; i--) {
            if (i > 2 && snakeHeadX == snake.getPointX(i-1) && snakeHeadY == snake.getPointY(i-1)) {
                inGame = false;
            }
        }
        if ((snakeHeadX == SIZE && snake.getDirection().equals(RIGHT)) ||
            (snakeHeadX < 0 && snake.getDirection().equals(LEFT)) ||
            (snakeHeadY < 0 && snake.getDirection().equals(UP)) ||
            (snakeHeadY == SIZE && snake.getDirection().equals(DOWN))) {
            inGame = false;
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < pointCounter; i++) {
            g.drawImage(gameFieldIM,(int)gameFieldPointsList.get(i).getX(), (int)gameFieldPointsList.get(i).getY(), this);
        }
        if (inGame){
            if (snake.getDirection().equals(STOP)){
                String str = "Pause";
                Font f = new Font("Arial",Font.BOLD, 20);
                FontMetrics fontMetrics = this.getFontMetrics(f);
                int strWidth = fontMetrics.stringWidth(str);
                g.setColor(Color.white);
                g.setFont(f);
                g.drawString(str, (SIZE - strWidth)/2,(SIZE + f.getSize())/2);
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
            timer.stop();
            snake.setDirection(STOP);
            String str = "Game Over";
            Font f = new Font("Arial",Font.BOLD, 20);
            FontMetrics fontMetrics = this.getFontMetrics(f);
            int strWidth = fontMetrics.stringWidth(str);
            g.setColor(Color.orange);
            g.setFont(f);
            g.drawString(str, (SIZE - strWidth)/2,(SIZE + f.getSize())/2);
        }
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