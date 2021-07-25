package main.java.ru.littlebigbro.GameElements;

import main.java.ru.littlebigbro.Enums.ImagePath;
import main.java.ru.littlebigbro.Extra.Point;
import main.java.ru.littlebigbro.Extra.Utils;
import main.java.ru.littlebigbro.Interfaces.GameElement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Apple implements GameElement {
    public static final int DEFAULT_SIZE = 10;
    private final String DEFAULT_IMAGE_PATH = ImagePath.GREEN_APPLE.getPath();

    private int size = DEFAULT_SIZE;
    private Point coordinates = new Point();
    private ImageIcon icon;
    private int score;
    private int chance = 0;
    private int stepsOfExist;
    private boolean isExist = false;

    public Apple(String pathToImage, int score, int stepsOfExist) {
        setScore(score);
        setStepsOfExist(stepsOfExist);
        setImage(pathToImage);
    }

    @Override
    public void create() {

    }

    //TODO: Переделать
    public boolean create(Point point, ArrayList<Point> restrictionList) {
        if (point == null || point.getX() < 0 || restrictionList == null) {
            return false;
        }
        while (true) {
            coordinates.setLocation(Utils.getRandomInt(point.getX()), Utils.getRandomInt(point.getY()));
            if (!restrictionList.contains(coordinates)) {
                break;
            }
        }
        isExist = true;
        return true;
    }

    public void remove() {
        isExist = false;
        coordinates.setLocation(-3, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return isExist == apple.isExist && Objects.equals(coordinates, apple.coordinates) && icon.equals(apple.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinates, icon, isExist);
    }

    public List<Point> getCoordinates() {
        List<Point> pointList = new ArrayList<>();
        pointList.add(coordinates);
        return pointList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Image getImage() {
        return icon.getImage();
    }

    public void setImage(String pathToImage) {
        String path = pathToImage == null || pathToImage.isEmpty() ? DEFAULT_IMAGE_PATH : pathToImage;
        icon = new ImageIcon(path);
    }

    public int getStepsOfExist() {
        return stepsOfExist;
    }

    public void setStepsOfExist(int steps) {
        stepsOfExist = steps;
    }

    public boolean isExist() {
        return isExist;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getChance() {
        return chance;
    }

    public void increaseChance(int chance) {
        this.chance += chance;
    }

    public void zeroChance() {
        this.chance = 0;
    }
}