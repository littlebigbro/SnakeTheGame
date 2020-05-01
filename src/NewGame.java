import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewGame extends JPanel implements ActionListener {
    public NewGame(){
        setBackground(Color.GREEN);
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            if(e.getKeyCode() == KeyEvent.VK_LEFT ){
//                left = true;
//                up = false;
//                down = false;
//            }
//            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//                right = true;
//                up = false;
//                down = false;
//            }
//            if(e.getKeyCode() == KeyEvent.VK_UP){
//                up = true;
//                left = false;
//                right = false;
//            }
//            if(e.getKeyCode() == KeyEvent.VK_DOWN){
//                down = true;
//                left = false;
//                right = false;
//            }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                ;
            }
        }
    }
}