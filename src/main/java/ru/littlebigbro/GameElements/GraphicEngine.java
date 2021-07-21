package main.java.ru.littlebigbro.GameElements;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GraphicEngine {
    private Graphics g;
    private ImageObserver imageObserver;

    public GraphicEngine(Graphics g, ImageObserver imageObserver) {
        this.g = g;
        this.imageObserver = imageObserver;
    }

    public void drawSnake(Snake snake) {

    }

    public void drawApple(Apple apple) {
        Image image = apple.getImage();
        int x = apple.getCoordinates().getX();
        int y = apple.getCoordinates().getY();
        g.drawImage(image, x, y, imageObserver);
    }

    public Graphics drawFieldWithCells(Cell cell, int width, int height) {
        Image cellImage = cell.getImage();
        int widthCount = width/cell.getCellSize();
        int heightCount = height/cell.getCellSize();
        for (int i = 0; i < widthCount; i++) {
            for (int j = 0; j < heightCount; j++) {
                g.drawImage(cellImage, i, j, imageObserver);
            }
        }
        return g;
    }
}
