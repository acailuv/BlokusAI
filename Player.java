import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {

    protected int score;
    protected Piece pieces[] = new Piece[21];
    protected int currentPieceIndex = 0;
    protected int lastPiece;
    protected Piece currentPiece;
    protected int[] pieceValue = new int[]{1, 2, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    protected int playerId;
    protected Boolean usedPiece[] = new Boolean[21];

    protected JFrame playerFrame                = new JFrame();
    protected JPanel playerPanel                = new JPanel(new BorderLayout());
    protected JPanel playerSelection            = new JPanel(new GridLayout(5,5));
    protected JPanel mirrorSelection            = new JPanel(new GridLayout(1,2));
    protected JPanel playerSelectionTiles[][]   = new JPanel[5][5];
    protected JButton playerControls[]          = new JButton[5];

    protected JFrame remainingPieceFrame        = new JFrame("Remaining Pieces");
    protected JPanel remainingPieceTiles[][]    = new JPanel[15][35];

    public Player(int playerId) {
        // Setting Player ID
        this.playerId = playerId;
        System.out.println("Created Player: " + Integer.toString(this.playerId));

        // Setting Title
        playerFrame.setTitle("Player #" + Integer.toString(playerId + 1));

        // Initializing Selection Tiles
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                playerSelectionTiles[i][j] = new JPanel();
                playerSelectionTiles[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                playerSelectionTiles[i][j].setBackground(Color.WHITE);
                playerSelection.add(playerSelectionTiles[i][j]);
            }
        }
        remainingPieceFrame.setLocation(0, 500);
        remainingPieceFrame.setResizable(false);
        remainingPieceFrame.setSize(700, 300);
        remainingPieceFrame.add(playerPanel);

        // Initializing Remaining Piece Frame
        remainingPieceFrame.setLayout(new GridLayout(15, 35));
        for (int i=0; i<15; i++) {
            for (int j=0; j<35; j++) {
                remainingPieceTiles[i][j] = new JPanel();
                remainingPieceTiles[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                remainingPieceTiles[i][j].setBackground(Color.WHITE);
                remainingPieceFrame.add(remainingPieceTiles[i][j]);
            }
        }

        // Initializing Player Control Buttons
        playerControls[0] = new JButton("<");
        playerControls[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPieceIndex = (currentPieceIndex+20)%21;
                while(usedPiece[currentPieceIndex] == true) {
                    currentPieceIndex = (currentPieceIndex+20)%21;
                }
                currentPiece = pieces[currentPieceIndex];
                refreshGrid();
            }
        });
        playerControls[1] = new JButton(">");
        playerControls[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPieceIndex = (currentPieceIndex+1)%21;
                while(usedPiece[currentPieceIndex] == true) {
                    currentPieceIndex = (currentPieceIndex+1)%21;
                }
                currentPiece = pieces[currentPieceIndex];
                refreshGrid();
            }
        });
        playerControls[2] = new JButton("Rotate");
        playerControls[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPiece.matrix = currentPiece.rotate();
                currentPiece.matrix = currentPiece.centralize(currentPiece.matrix);
                refreshGrid();
            }
        });
        playerControls[3] = new JButton("Horizontal Mirror");
        playerControls[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPiece.matrix = currentPiece.horizontalMirror(currentPiece.matrix);
                currentPiece.matrix = currentPiece.centralize(currentPiece.matrix);
                refreshGrid();
            }
        });
        playerControls[4] = new JButton("Vertical Mirror");
        playerControls[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPiece.matrix = currentPiece.verticalMirror(currentPiece.matrix);
                currentPiece.matrix = currentPiece.centralize(currentPiece.matrix);
                refreshGrid();
            }
        });
        // Set all used pieces as false
        for(int i=0; i<21; i++) {
            usedPiece[i] = false;
        }
        // Initializing Pieces
        for(int i=0; i<21; i++) {
            pieces[i] = new Piece(i);
        }
        currentPiece = pieces[currentPieceIndex];
        refreshGrid();

        // Initializing Player Frame Panel
        playerPanel.add(playerControls[0], BorderLayout.WEST);
        playerPanel.add(playerControls[1], BorderLayout.EAST);
        playerPanel.add(playerControls[2], BorderLayout.NORTH);
        mirrorSelection.add(playerControls[3], BorderLayout.SOUTH);
        mirrorSelection.add(playerControls[4], BorderLayout.SOUTH);
        playerPanel.add(mirrorSelection, BorderLayout.SOUTH);
        playerPanel.add(playerSelection, BorderLayout.CENTER);
        playerFrame.setResizable(false);
        playerFrame.setSize(500, 500);
        playerFrame.add(playerPanel);
    }

    public void refreshGrid() {
        currentPiece.matrix = currentPiece.centralize(currentPiece.matrix);
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                playerSelectionTiles[i][j].setBackground(Color.WHITE);
            }
        }
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if (currentPiece.matrix[i][j] >= 1) {
                    switch (this.playerId) {
                        case 0:
                        playerSelectionTiles[i][j].setBackground(Color.RED);
                        break;

                        case 1:
                        playerSelectionTiles[i][j].setBackground(Color.GREEN);
                        break;

                        case 2:
                        playerSelectionTiles[i][j].setBackground(Color.BLUE);
                        break;

                        case 3:
                        playerSelectionTiles[i][j].setBackground(Color.YELLOW);
                        break;
                    }
                }
            }
        }
    }
    protected Boolean usedAll() {
        for(int i = 0; i < usedPiece.length; i++) {
            if(usedPiece[i] == false) {
                return false;
            }
        }
        return true;
    }

    public void refreshRemainingPieces() {
        // Reset the remaining pieces panels
        for (int i=0; i<15; i++) {
            for (int j=0; j<35; j++) {
                remainingPieceTiles[i][j].setBackground(Color.WHITE);
            }
        }

        // Re-populate the panels with remaining pieces
        int currentRow = 0;
        int currentCol = 0;
        for (int id=0; id<21; id++) {
            if (!usedPiece[id]) {
                Integer currentMatrix[][] = pieces[id].centralize(pieces[id].matrix);
                for (int i=0; i<5; i++) {
                    for (int j=0; j<5; j++) {
                        if (currentMatrix[i][j] >= 1) {
                            remainingPieceTiles[currentRow + i][currentCol + j].setBackground(Color.RED);
                        }
                    }
                }
                if (currentCol+5 == 35) {
                    currentRow += 5;
                    currentCol = 0;
                } else {
                    currentCol += 5;
                }
            }
        }
    }

    public void render() {
        refreshRemainingPieces();
        remainingPieceFrame.setVisible(true);
        playerFrame.setVisible(true);
    }

    public void hide() {
        remainingPieceFrame.setVisible(false);
        playerFrame.setVisible(false);
    }

}