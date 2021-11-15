package main.java.ru.littlebigbro.GUI.Buttons;

import main.java.ru.littlebigbro.GUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseButton extends JButton {

    private static final String PAUSE = "Pause";
    private static final String CONTINUE = "Continue";

    public PauseButton() {
        setText(PAUSE);
        setPreferredSize(new Dimension(90, 40));
        addActionListener(new PauseListener());
        setVisible(false);
    }

    public void changeTextOfPauseButton() {
        if (this.getText().equals(PAUSE)) {
            this.setText(CONTINUE);
        }
        if (this.getText().equals(CONTINUE)) {
            this.setText(PAUSE);
        }
    }

    public GridBagConstraints getGridBagConstraints() {
        return new GridBagConstraints(0, 1, 2, 1, 1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0);
    }

    class PauseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GUI.pauseAction();
        }
    }
}
