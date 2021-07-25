package main.java.ru.littlebigbro.GameElements;

import main.java.ru.littlebigbro.Enums.ImagePath;
import main.java.ru.littlebigbro.Extra.Point;
import main.java.ru.littlebigbro.Interfaces.GameElement;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Cell implements GameElement {
    private final String DEFAULT_IMAGE_PATH = ImagePath.FIELD_CELL.getPath();
    private final int DEFAULT_CELL_SIZE = 10;
    private int cellSize;
    private ImageIcon icon;

    @Override
    public void create() {
        setCellSize(DEFAULT_CELL_SIZE);
        setImage(DEFAULT_IMAGE_PATH);
    }

    public Image getImage() {
        return icon.getImage();
    }

    public void setImage(String pathToImage) {
        String path = pathToImage == null || pathToImage.isEmpty() ? DEFAULT_IMAGE_PATH : pathToImage;
        icon = new ImageIcon(path);
    }

    public List<Point> getCoordinates() {
        return Collections.emptyList();
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
}
