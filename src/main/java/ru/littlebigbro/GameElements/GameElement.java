package main.java.ru.littlebigbro.GameElements;

import java.awt.*;
import java.util.ArrayList;

public interface GameElement {
    void create(Point point, ArrayList<Point> restrictionList);
}
