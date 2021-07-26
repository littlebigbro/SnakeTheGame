package main.java.ru.littlebigbro.Extra;

import java.util.Random;

public class Utils {
    public static int getRandomInt(Number number) {
        int bound = number.intValue();
        return new Random().nextInt(bound);
    }

    public static Point getRandomPoint(int maxX, int maxY) {
        int x = getRandomInt(maxX / 10) * 10;
        int y = getRandomInt(maxY / 10) * 10;
        return new Point(x, y);
    }
}
