package main.java.ru.littlebigbro.Interfaces;


import main.java.ru.littlebigbro.Extra.Point;

import java.awt.*;
import java.util.List;

public interface GameElement {
    void create();

    Image getImage();

    void setImage(String pathToImage);

    List<Point> getCoordinates();
}