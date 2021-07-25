package main.java.ru.littlebigbro.GameElements;

import main.java.ru.littlebigbro.Enums.ImagePath;
import main.java.ru.littlebigbro.Enums.MovementDirection;
import main.java.ru.littlebigbro.Extra.Point;
import main.java.ru.littlebigbro.Interfaces.GameElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements GameElement {
    private static final String DEFAULT_HEAD_IMAGE_PATH = ImagePath.SNAKE_HEAD.getPath();
    private static final String DEFAULT_BODY_IMAGE_PATH = ImagePath.SNAKE_BODY.getPath();
    private static final int DEFAULT_SEGMENT_SIZE = 10;
    private static final int DEFAULT_SNAKE_SIZE = 3;
    private String snakeDirection = MovementDirection.NO_DIRECTION.getDirection();
    private main.java.ru.littlebigbro.Extra.Point point;
    final private List<main.java.ru.littlebigbro.Extra.Point> points = new ArrayList<>();
    private int size;
    private boolean moved;
    private ImageIcon icon;
    private ImageIcon headIcon;
    private int segmentSize;

    @Override
    public void create() {
        setHeadImage(DEFAULT_HEAD_IMAGE_PATH);
        setImage(DEFAULT_BODY_IMAGE_PATH);
        setSegmentSize(DEFAULT_SEGMENT_SIZE);
        setSize(DEFAULT_SNAKE_SIZE);
    }

    private void setSegmentSize(int segmentSize) {
        this.segmentSize = segmentSize;
    }

    public int getSegmentSize() {
        return segmentSize;
    }

    private void setHeadImage(String pathToImage) {
        String path = pathToImage == null || pathToImage.isEmpty() ? DEFAULT_BODY_IMAGE_PATH : pathToImage;
        headIcon = new ImageIcon(path);
    }

    public Image getHeadImage() {
        return headIcon.getImage();
    }

    /**
     * @return Body image
     */
    @Override
    public Image getImage() {
        return icon.getImage();
    }

    /**
     * @param pathToImage Sets body image using path to image in the repo
     */
    @Override
    public void setImage(String pathToImage) {
        String path = pathToImage == null || pathToImage.isEmpty() ? DEFAULT_BODY_IMAGE_PATH : pathToImage;
        icon = new ImageIcon(path);
    }

    @Override
    public List<main.java.ru.littlebigbro.Extra.Point> getCoordinates() {
        return points;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        boolean result = checkSize(size);
        /*
         * Если переданное больше текущего, то увеличиваем.
         * Если переданное меньше текущего, то проверяем разницу между значениями(дельта)
         * Если дельта меньше стандартного значения то используем стандартное,
         * Если больше то и используем переданное(обрезаем)
         * */
        this.size = size;
    }

    private boolean checkSize(int size) {
        if (size > this.size) {
            for (int i = this.size - 1; i < size; i++) {
                point = new main.java.ru.littlebigbro.Extra.Point();
                point.setLocation(points.get(i));
                points.add(i, point);
            }
        } else {
            points.subList(size, this.size).clear();
        }
        return false;
    }

    public String getDirection() {
        return snakeDirection;
    }

    public void setDirection(String direction) {
        snakeDirection = direction;
    }

    public int getPointX(int pointIndex) {
        return points.get(pointIndex).getX();
    }

    public int getPointY(int pointIndex) {
        return points.get(pointIndex).getY();
    }


    public void draw() {
        for (int i = 0; i < size; i++) {
            point = new Point();
            point.setLocation(40 - i * segmentSize, 30);
            points.add(i, point);
        }
    }

    public void move() {
        for (int i = size - 1; i > 0; i--) {
            points.get(i).move((int) points.get(i - 1).getX(), (int) points.get(i - 1).getY());

        }
        if (snakeDirection.equals("left")) {
            points.get(0).translate(-segmentSize, 0);
        }
        if (snakeDirection.equals("right")) {
            points.get(0).translate(segmentSize, 0);
        }
        if (snakeDirection.equals("up")) {
            points.get(0).translate(0, -segmentSize);
        }
        if (snakeDirection.equals("down")) {
            points.get(0).translate(0, segmentSize);
        }
        moved = true;
    }


    public void setMoved(boolean isMoved) {
        moved = isMoved;
    }

    public boolean getMoved() {
        return moved;
    }
}