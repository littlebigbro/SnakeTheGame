import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Apples {
    // так же надо реализовать время жизни
    public ImageIcon appleIcon;
    private int appleSize = 10;
    private int score;
    private boolean isExist;
    private Point applePoint = new Point();

    public Image icon(){
        return appleIcon.getImage();
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
        isExist = true;
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
        isExist = false;
    }

    public boolean isExist(){
        return isExist;
    }
}