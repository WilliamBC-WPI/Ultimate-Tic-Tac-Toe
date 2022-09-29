import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static Game game;

    public static final String REFEREEPATH = "../Referee/uttt_referee_v4/first_four_moves";

    public static void main(String[] args){
        Main.startGame();
    }

    public static void startGame() {
        game = new Game("Bob", "Zane");
        playFirstFourMoves();
    }

    public static void playFirstFourMoves() {
        ArrayList<String> firstFourMoves = Reader.readFile(REFEREEPATH);
        for (int i = 0; i < firstFourMoves.size(); i++) {
            int playerNumber = Reader.parseMove(firstFourMoves.get(i))[0];
            int[] locationToPlay = {Reader.parseMove(firstFourMoves.get(i))[1],
                    Reader.parseMove(firstFourMoves.get(i))[2]};
            game.makeMove(playerNumber, locationToPlay);
            Board.printBoardArray();
        }
    }
}