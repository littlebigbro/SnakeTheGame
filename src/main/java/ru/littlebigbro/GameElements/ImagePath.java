package main.java.ru.littlebigbro.GameElements;

public enum ImagePath {
    GREEN_APPLE("./src/main/resources/pics/greenapple.png"),
    RED_APPLE("./src/main/resources/pics/redapple.png"),
    YELLOW_APPLE("./src/main/resources/pics/yellowapple.png"),

    FIELD_CELL("./src/main/resources/pics/field3.png"),
    SNAKE_BODY("./src/main/resources/pics/snakeBody.png"),
    SNAKE_HEAD("./src/main/resources/pics/snakeHead.png");

    private String path;

    ImagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
