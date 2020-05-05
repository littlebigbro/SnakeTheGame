import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Snake {
    // при поедании яблока добавлять ещё одно значение в оба листа
    private final int SEGMENT_SIZE = 10;
    private int snakeSize = 6;
    private String snakeDirection = "stop";
    private ArrayList<Integer> x = new ArrayList<Integer>();
    private ArrayList<Integer> y = new ArrayList<Integer>();
    private ImageIcon segmentIcon;

    public Image icon(){
        segmentIcon = new ImageIcon("pics/dark.png");
        return segmentIcon.getImage();
    }


    public int getSnakeSize(){
       return snakeSize;
    }

    public void setSnakeSize(int size){
        if (size > 0){
            snakeSize = size;
        }
    }

    public String getDirection(){
        return snakeDirection;
    }

    public void setDirection(String direction){
        snakeDirection = direction;
    }
    //отрисовывает змейку
    public void draw(){
        for (int i = 0; i < snakeSize; i++){
            x.add(i, 40 - i * SEGMENT_SIZE);
            y.add(i, 30);
        }
    }

    public ArrayList<Integer> getX() {
        return x;
    }

    public ArrayList<Integer> getY() {
        return y;
    }

    public void move(String snakeDirection){
        int a = x.get(0);
        int b = y.get(0);
        for (int i = snakeSize - 1; i > 0; i--){
            x.set(i, x.get(i-1));
            y.set(i, y.get(i-1));
        }
        if(snakeDirection.equals("left")){
            x.set(0, a - SEGMENT_SIZE);
        }
        if(snakeDirection.equals("right")){
            x.set(0, a + SEGMENT_SIZE);
        }
        if(snakeDirection.equals("up")){
            y.set(0, b - SEGMENT_SIZE);
        }
        if(snakeDirection.equals("down")){
            y.set(0, b + SEGMENT_SIZE);
        }
        if(snakeDirection.equals("stop")){
            ;
        }
    }
}