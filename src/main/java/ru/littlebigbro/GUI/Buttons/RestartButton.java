package main.java.ru.littlebigbro.GUI.Buttons;

import main.java.ru.littlebigbro.GUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartButton extends JButton {

    private static final String RESTART = "Restart";

    public RestartButton() {
        setText(RESTART);
        setPreferredSize(new Dimension(90, 40));
        addActionListener(new RestartListener());
        setVisible(false);
    }

    public GridBagConstraints getGridBagConstraints() {
        return new GridBagConstraints(2, 1, 2, 1, 1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0);
    }

    class RestartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GUI.restartAction();
        }
    }
}
