import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JPanel implements ActionListener {
    private final int SIZE = 360;
    private final int DOT_SIZE = 12;
    private final int ALL_DOTS = 900;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left;
    private boolean right = true;
    private boolean up;
    private boolean down;
    private boolean inGame = true;

    public Game(){
        setPreferredSize(new Dimension(SIZE,SIZE));
        setBackground(Color.DARK_GRAY);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame(){
        dots = 3;
        for (int i = 0; i < dots; i++){
            x[i] = 36 - i * DOT_SIZE;
            y[i] = 36;
        }
        timer = new Timer(300,this);
        timer.start();
        createApple();
    }

    public void createApple(){ //создает яблоко в рандомной точке игрового поля
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;
    }

    public void loadImages(){
        ImageIcon appleIcon = new ImageIcon("pics/apple.png");
        apple = appleIcon.getImage();
        ImageIcon dotIcon = new ImageIcon("pics/dark.png");
        dot = dotIcon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++){
                g.drawImage(dot,x[i],y[i], this);
            }
        } else{
            String str = "Game Over";
            Font f = new Font("Arial",Font.BOLD, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, SIZE/2-50,SIZE/2);
        }
    }

    public void move(){
        for (int i = dots; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        if(left){
            x[0] -=DOT_SIZE;
        }
        if(right){
            x[0] +=DOT_SIZE;
        }
        if(up){
            y[0] -=DOT_SIZE;
        }
        if(down){
            y[0] +=DOT_SIZE;
        }
    }

    public void checkApple(){
        if(x[0] == appleX && y[0] == appleY){
            dots++;
            createApple();
        }
    }

    public void checkCollisions(){
        for (int i = dots; i > 0; i--){
            if (i > 4 && x[0] == x[i] && y[0] == y[i]){
             inGame = false;
            }
        }
        
        if (x[0] > SIZE || x[0] < 0 || y[0] < 0 || y[0] > SIZE){
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame){
            checkApple();
            move();
            checkCollisions();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_UP && !down){
                up = true;
                left = false;
                right = false;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN && !up){
                down = true;
                left = false;
                right = false;
            }
        }
    }
}
