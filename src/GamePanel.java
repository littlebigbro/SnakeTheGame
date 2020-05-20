import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {
    private final static int SIZE = 360;
    private Snake snake = new Snake(); //если сделать публичным то можно наследовать, например для метода который проверяет на столкновения
    private GreenApple greenApple = new GreenApple();
    private Image segmentIm;
    private Image appleIm;
    private Timer timer;
    private final String RIGHT = "right";
    private final String LEFT = "left";
    private final String UP = "up";
    private final String DOWN = "down";
    private final String STOP = "stop";
    private String currentDirection = "right";
    private boolean escPressed = false;

    public GamePanel(){
        setPreferredSize(new Dimension(SIZE,SIZE));
        setBackground(Color.BLACK);
        loadImages();
//        initGame();
        timer = new Timer(250,this);
        timer.start();
        greenApple.create(snake.getPoints());
        snake.setDirection(currentDirection);
        snake.draw();

        addKeyListener(new FieldKeyListener());

        setFocusable(true);
    }

    public void loadImages(){
        appleIm = greenApple.icon();
        segmentIm = snake.icon();
    }

    public void checkApple(){
        if((int) snake.getPoints().get(0).getX() == greenApple.getX() && (int) snake.getPoints().get(0).getY() == greenApple.getY()){
            int snakeSize = snake.getSnakeSize();
            snakeSize++;
            snake.setSnakeSize(snakeSize);
            greenApple.create(snake.getPoints());
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(true){
            g.drawImage(appleIm,greenApple.getX(),greenApple.getY(),this);
            for (int i = 0; i < snake.getSnakeSize(); i++){
                g.drawImage(segmentIm, (int) snake.getPoints().get(i).getX(), (int) snake.getPoints().get(i).getY(),this);
            }
        } else{
            String str = "Game Over";
            Font f = new Font("Arial",Font.BOLD, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, SIZE/2-50,SIZE/2);
        }
        if (snake.getDirection().equals(STOP)){
            String str = "Pause";
            Font f = new Font("Arial",Font.BOLD, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, SIZE/2-50,SIZE/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!snake.getDirection().equals(STOP)){
            checkApple();
            snake.move();
//           // checkCollisions();
        }
        repaint();
    }
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //добавить задержку на передачу равную времени необходимому для ячейки для перемещения
            //мб проверять координаты на изменение если dX dY = 0 то нельзя нажать
            if(e.getKeyCode() == KeyEvent.VK_LEFT && !snake.getDirection().equals(RIGHT) && !escPressed){
                snake.setDirection(LEFT);
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT&& !snake.getDirection().equals(LEFT) && !escPressed){
                snake.setDirection(RIGHT);
            }
            if(e.getKeyCode() == KeyEvent.VK_UP&& !snake.getDirection().equals(DOWN) && !escPressed){
                snake.setDirection(UP);
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN && !snake.getDirection().equals(UP) && !escPressed){
                snake.setDirection(DOWN);
            }
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                if(escPressed){
                    snake.setDirection(currentDirection);
                    escPressed = false;
                }else{
                    currentDirection = snake.getDirection();
                    snake.setDirection(STOP);
                    escPressed = true;
                }
            }
        }
    }
}
