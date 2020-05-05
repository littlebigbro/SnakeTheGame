import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Apples {
    // переменные характерные для яблока: размер, координаты, картинка.
    // так же надо реализовать время жизни
    // метод создания яблок
    // метод вычисления счета.
    // метод проверки при столкновении
    public int appleX;
    public int appleY;
    public ImageIcon appleIcon;

    public int getX() {
        return appleX;
    }

    public int getY() {
        return appleY;
    }

    public Image icon(){
        return appleIcon.getImage();
    }
}
