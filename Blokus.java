import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Blokus {

    public static final int MAX_PLAYER = 4;
    protected static Player players[] = new Player[4];
    protected static Board board = new Board();
    protected static Gui mainGui;

    public static void main(String args[]) {
        System.out.println("Blokus is ON!");
        // Initializing Players
        for (int i = 0; i < MAX_PLAYER; i++) {
            if (i != 0)
                players[i] = new AI(i);
            else
                players[i] = new Player(i);
        }
        mainGui = new Gui(players, board);
        mainGui.renderBoard();

        // Demo
        setTurn(0);
    }

    public static void setTurn(int playerId) {
        board.currentPlayerId = playerId;
        mainGui.hidePlayers();
        mainGui.renderPlayer(playerId);
        if (players[playerId] instanceof AI) {
            if (board.hasMove() == false) {
                JOptionPane.showMessageDialog(new JFrame(),
                "No more moves for player " + Integer.toString(board.currentPlayerId + 1) + "!", "Out of moves",
                JOptionPane.WARNING_MESSAGE);
                board.gameOver[board.currentPlayerId] = true;
            }
            board.boardTiles = ((AI) players[playerId]).nextMove(board);
            board.firstTurn[playerId] = false;
            board.nextPlayer();
        }
    }

    public static void gameFinished() {
        new Results(players);
    }
}