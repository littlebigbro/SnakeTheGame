package main.java.ru.littlebigbro.Enums;

public enum MovementDirection {
    RIGHT("right"),
    LEFT("left"),
    UP("up"),
    DOWN("down"),
    NO_DIRECTION("no_direction");

    private String direction;

    MovementDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
