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
    private String moveDirection = RIGHT;

    public GamePanel(){
        setPreferredSize(new Dimension(SIZE,SIZE));
        setBackground(Color.DARK_GRAY);
        loadImages();
//        initGame();
        timer = new Timer(300,this);
        timer.start();
        snake.draw();
        addKeyListener(new FieldKeyListener());

        setFocusable(true);
    }

    public void loadImages(){
        appleIm = greenApple.icon();
        segmentIm = snake.icon();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(true){
            g.drawImage(appleIm,greenApple.getX(),greenApple.getY(),this);
            for (int i = 0; i < snake.getSnakeSize(); i++){
                g.drawImage(segmentIm,snake.getX().get(i),snake.getY().get(i), this);
            }
        } else{
            String str = "Game Over";
            Font f = new Font("Arial",Font.BOLD, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, SIZE/2-50,SIZE/2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (true){
            //checkApple();
            snake.move(moveDirection);
           // checkCollisions();
        }
        repaint();
    }
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //добавить задержку на передачу равную времени необходимому для ячейки для перемещения
            //проверка на направление движение
            if(e.getKeyCode() == KeyEvent.VK_LEFT && moveDirection != RIGHT){
                moveDirection = LEFT;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT&& moveDirection != LEFT){
                moveDirection = RIGHT;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP&& moveDirection != DOWN){
                moveDirection = UP;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN && moveDirection != UP){
                moveDirection = DOWN;
            }
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                moveDirection = STOP;
            }
        }
    }
}
