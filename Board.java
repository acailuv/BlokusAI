import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board {

    public static final int BOARD_SIDE = 20;

    protected JFrame boardFrame = new JFrame("Blokus Board");
    protected JPanel board = new JPanel(new GridLayout(Board.BOARD_SIDE, Board.BOARD_SIDE));
    protected Tile boardTiles[][] = new Tile[Board.BOARD_SIDE][Board.BOARD_SIDE];
    protected Boolean firstTurn[] = new Boolean[4];
    protected Player currentPlayer;
    protected int currentPlayerId;

    public Board() {
        // Set all players as first turn
        for (int i = 0; i < 4; i++) {
            firstTurn[i] = true;
        }
        // Setting Layout
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setLayout(new BorderLayout(10, 10));

        // Initializing Board
        for (int i = 0; i < Board.BOARD_SIDE; i++) {
            for (int j = 0; j < Board.BOARD_SIDE; j++) {
                boardTiles[i][j] = new Tile();
                boardTiles[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                boardTiles[i][j].setBackground(Color.WHITE);

                // Rough idea on how to highlight tiles
                int row, col;
                row = i;
                col = j;
                boardTiles[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        Piece currentPiece = currentPlayer.currentPiece;
                        for (int k = 0; k < 5; k++) {
                            for (int l = 0; l < 5; l++) {
                                if (currentPiece.matrix[k][l] >= 1) {
                                    highlightTile(row - 2 + k, col - 2 + l, Color.CYAN);
                                }
                            }
                        }
                    }

                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        revertAll();
                    }
                });
                boardTiles[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Piece currentPiece = currentPlayer.currentPiece;
                        if (isValidMove(row, col).equals("Valid move")) {
                            for (int k = 0; k < 5; k++) {
                                for (int l = 0; l < 5; l++) {
                                    if (currentPiece.matrix[k][l] >= 1) {
                                        boardTiles[row - 2 + k][col - 2 + l].place = currentPlayerId;
                                    }
                                }
                            }
                            // Mark the piece as used and search for the next available piece
                            firstTurn[currentPlayerId] = false;
                            currentPlayer.usedPiece[currentPlayer.currentPieceIndex] = true;
                            while (currentPlayer.usedPiece[currentPlayer.currentPieceIndex] == true) {
                                currentPlayer.currentPieceIndex = (currentPlayer.currentPieceIndex + 1) % 21;
                            }
                            currentPlayer.currentPiece = currentPlayer.pieces[currentPlayer.currentPieceIndex];
                            currentPlayer.refreshGrid();
                            Blokus.setTurn((currentPlayerId + 1) % 4);
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), isValidMove(row, col), "Invalid move",
                                    JOptionPane.ERROR_MESSAGE);
                        }
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

    public void setPlayer(Player player) {
        currentPlayer = player;
    }

    private String isValidMove(int row, int col) {
        Piece currentPiece = currentPlayer.currentPiece;
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (currentPiece.matrix[k][l] >= 1) {
                    if (row - 2 + k >= BOARD_SIDE || row - 2 + k < 0 || col - 2 + l >= BOARD_SIDE || col - 2 + l < 0) {
                        return "Out of bounds!";
                    }
                }
            }
        }
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (currentPiece.matrix[k][l] >= 1 && boardTiles[row - 2 + k][col - 2 + l].place != -1) {
                    return "Cannot place on top of other piece!";
                }
            }
        }
        if (firstTurn[currentPlayerId] == true) {
            Boolean edge = false;
            switch (currentPlayerId) {
            case 0:
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (currentPiece.matrix[k][l] >= 1 && row - 2 + k == 0 && col - 2 + l == 0) {
                            edge = true;
                            break;
                        }
                    }
                }
                break;

            case 1:
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (currentPiece.matrix[k][l] >= 1 && row - 2 + k == 0 && col - 2 + l == BOARD_SIDE - 1) {
                            edge = true;
                            break;
                        }
                    }
                }

                break;

            case 2:
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (currentPiece.matrix[k][l] >= 1 && row - 2 + k == BOARD_SIDE - 1 && col - 2 + l == 0) {
                            edge = true;
                            break;
                        }
                    }
                }

                break;

            case 3:
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        if (currentPiece.matrix[k][l] >= 1 && row - 2 + k == BOARD_SIDE - 1
                                && col - 2 + l == BOARD_SIDE - 1) {
                            edge = true;
                            break;
                        }
                    }
                }

                break;
            }
            if (edge == false) {
                return "First piece must be at a corner!";
            }
        }
        return "Valid move";
    }

    private void highlightTile(int row, int col, Color c) {
        try {
            boardTiles[row][col].setBackground(c);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Skipp
        }
    }

    private void revertAll() {
        for (int i = 0; i < Board.BOARD_SIDE; i++) {
            for (int j = 0; j < Board.BOARD_SIDE; j++) {
                boardTiles[i][j].revert();
            }
        }
    }

    public void render() {
        boardFrame.setVisible(true);
    }

    public void hide() {
        boardFrame.setVisible(false);
    }

}