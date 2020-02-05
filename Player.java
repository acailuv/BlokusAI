import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Player {

    protected int score;
    protected Piece pieces[] = new Piece[21];
    protected int currentPieceIndex = 0;
    protected Piece currentPiece;
    protected int playerId;

    protected JFrame playerFrame                = new JFrame();
    protected JPanel playerPanel                = new JPanel(new BorderLayout());
    protected JPanel playerSelection            = new JPanel(new GridLayout(5,5));
    protected JPanel mirrorSelection            = new JPanel(new GridLayout(1,2));
    protected JPanel playerSelectionTiles[][]   = new JPanel[5][5];
    protected JButton playerControls[]          = new JButton[5];

    public Player(int playerId) {
        // Setting Player ID
        this.playerId = playerId;
        System.out.println("Created Player: " + Integer.toString(this.playerId));

        // Setting Title
        playerFrame.setTitle("Player #" + Integer.toString(playerId));

        // Initializing Selection Tiles
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                playerSelectionTiles[i][j] = new JPanel();
                playerSelectionTiles[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                playerSelectionTiles[i][j].setBackground(Color.WHITE);
                playerSelection.add(playerSelectionTiles[i][j]);
            }
        }

        // Initializing Player Control Buttons
        playerControls[0] = new JButton("<");
        playerControls[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPieceIndex = (currentPieceIndex+20)%21;
                currentPiece = pieces[currentPieceIndex];
                refreshGrid();
            }
        });
        playerControls[1] = new JButton(">");
        playerControls[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentPieceIndex = (currentPieceIndex+1)%21;
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
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                playerSelectionTiles[i][j].setBackground(Color.WHITE);
            }
        }
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if (currentPiece.matrix[i][j] >= 1) {
                    switch (this.playerId) {
                        case 1:
                        playerSelectionTiles[i][j].setBackground(Color.RED);
                        break;

                        case 2:
                        playerSelectionTiles[i][j].setBackground(Color.GREEN);
                        break;

                        case 3:
                        playerSelectionTiles[i][j].setBackground(Color.BLUE);
                        break;

                        case 4:
                        playerSelectionTiles[i][j].setBackground(Color.YELLOW);
                        break;
                    }
                }
            }
        }
    }

    public void render() {
        playerFrame.setVisible(true);
    }

    public void hide() {
        playerFrame.setVisible(false);
    }

}