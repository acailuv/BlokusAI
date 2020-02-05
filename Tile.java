import javax.swing.JButton;
import java.awt.Color;

public class Tile extends JButton {
    protected int place = -1;

    protected void revert() {
        switch (place) {
        case -1:
            setBackground(Color.WHITE);
            break;
        case 0:
            setBackground(Color.RED);
            break;
        case 1:
            setBackground(Color.GREEN);
            break;
        case 2:
            setBackground(Color.BLUE);
            break;
        case 3:
            setBackground(Color.YELLOW);
            break;
        }
    }
}