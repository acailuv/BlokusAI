public class Gui {

    public Player players[];
    protected Board board;

    public Gui(Player[] players, Board board) {
        this.players = players;
        this.board = board;
    }

    public void renderBoard() {
        board.render();
    }

    public void renderPlayer(int playerId) {
        players[playerId].render();
        board.setPlayer(players[playerId]);
        board.currentPlayerId = playerId;
    }

    public void hidePlayers() {
        for (Player p : players) {
            if (p.playerFrame.isVisible()) {
                p.hide();
            }
        }
    }

}