public class Game {

    Player player1;
    Player player2;

    public Game(String player1Name, String player2Name) {
        player1 = new Player(1, player1Name);
        player2 = new Player(-1, player2Name);
        Board.initializeBoards();
    }

    /**
     * @param playerNumber Number of the player playing
     * @param locationToPlay An integer array where the first element is the local board to play in,
     *                       and the second element is the position on the board to play
     */
    public void makeMove(int playerNumber, int[] locationToPlay) {
        Board.boardArray[locationToPlay[0]][locationToPlay[1]] = playerNumber;
    }
}
