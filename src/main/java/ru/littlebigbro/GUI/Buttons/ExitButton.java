package main.java.ru.littlebigbro.GUI.Buttons;

import main.java.ru.littlebigbro.GUI.ExitActionListener;

import javax.swing.*;
import java.awt.*;

public class ExitButton extends JButton {

    public ExitButton() {
        setText("Exit");
        setPreferredSize(new Dimension(90, 40));
        addActionListener(new ExitActionListener());
        setVisible(true);
    }

    public GridBagConstraints getGridBagConstraints() {
        return new GridBagConstraints(0, 2, 6, 1, 1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0);
    }
}
