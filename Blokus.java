public class Blokus {

    public static final int MAX_PLAYER = 4;
    protected static Player players[] = new Player[4];
    protected static Board board      = new Board();
    protected static Gui mainGui;     
    public static void main(String args[]) {
        System.out.println("Blokus is ON!");
        // Initializing Players
        for (int i=0; i<MAX_PLAYER; i++) {
            players[i] = new Player(i+1);
        }
        mainGui = new Gui(players, board);
        mainGui.renderBoard();

        // Demo
        setTurn(3);
    }

    public static void setTurn(int playerId) {
        mainGui.hidePlayers();
        mainGui.renderPlayer(playerId);
    }

}