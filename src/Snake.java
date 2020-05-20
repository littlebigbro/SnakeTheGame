import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Snake {
    // при поедании яблока добавлять ещё одно значение в оба листа
    private final int SEGMENT_SIZE = 10;
    private int snakeSize = 3;
    private String snakeDirection = "stop";
    private ImageIcon segmentIcon;

    private Point point;
    private ArrayList<Point> points = new ArrayList<>();


    public Image icon(){
        segmentIcon = new ImageIcon("pics/snake.png");
        return segmentIcon.getImage();
    }

    public int getSnakeSize(){
       return snakeSize;
    }

    public void setSnakeSize(int size){
        if (size > 0){
            point = new Point();
            point.setLocation(points.get(snakeSize - 1));
            snakeSize = size;
            points.add(snakeSize - 1,point);
        }
    }

    public String getDirection(){
        return snakeDirection;
    }

    public void setDirection(String direction){
        snakeDirection = direction;
    }
    public void draw(){
        for (int i = 0; i < snakeSize; i++){
            point = new Point();
            point.setLocation(40 - i * SEGMENT_SIZE, 30);
            points.add(i, point);
        }
    }
    public ArrayList<Point> getPoints(){return points;}

    public void move(){
        for (int i = snakeSize - 1; i > 0; i--){
            points.get(i).move((int) points.get(i-1).getX(),(int) points.get(i-1).getY());

        }
        if(snakeDirection.equals("left")){
            points.get(0).translate(-SEGMENT_SIZE,0);
        }
        if(snakeDirection.equals("right")){
            points.get(0).translate(SEGMENT_SIZE,0);
        }
        if(snakeDirection.equals("up")){
            points.get(0).translate(0, -SEGMENT_SIZE);
        }
        if(snakeDirection.equals("down")){
            points.get(0).translate(0, SEGMENT_SIZE);
        }
        if(snakeDirection.equals("stop")){
        }
    }
}