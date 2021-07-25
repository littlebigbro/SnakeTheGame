package main.java.ru.littlebigbro.GameElements;

import main.java.ru.littlebigbro.Extra.Utils;

/**
 * Реализация игрального кубика
 * Выдает значение от 1 до 20 с учетом шанса
 */
public class GameCube {
    public static int shot(int chance) {
        return Utils.getRandomInt(chance) * 19 + 1;
    }
}