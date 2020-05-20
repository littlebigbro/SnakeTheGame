import javax.swing.*;
import java.awt.*;

public class GreenApple extends Apples {

    public GreenApple(){
        appleSize = 10;
        setScore(10);
    }

    public Image icon(){
        appleIcon = new ImageIcon("pics/greenapple.png");
        return appleIcon.getImage();
    }

}
