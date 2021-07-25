package main.java.ru.littlebigbro.Engines;

import main.java.ru.littlebigbro.Extra.Point;
import main.java.ru.littlebigbro.GameElements.Apple;
import main.java.ru.littlebigbro.GameElements.Cell;
import main.java.ru.littlebigbro.GameElements.Snake;
import main.java.ru.littlebigbro.Interfaces.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GraphicEngine implements Engine {
    private final Graphics g;
    private final ImageObserver imageObserver;

    public GraphicEngine(Graphics g, ImageObserver imageObserver) {
        this.g = g;
        this.imageObserver = imageObserver;
    }

    public void drawSnake(Snake snake) {
        snake.getImage();

    }

    public void drawApple(Apple apple) {
        Image image = apple.getImage();
        Point appleCoordinates = apple.getCoordinates().get(0);
        int x = appleCoordinates.getX();
        int y = appleCoordinates.getY();
        g.drawImage(image, x, y, imageObserver);
    }

    public void drawFieldWithCells(String cellImagePath, int width, int height) {
        Cell cell = new Cell();
        cell.setImage(cellImagePath);
        Image cellImage = cell.getImage();
        for (int i = 0; i < width; i += 10) {
            for (int j = 0; j < height; j += 10) {
                g.drawImage(cellImage, i, j, imageObserver);
            }
        }
    }

    public void drawMessageString(String message, JComponent component, int fieldSizeInPixels) {
        Font f = new Font("Arial", Font.BOLD, 20);
        FontMetrics fontMetrics = component.getFontMetrics(f);
        int strWidth = fontMetrics.stringWidth(message);
        int x = (fieldSizeInPixels - strWidth) / 2;
        int y = (fieldSizeInPixels + f.getSize()) / 2;
        g.setFont(f);
        g.drawString(message, x, y);
    }

    public void setStringColor(Color color) {
        g.setColor(color);
    }
}
