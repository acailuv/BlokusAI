public class AI extends Player {
    private int BOARD_SIDE = 20;

    public AI(int playerId) {
        super(playerId);
    }

    // Under Construction...
    public Tile[][] nextMove(Board board) {
        Boolean moved = false;
        for (int i = 0; i < BOARD_SIDE; i++) {
            for (int j = 0; j < BOARD_SIDE; j++) {
                for (int ii = 20; ii >= 0; ii--) {
                    if (usedPiece[ii] == true) {
                        continue;
                    }
                    Piece check = pieces[ii];
                    for (int x = 0; x < 4; x++) {
                        check.rotate();
                        // System.out.print(board.isValidMove(i, j, check));
                        // System.out.println(" " + Integer.toString(ii));
                        if (board.isValidMove(i, j, check).equals("Valid move")) {
                            moved = true;
                            for (int k = 0; k < 5; k++) {
                                for (int l = 0; l < 5; l++) {
                                    if (check.matrix[k][l] >= 1) {
                                        board.boardTiles[i - 2 + k][j - 2 + l].place = board.currentPlayerId;
                                    }
                                }
                            }
                        }
                    }
                    if (moved == true) {
                        return board.boardTiles;
                    }
                    check.horizontalMirror(check.matrix);
                    for (int x = 0; x < 4; x++) {
                        check.rotate();
                        // System.out.print(board.isValidMove(i, j, check));
                        // System.out.println(" " + Integer.toString(ii));
                        if (board.isValidMove(i, j, check).equals("Valid move")) {
                            moved = true;
                            for (int k = 0; k < 5; k++) {
                                for (int l = 0; l < 5; l++) {
                                    if (check.matrix[k][l] >= 1) {
                                        board.boardTiles[i - 2 + k][j - 2 + l].place = board.currentPlayerId;
                                    }
                                }
                            }
                        }
                    }
                    if(moved == true) {
                        return board.boardTiles;
                    }
                }
            }
        }
        if (moved == false) {
            System.out.println("huh?");
        }
        return board.boardTiles;
    }

}