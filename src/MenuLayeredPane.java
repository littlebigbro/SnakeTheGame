import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuLayeredPane extends JPanel {

    public JLayeredPane layeredPane;
    JPanel mainMenu;
    public MenuLayeredPane() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(500, 380));
        layeredPane.setBorder(BorderFactory.createTitledBorder("Menu"));
        mainMenu = new JPanel();
        mainMenu.setOpaque(true);
        mainMenu.setBounds(0,0,360,360);
        mainMenu.setBackground(Color.WHITE);

        JButton newGameB = new JButton("New Game");
        newGameB.addActionListener(new NewGameButtonListener());

        JButton score = new JButton("Score");

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ExitAction());

        mainMenu.add(newGameB);
        mainMenu.add(score);
        mainMenu.add(exit);
        add(mainMenu);
        //layeredPane.add(mainMenu,0);
        add(layeredPane);
    }

    class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainMenu.setVisible(false);
            NewGame game = new NewGame();
            PauseMenu pause = new PauseMenu();
            game.setOpaque(true);
            game.setBounds(0,0,360,360);
            game.setVisible(true);
            layeredPane.add(game,1);
            pause.setOpaque(true);
            pause.setBounds(0,0,360,360);
            pause.setVisible(false);
            layeredPane.add(pause, 2);
            game.requestFocus(); //запрашивает фокус после создания панели
        }

    }
 /*   public JLabel createColoredLabel(String text, Color color, Point zero) {
        label = new JLabel(text);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        //label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBounds(zero.x, zero.y, 500, 500);
        return label;
    }

    public JPanel createControlPanel() {

        layerList = new JComboBox(layerStrings);
        layerList.setSelectedIndex(0);

        layerList.setActionCommand(LAYER_COMMAND);
        layerList.addActionListener(this);

        JPanel controls = new JPanel();
        controls.add(layerList);
        controls.setBorder(BorderFactory.createTitledBorder("Main Menu"));
        return controls;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (LAYER_COMMAND.equals(cmd)) {

            System.out.println(label.getText());
        }
    }*/
}

//        Game newGame = new Game();
//        newGame.setOpaque(true);
//        newGame.setBounds(0,0,360,360); //обязательно надо
//        BackToMM back = new BackToMM();
//        back.setOpaque(true);
//        back.setBounds(80,0,200,360);

//        layeredPane.add(newGame,1);
//        layeredPane.add(back,0);
// newGame.setOpaque(false);
//        back.setOpaque(true);
//        back.setVisible(true);
// newGame.setVisible(false);