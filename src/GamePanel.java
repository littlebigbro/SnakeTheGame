import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private final static int SIZE = 360;
    private Snake snake = new Snake();
    private final int SEGMENT_SIZE = 10;
    private Image segment;
    private Image apple;
    private int[] x = new int[SIZE * SEGMENT_SIZE];
    private int[] y = new int[SIZE * SEGMENT_SIZE];

    public GamePanel(){
        setPreferredSize(new Dimension(SIZE,SIZE));
        setBackground(Color.DARK_GRAY);
        loadImages();
        int snakeSize = snake.getSize();
        for (int i = 0; i < snakeSize; i++){
            x[i] = 40 - i * SEGMENT_SIZE;
            y[i] = 30;
        }
        //initGame();
        addKeyListener(new FieldKeyListener());
        //setFocusable(true);
    }

    //не нравится, надо переделать
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(true){
            //g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < snake.getSize(); i++){
                g.drawImage(segment,x[i],y[i], this);
            }
        } else{
            String str = "Game Over";
            Font f = new Font("Arial",Font.BOLD, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, SIZE/2-50,SIZE/2);
        }
    }

    //не нравится, надо переделать
    public void loadImages(){
        ImageIcon appleIcon = new ImageIcon("pics/apple.png");
        apple = appleIcon.getImage();
        ImageIcon dotIcon = new ImageIcon("pics/dark.png");
        segment = dotIcon.getImage();
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (inGame){
//            checkApple();
//            move();
//            checkCollisions();
//        }
//        repaint();
//    }
    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                snake.move("left");
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                snake.move("right");
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                snake.move("up");
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                snake.move("down");
            }
        }
    }
}
