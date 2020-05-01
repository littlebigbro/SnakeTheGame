/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow {

    JFrame frame = new JFrame();
    JPanel mainmenuPanel = new JPanel();


    public GameWindow() {
        JPanel east = new JPanel();
        JPanel west = new JPanel();
        JPanel south = new JPanel();
        JPanel north = new JPanel();

        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setUndecorated(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(mainmenuPanel);
        frame.add(BorderLayout.EAST, east);
        frame.add(BorderLayout.WEST, west);
        frame.add(BorderLayout.NORTH, north);
        frame.add(BorderLayout.SOUTH, south);
        frame.addKeyListener(new ExitAction());
        frame.setFocusable(true); //addKeyListener для фрейма или панели не активируется без этого параметра

        east.setBackground(Color.BLUE);
        west.setBackground(Color.BLUE);
        south.setBackground(Color.GREEN);
        north.setBackground(Color.GREEN);
        mainmenuPanel.setBackground(Color.BLACK);

        north.setPreferredSize(new Dimension(460, 50));
        south.setPreferredSize(new Dimension(460, 50));
        west.setPreferredSize(new Dimension(50, 360));
        east.setPreferredSize(new Dimension(50, 360));
        mainmenuPanel.setPreferredSize(new Dimension(360, 360));

        JButton newGameB = new JButton("New Game");
        newGameB.addActionListener(new NewGameButtonListener());

        JButton score = new JButton("Score");

        JButton exit = new JButton("Exit");
        exit.addActionListener(new ExitAction());

        mainmenuPanel.add(newGameB);
        mainmenuPanel.add(score);
        mainmenuPanel.add(exit);
        frame.setVisible(true);
    }

    class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainmenuPanel.setVisible(false);
            Game newGame = new Game();
            newGame.setVisible(true);
            frame.add(newGame);
            newGame.requestFocus(); //запрашивает фокус после создания фрейма
        }
    }

//19.04.2020 реализовать выход в главное меню
// при проигрыше реализовать выход из игры, выход в гл меню. начать заново, запись в таблицу
/*   class ScoreButtonListener implements ActionListener{ //сделать вывод таблицы 7 последних результатов игроков
        @Override
        public void actionPerformed(ActionEvent e) {
            new ScoreMenu();
        }
    }
*/
/*   public static void main(String[] args){
        new GameWindow();
    }
}
*/