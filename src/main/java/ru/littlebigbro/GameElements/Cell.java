package main.java.ru.littlebigbro.GameElements;

import javax.swing.*;
import java.awt.*;

public class Cell {
    private final String DEFAULT_IMAGE_PATH = ImagePath.FIELD_CELL.getPath();
    private final int DEFAULT_CELL_SIZE = 10;
    private int cellSize;
    private ImageIcon icon;

    public Cell() {
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

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
}
