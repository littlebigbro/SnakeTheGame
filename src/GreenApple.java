import javax.swing.*;
import java.awt.*;

public class GreenApple extends Apples {

    public GreenApple(){
        appleX = 50;
        appleY = 100;
    }

    public Image icon(){
        appleIcon = new ImageIcon("pics/apple.png");
        return appleIcon.getImage();
    }
}
