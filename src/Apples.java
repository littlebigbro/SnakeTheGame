import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Apples {
    // так же надо реализовать время жизни
    // метод создания яблок
    // метод вычисления счета.
    // метод проверки при столкновении
    // переписать под точку

    public ImageIcon appleIcon;
    private int appleSize = 10;
    private int score;
    private Point applePoint = new Point();
    public Image icon(){
        return appleIcon.getImage();
    }

    public int getX() { return (int)applePoint.getX(); }
    public int getY() {
        return (int)applePoint.getY();
    }

    public void create(ArrayList<Point> restrictionList){
        int appleX;
        int appleY;
        while (true){
            appleX = new Random().nextInt(35) * appleSize;
            appleY = new Random().nextInt(35) * appleSize;
            applePoint.setLocation(appleX,appleY);
            if (restrictionList.contains(applePoint)){
                continue;
            } else { break;}
        }
    }
    public int getScore(){
        return score;
    }
    public void setScore(int dScore){
        score = dScore;
    }
}