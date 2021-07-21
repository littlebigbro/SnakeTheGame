package main.java.ru.littlebigbro;

import main.java.ru.littlebigbro.GameElements.Point;

import java.util.Random;

public class Utils {
    public static int getRandomInt(Number number) {
        int bound = number.intValue();
        return new Random().nextInt(bound);
    }

    public static Point getRandomPoint(int maxX, int maxY) {
        return new Point(getRandomInt(maxX),getRandomInt(maxY));
    }
}
