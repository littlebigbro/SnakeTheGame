package main.java.ru.littlebigbro.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ExitActionListener extends KeyAdapter implements ActionListener {
    @Override
    public void keyTyped(KeyEvent e) { }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GUI.exitAction();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        GUI.exitAction();
    }
}
