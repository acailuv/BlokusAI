import javax.swing.*;
import java.awt.*;

public class Board {

    public static final int BOARD_SIDE = 20;

    protected JFrame boardFrame          = new JFrame("Blokus Board");
    protected JPanel board               = new JPanel(new GridLayout(Board.BOARD_SIDE,Board.BOARD_SIDE));
    protected JButton boardTiles[][]     = new JButton[Board.BOARD_SIDE][Board.BOARD_SIDE];

    public Board() {
        // Setting Layout
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setLayout(new BorderLayout(10, 10));

        // Initializing Board
        for(int i=0; i<Board.BOARD_SIDE; i++) {
            for(int j=0; j<Board.BOARD_SIDE; j++) {
                boardTiles[i][j] = new JButton();
                boardTiles[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                boardTiles[i][j].setBackground(Color.WHITE);

                // Rough idea on how to highlight tiles
                int row, col;
                row = i;
                col = j;
                boardTiles[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        highlightTile(row, col,     Color.RED);
                        highlightTile(row+1, col,   Color.RED);
                        highlightTile(row-1, col,   Color.RED);
                        highlightTile(row, col+1,   Color.RED);
                        highlightTile(row, col-1,   Color.RED);
                    }
                
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        highlightTile(row, col,     Color.WHITE);
                        highlightTile(row+1, col,   Color.WHITE);
                        highlightTile(row-1, col,   Color.WHITE);
                        highlightTile(row, col+1,   Color.WHITE);
                        highlightTile(row, col-1,   Color.WHITE);
                    }
                });
                // -----------------------------------------------------------

                board.add(boardTiles[i][j]);
            }
        }

        // Final Initialization
        boardFrame.add(board);
        boardFrame.setSize(800, 800);
        boardFrame.setResizable(false);
    }

    private void highlightTile(int row, int col, Color c) {
        try {
            boardTiles[row][col].setBackground(c);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Skipp
        }
    }

    public void render() {
        boardFrame.setVisible(true);
    }

    public void hide() {
        boardFrame.setVisible(false);
    }

}