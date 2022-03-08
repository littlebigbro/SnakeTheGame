package main.java.ru.littlebigbro.Interfaces;

import main.java.ru.littlebigbro.Enums.MovementDirection;
import main.java.ru.littlebigbro.Extra.Restrictions;

public interface GameConstants {
    public static final String LEFT = MovementDirection.LEFT.getDirection();
    public static final String UP = MovementDirection.UP.getDirection();
    public static final String DOWN = MovementDirection.DOWN.getDirection();
    public static final String STOP = MovementDirection.NO_DIRECTION.getDirection();
    public static final int FIELD_SIZE_IN_PIXELS = Restrictions.GAME_FIELD_HEIGHT;
    public static final int SEGMENT_SIZE_IN_PIXELS = 10;
    public static final int SEGMENTS_COUNT = FIELD_SIZE_IN_PIXELS / SEGMENT_SIZE_IN_PIXELS;
    public static final String RIGHT = MovementDirection.RIGHT.getDirection();
}
