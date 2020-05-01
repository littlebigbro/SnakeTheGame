import javax.swing.*;

public class Exit {
    public Exit() {
        int resDlg = JOptionPane.showConfirmDialog(null, "Exit ?", "", JOptionPane.YES_NO_OPTION);
        if (resDlg == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
