import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;

public class Main {
    public static Game game;
    public static final String FIRST_FOUR_MOVES = "../Referee/uttt_referee_v7/first_four_moves";
    public static final String MOVES_PATH = "../Referee/uttt_referee_v7/move_file";
    public static final String FILEPATH = "../Referee/uttt_referee_v7";

    public static void main(String[] args){
        Main.startGame();
    }

    public static void startGame() {
        game = new Game("Us", "Them");
        System.out.println(game.moveArrayToString(new int[]{1, 2, 5}));
        playFirstFourMoves();
        for (int i = 0; i < 10; i++) {
            Main.turn(game);
        }
    }

    public static void turn(Game game) {
        int currentPlayer = Reader.whosTurn();

        File movesFile = new File(Main.MOVES_PATH);
        String whoMadeLastMoveName = "";
        ArrayList<String> moveList = new ArrayList<>();

        if(Reader.doesFileContain(movesFile)) {
            moveList = Reader.readFile(FIRST_FOUR_MOVES);
            String lastMove = moveList.get(moveList.size()-1);
            whoMadeLastMoveName = Reader.getPlayerNameFromMoveString(lastMove);
        }

        if (Reader.ourTurn()) {
            String userMove = Reader.readMove();
            int[] locationToPlay = Reader.parseMove(userMove);
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

            String lastMove = Reader.readFile(MOVES_PATH).get(0);
            int[] locationToPlay = {Reader.parseMove(lastMove)[1], Reader.parseMove(lastMove)[2]};
            System.out.println("the location is " + Arrays.toString(locationToPlay));
            game.makeMove(game.player2.playerNumber, locationToPlay);
        }
    }

    public static void playFirstFourMoves() {
        ArrayList<String> firstFourMoves = Reader.readFile(FIRST_FOUR_MOVES);
        for (int i = 0; i < firstFourMoves.size(); i++) {
            int playerNumber = Reader.parseMove(firstFourMoves.get(i))[0];
            int[] locationToPlay = {Reader.parseMove(firstFourMoves.get(i))[1],
                    Reader.parseMove(firstFourMoves.get(i))[2]};
            game.makeMove(playerNumber, locationToPlay);
            Board.printBoardArray();
        }
    }
}