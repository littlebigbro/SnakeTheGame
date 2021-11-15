package main.java.ru.littlebigbro.GUI.Buttons;

import main.java.ru.littlebigbro.GUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesButton extends JButton {
    private static final String RULES = "Rules";
    public RulesButton() {
        setText(RULES);
        setPreferredSize(new Dimension(90, 40));
        addActionListener(new RulesListener());
        setVisible(true);
    }

    public GridBagConstraints getGridBagConstraints() {
        return new GridBagConstraints(4, 1, 2, 1, 1, 1,
                GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0);
    }

    class RulesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GUI.pauseAction();
            String[] rulesText = new String[]{
                    "Rules:",
                    "1) Eat green apple and you gain 10 points;",
                    "2) Eat red apple and you gain 100 points;",
                    "3) Eat yellow apple and you lose 50 points;",
                    "4) DO NOT hit walls or yourself!!!"
            };
            JOptionPane.showMessageDialog(null, rulesText, RULES, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
