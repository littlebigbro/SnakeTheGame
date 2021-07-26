package main.java.ru.littlebigbro.Extra;

import java.util.ArrayList;
import java.util.List;

public class Restrictions {
    //Класс в котором прописаны все ограничения для создания новых обьектов на игровом поле
    //Для исключения ситуаций, в которых "яблоко" может быть создано за игровым полем или внутри "змейки"
    public static final int GAME_FIELD_HEIGHT = 300;
    public static final int GAME_FIELD_WIDTH = 300;
    public static List<Point> snakePoints;
    private static List<Point> applesPoints = new ArrayList<>();

    public static List<Point> getApplesPoints() {
        return applesPoints;
    }

    public static void addApplePoint(Point point) {
        applesPoints.add(point);
    }

    public static boolean dropApplePoint(Point p) {
        if (applesPoints.contains(p)) {
            applesPoints.remove(p);
            return true;
        }
        return false;
    }

    public static boolean contains(Point point) {
        return getAllRestrictionPoints().contains(point);
    }

    public static List<Point> getAllRestrictionPoints() {
        List<Point> allPoints = new ArrayList<>();
        if (snakePoints != null) {
            allPoints.addAll(snakePoints);
        }
        if (applesPoints != null) {
            allPoints.addAll(applesPoints);
        }
        return allPoints;
    }
}
