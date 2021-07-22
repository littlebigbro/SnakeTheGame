package main.java.ru.littlebigbro.GameElements;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class GraphicEngine {
    private final Graphics g;
    private final ImageObserver imageObserver;
    private String message;

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

    public void drawFieldWithCells(Cell cell, int width, int height) {
        Image cellImage = cell.getImage();
        for (int i = 0; i < width; i+=10) {
            for (int j = 0; j < height; j+=10) {
                g.drawImage(cellImage, i, j, imageObserver);
            }
        }
    }

    public void drawMessageString(String message, JComponent component, int fieldSizeInPixels) {
        this.message = message;
        Font f = new Font("Arial",Font.BOLD, 20);
        FontMetrics fontMetrics = component.getFontMetrics(f);
        int strWidth = fontMetrics.stringWidth(message);
        g.setFont(f);
        g.drawString(message, (fieldSizeInPixels - strWidth)/2,(fieldSizeInPixels + f.getSize())/2);
    }

    public void setColor(Color color) {
        g.setColor(color);
    }
}
