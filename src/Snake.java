import javax.swing.*;
import java.awt.*;

public class Snake {
    // Должен быть метод который рисует змейку.
    // Метод который двигает змейку
    private int snakeSize = 3;
    private String snakeDirection = "stop";

    public int getSize(){
       return snakeSize;
    }

    public void setSize(int size){
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
//        ImageIcon dotIcon = new ImageIcon("pics/dark.png");
//        segment = dotIcon.getImage();
//        for (int i = 0; i < snakeSize; i++){
//            x[i] = 40 - i * SEGMENT_SIZE;
//            y[i] = 30;
//        }
    }

    //двигает змейку
    public void move(String snakeDirection){

    }
}