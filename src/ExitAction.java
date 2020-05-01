import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExitAction implements ActionListener, KeyListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new Exit();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            new Exit();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
