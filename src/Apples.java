import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Apples {
    public ImageIcon appleIcon;
    final private int appleSize = 10;
    private int score;
    private boolean isExist;
    final private Point applePoint = new Point();
    private int timeOfExist = 0;

    public Image icon(){
        return appleIcon.getImage();
    }

    public void create(int fieldX, int fieldY, ArrayList<Point> restrictionList){
        int appleX;
        int appleY;
        while (true){
            appleX = new Random().nextInt(fieldX/10 - 1) * appleSize;
            appleY = new Random().nextInt(fieldY/10 - 1) * appleSize;
            applePoint.setLocation(appleX,appleY);
            if (!restrictionList.contains(applePoint)){
                break;
            }
        }
        isExist = true;
    }

    public void create(int x, int y){
        applePoint.setLocation(x,y);
    }

    public int getX() { return (int)applePoint.getX(); }
    public int getY() {
        return (int)applePoint.getY();
    }

    public int getScore(){
        return score;
    }

    public void setScore(int dScore){
        score = dScore;
    }

    public void delete(){
        // так как нельзя удалить созданную точку, переписываю её координаты на недоступные в данном игровом поле
        isExist = false;
        applePoint.setLocation(-3,0);
    }
    public void setTimeOfExist(int time){
        timeOfExist = time;
    }

    public int getTimeOfExist(){
        return timeOfExist;
    }

    public boolean isExist(){
        return isExist;
    }
}