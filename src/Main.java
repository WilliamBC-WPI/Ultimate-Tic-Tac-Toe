import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

/**
 *
 * The AI has been improved by increasing its depth, previously the AI was on depth 2
 * but now is on depth 7. The AI also have an improved evaluation function.
 */
public class Main {
    public static Game game;
    public static final String FIRST_FOUR_MOVES = "first_four_moves";
    public static final String MOVES_PATH = "move_file";
    public static final String FILEPATH = "uttt_referee_v7";
    public static final String END_GAME_PATH = "end_game";
    static String lastMove = "";

    public static void main(String[] args){
        Main.startGame(args[0]);
    }

    public static void startGame(String playerName) {
        game = new Game(playerName, Reader.getOtherPlayerName());

        playFirstFourMoves();
       while (!Reader.gameOver()) {
            turn(game);
        }
        System.out.println("GAME ENDED");
    }

    public static void turn(Game game) {
        File movesFile = new File(Main.MOVES_PATH);
        File firstFourMovesFile = new File(FIRST_FOUR_MOVES);
        ArrayList<String> moveList = new ArrayList<>();

        if(Reader.doesFileContain(firstFourMovesFile)) {
            moveList = Reader.readFile(FIRST_FOUR_MOVES);
            lastMove = moveList.get(moveList.size()-1);
        }

        if (Reader.ourTurn()) {
            if (Reader.doesFileContain(movesFile)) {
                lastMove = Reader.readFile(MOVES_PATH).get(0);
            }
            int[] locationToPlay = {Reader.parseMove(lastMove)[1], Reader.parseMove(lastMove)[2]};
            game.makeMove(game.player2.playerNumber, locationToPlay);

            String userMove = "";

                int[] moveArray = Reader.parseMove(lastMove);
                int[] bestMoveArray = Minimax.findBestMove(Board.boardArray, moveArray[2]);
                System.out.println("the best move array is" + Arrays.toString(bestMoveArray));
                userMove = game.moveArrayToString(bestMoveArray);

            locationToPlay = new int[]{Reader.parseMove(userMove)[1], Reader.parseMove(userMove)[2]};
            game.makeMove(game.player1.playerNumber, locationToPlay);
            Reader.writeToFile(userMove);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                //break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            while (!Reader.ourTurn()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    //break;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void playFirstFourMoves() {
        ArrayList<String> firstFourMoves = Reader.readFile(FIRST_FOUR_MOVES);
        for (int i = 0; i < firstFourMoves.size(); i++) {
            int playerNumber = Reader.parseMove(firstFourMoves.get(i))[0];
            int[] locationToPlay = {Reader.parseMove(firstFourMoves.get(i))[1],
                    Reader.parseMove(firstFourMoves.get(i))[2]};
            game.makeMove(playerNumber, locationToPlay);
        }
    }
}