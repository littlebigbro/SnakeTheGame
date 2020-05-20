import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Apples {
    // так же надо реализовать время жизни
    // метод создания яблок
    // метод вычисления счета.
    // метод проверки при столкновении
    // переписать под точку

    public ImageIcon appleIcon;
    public int appleSize;
    private int score;
    private Point applePoint = new Point();
    public Image icon(){
        return appleIcon.getImage();
    }

    public int getX() { return (int)applePoint.getX(); }
    public int getY() {
        return (int)applePoint.getY();
    }

    public void create(){
        int appleX = new Random().nextInt(35) * appleSize;
        int appleY = new Random().nextInt(35) * appleSize;
        applePoint.setLocation(appleX,appleY);
    }
    public void create(ArrayList<Point> restrictionList){
        //зарефакторить (не брать точки при создании со значениями из листа)
        int appleX = new Random().nextInt(35) * appleSize;
        int appleY = new Random().nextInt(35) * appleSize;
        applePoint.setLocation(appleX,appleY);
        while (restrictionList.contains(applePoint)){
            appleX = new Random().nextInt(35) * appleSize;
            appleY = new Random().nextInt(35) * appleSize;
            applePoint.setLocation(appleX,appleY);
        }
    }
    public int getScore(){
        return score;
    }
    public void setScore(int dScore){
        score = dScore;
    }
}
