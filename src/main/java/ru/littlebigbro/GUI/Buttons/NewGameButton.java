package main.java.ru.littlebigbro.GUI.Buttons;

import main.java.ru.littlebigbro.GUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameButton extends JButton {
    private static final String NEW_GAME = "New Game";
    public NewGameButton() {
        setText(NEW_GAME);
        setPreferredSize(new Dimension(90, 40));
        addActionListener(new NewGameButtonListener());
        setVisible(true);
    }

    class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            GUI.startNewGame();
        }
    }
}
