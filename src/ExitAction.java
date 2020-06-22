import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExitAction implements ActionListener, KeyListener {
    public ExitAction(){
        int resDlg = JOptionPane.showConfirmDialog(null, "Exit?", "", JOptionPane.YES_NO_OPTION);
        if (resDlg == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ExitAction();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            new ExitAction();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}