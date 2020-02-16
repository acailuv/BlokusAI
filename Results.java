import javax.swing.*;
import java.awt.*;

public class Results extends JFrame {
    public Results(Player[] players) {
        int scores[] = new int[players.length];
        for(int i = 0; i < players.length; i++) {
            if(players[i].usedAll() == true) {
                if(players[i].lastPiece == 0) {
                    scores[i] = 20;
                } else {
                    scores[i] = 15;
                }
            } else {
                scores[i] = 0;
                for(int j = 0; j < players[i].usedPiece.length; j++) {
                    if(players[i].usedPiece[j] == false) {
                        scores[i] -= players[i].pieceValue[j];
                    }
                }
            }
        }        
        
        JPanel pane = new JPanel(new GridLayout(4, 1));

        for(int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
            pane.add(new JLabel("Player " + Integer.toString(i+1) + ": " + Integer.toString(scores[i])));
        }

        add(pane);
        setTitle("Results");
        setSize(800, 800);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}