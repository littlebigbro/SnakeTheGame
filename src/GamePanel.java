import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private final static int SIZE = 360;
    private Snake snake;
    private int snakeHeadX;
    private int snakeHeadY;
    private Apples greenApple;
    private Apples redApple;
    private Apples yellowApple;
    private Image segmentIm;
    private Image snakeHeadIm;
    private Image greenAppleIm,redAppleIm,yellowAppleIm;
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

    public GamePanel(){
        setPreferredSize(new Dimension(SIZE,SIZE));
        setBackground(Color.BLACK);
        loadGameObjects();
        timer = new Timer(350,this);
        timer.start();
        gameScore = 0;
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    private void loadGameObjects(){
        snake = new Snake();
        snake.setDirection(currentDirection);
        snake.draw();

        greenApple = new Apples();
        greenApple.create(snake.getPoints()); // может наложиться на другие яблоки
        greenApple.setScore(10);
        greenApple.appleIcon = new ImageIcon("pics/greenapple.png");

        redApple = new Apples();
        redApple.setScore(100);
        redApple.appleIcon = new ImageIcon("pics/redapple.png");

        yellowApple = new Apples();
        yellowApple.setScore(-50);
        yellowApple.appleIcon = new ImageIcon("pics/yellowapple.png");
        loadImages();
    }

    private void loadImages(){
        greenAppleIm = greenApple.icon();
        redAppleIm = redApple.icon();
        yellowAppleIm = yellowApple.icon();
        segmentIm = snake.icon();
        snakeHeadIm = snake.head();
    }

    public int getGameScore(){
        return gameScore;
    }

    private void checkApple(){
        double snakeSize = snake.getSnakeSize();
        if (snakeHeadX == greenApple.getX() && snakeHeadY == greenApple.getY()){
            snakeSize++;
            snake.setSnakeSize((int)snakeSize);
            gameScore += greenApple.getScore();
            greenApple.create(snake.getPoints());
            if (snakeSize > 5){
                redApple.create(snake.getPoints());
            }
        }
        if (snakeHeadX == redApple.getX() && snakeHeadY == redApple.getY()){
            if (snakeSize / 2 >= 3){
                snakeSize = snakeSize/2;
            }
            snake.setSnakeSize((int) Math.ceil(snakeSize));
            gameScore += redApple.getScore();
        }
        if (snakeHeadX == yellowApple.getX() && snakeHeadY == yellowApple.getY()){
            snakeSize++;
            snake.setSnakeSize((int)snakeSize);
            gameScore += yellowApple.getScore();
        }
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
            if (snake.getDirection().equals(STOP)){
                String str = "Pause";
                Font f = new Font("Arial",Font.BOLD, 14);
                g.setColor(Color.white);
                g.setFont(f);
                g.drawString(str, SIZE/2-50,SIZE/2);
            } else {
                g.drawImage(greenAppleIm, greenApple.getX(), greenApple.getY(), this);
                if (snake.getSnakeSize() > 5){
                g.drawImage(redAppleIm, redApple.getX(), redApple.getY(), this);
                g.drawImage(yellowAppleIm, yellowApple.getX(), yellowApple.getY(), this); }
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
            switch(snake.getSnakeSize()){
                case 8: timer.setDelay(300);
                    break;
                case 13: timer.setDelay(250);
                    break;
                case 18: timer.setDelay(200);
                    break;
                case 23: timer.setDelay(150);
                    break;
                case 28: timer.setDelay(100);
                    break;
                case 33: timer.setDelay(75);
                    break;
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
