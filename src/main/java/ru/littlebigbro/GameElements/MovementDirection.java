package main.java.ru.littlebigbro.GameElements;

public enum MovementDirection {
    RIGHT("right"),
    LEFT("left"),
    UP("up"),
    DOWN("down"),
    NODIRECTION("nodirection");

    private String direction;
    MovementDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
