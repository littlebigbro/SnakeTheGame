package main.java.ru.littlebigbro.GameElements;

import main.java.ru.littlebigbro.Enums.ImagePath;
import main.java.ru.littlebigbro.Enums.MovementDirection;
import main.java.ru.littlebigbro.Extra.Point;
import main.java.ru.littlebigbro.Interfaces.GameConstants;
import main.java.ru.littlebigbro.Interfaces.GameElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements GameElement, GameConstants {
    private static final String DEFAULT_HEAD_IMAGE_PATH = ImagePath.SNAKE_HEAD.getPath();
    private static final String DEFAULT_BODY_IMAGE_PATH = ImagePath.SNAKE_BODY.getPath();
    private static final int DEFAULT_SEGMENT_SIZE = 10;
    private static final int DEFAULT_SNAKE_SIZE = 3;

    private String direction;
    final private List<Point> points = new ArrayList<>();
    //Глобальный размер змейки, изменяется из вне
    private int size = DEFAULT_SNAKE_SIZE;
    private boolean moved;
    private ImageIcon icon;
    private ImageIcon headIcon;
    private int counter = 0;
    private Point lastPoint = new Point();

    @Override
    public void create() {
        setHeadImage(DEFAULT_HEAD_IMAGE_PATH);
        setImage(DEFAULT_BODY_IMAGE_PATH);
        setDirection(RIGHT);
        draw();
    }

    private void draw() {
        for (int i = 0; i < size; i++) {
            Point point = new Point();
            point.setLocation(40 - i * DEFAULT_SEGMENT_SIZE, 30);
            points.add(i, point);
        }
    }

    public void move() {
        checkSize();
        //Двигаем тело вперед
        Point nextPoint;
        for (int i = points.size() - 1; i > 0; i--) {
            nextPoint = points.get(i - 1);
            points.get(i).move(nextPoint.getX(), nextPoint.getY());
        }
        int dX = 0;
        int dY = 0;
        if (direction.equals(LEFT)) {
            dX = -1;
        }
        if (direction.equals(RIGHT)) {
            dX = 1;
        }
        if (direction.equals(UP)) {
            dY = -1;
        }
        if (direction.equals(DOWN)) {
            dY = 1;
        }
        //Двигаем голову
        points.get(0).translate(dX * DEFAULT_SEGMENT_SIZE, dY * DEFAULT_SEGMENT_SIZE);
        moved = true;
    }

    private void checkSize() {
        if (points.size() < this.size) {
            addBodySegment();
        }
        if (points.size() > this.size) {
            removeSegments();
        }
    }

    private void addBodySegment() {
        counter++;
        int pointsSize = points.size();
        int lastPointIndex = pointsSize - 1;
        if (counter == pointsSize) {
            points.add(pointsSize, lastPoint);
            counter = 0;
            lastPoint = new Point();
        } else if (counter == lastPointIndex) {
            lastPoint.setLocation(points.get(lastPointIndex));
        }
    }

    private void removeSegments() {
        int fromIndex = points.size() - 1;
        int toIndex = this.size - 1;
        points.subList(fromIndex, toIndex).clear();
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
     * @param pathToImage Sets body image using path to image in the repository
     */
    @Override
    public void setImage(String pathToImage) {
        String path = pathToImage == null || pathToImage.isEmpty() ? DEFAULT_BODY_IMAGE_PATH : pathToImage;
        icon = new ImageIcon(path);
    }

    @Override
    public List<Point> getCoordinates() {
        return points;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size < DEFAULT_SNAKE_SIZE) {
            size = DEFAULT_SNAKE_SIZE;
        }
        this.size = size;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPointX(int pointIndex) {
        int pointX;
        int snakeLength = this.size;
        if (this.size > points.size()) {
            snakeLength = points.size();
        }
        if (pointIndex >= snakeLength) {
            pointX = points.get(snakeLength - 1).getX();
        } else {
            pointX = points.get(pointIndex).getX();
        }
        return pointX;
    }

    public int getPointY(int pointIndex) {
        int pointY;
        int snakeLength = this.size;
        if (this.size > points.size()) {
            snakeLength = points.size();
        }
        if (pointIndex >= snakeLength) {
            pointY = points.get(snakeLength - 1).getY();
        } else {
            pointY = points.get(pointIndex).getY();
        }
        return pointY;
    }

    public void setMoved(boolean isMoved) {
        moved = isMoved;
    }

    public boolean getMoved() {
        return moved;
    }
}