import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Snake {
    private final int SEGMENT_SIZE = 10;
    private int snakeSize = 5;
    private String snakeDirection = "stop";
    private ImageIcon segmentIcon;
    private ImageIcon snakeHeadIcon;
    private Point point;
    private ArrayList<Point> points = new ArrayList<>();
    private boolean moved;

    public Image icon(){
        segmentIcon = new ImageIcon("pics/snake.png");
        return segmentIcon.getImage();
    }

    public Image head(){
        snakeHeadIcon = new ImageIcon("pics/snakeHead.png");
        return snakeHeadIcon.getImage();
    }

    public int getSnakeSize(){
       return snakeSize;
    }

    public void setSnakeSize(int size){
        if (size > 2){
            if(size > snakeSize) {
                for (int i = snakeSize - 1; i < size; i++){
                    point = new Point();
                    point.setLocation(points.get(i));
                    points.add(i, point);
                }
            } else {
                for(int i = snakeSize - 1 ; i >= size ; i-- ){
                    points.remove(i);
                }
            }
            snakeSize = size;
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

    public int getPointX(int Key){
        return (int) points.get(Key).getX();
    }

    public int getPointY(int Key){
        return (int) points.get(Key).getY();
    }

    public void setMoved(boolean isMoved){
        moved = isMoved;
    }
    public boolean getMoved(){
        return moved;
    }

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
        moved = true;
    }
}