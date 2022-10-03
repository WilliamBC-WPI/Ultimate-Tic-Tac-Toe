<<<<<<< Updated upstream
import java.util.ArrayList;
=======
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
>>>>>>> Stashed changes

public class Main {
    public static Game game;

<<<<<<< Updated upstream
    public static final String FIRST_FOUR_MOVES = "../Referee/uttt_referee_v4/first_four_moves";
    public static final String MOVES_PATH = "../Referee/uttt_referee_v4/move_file";
=======
    //public static final String REFEREEPATH = "../Referee/uttt_referee_v4/first_four_moves";
    public static final String REFEREEPATH = "/Users/will/Documents/GitHub/Ultimate Tic-Tac-Toe/Referee/uttt_referee_v4/first_four_moves";
    public static final String MOVEPATH =  "/Users/will/Documents/GitHub/Ultimate Tic-Tac-Toe/Referee/uttt_referee_v4/move_file";
    public static final String FILEPATH = "/Users/will/Documents/GitHub/Ultimate Tic-Tac-Toe/Referee/uttt_referee_v4";
>>>>>>> Stashed changes

    public static void main(String[] args){
        Main.startGame();
//        Reader.writeToFile("Z 3 4");
//        Reader.writeToFile("W 5 6");
    }

    public static void startGame() {
        game = new Game("Us", "Them");
        System.out.println(game.moveArrayToString(new int[]{1, 2, 5}));
        playFirstFourMoves();
        for(int i = 0; i < 10; i++) {
            Main.turn(game);
        }
    }

    public static void turn(Game game) {
        String playerOne = game.player1.playerName;
        String playerTwo = game.player2.playerName;
        //If your turn, write to board
        //If opponents turn, wait till last line of move_list is from opponent and read from board
        int currentPlayer = Reader.whosTurn();

            //if theres a move in the move file, were going second
            //if not youre going first
        if()
        ArrayList<String> moveList = Reader.readFile(REFEREEPATH);
        String lastMove = moveList.get(moveList.size()-1);
        String whoMadeLastMoveName = Reader.getPlayerNameFromMoveString(lastMove);

        //System.out.println("CurrentPlayer: " + currentPlayer);
        if(currentPlayer == 1) {
            String userMove = Reader.readMove();
            int[] locationToPlay = Reader.parseMove(userMove);
            game.makeMove(game.player1.playerNumber, locationToPlay);
            Reader.writeToFile(userMove);
        }
        else if(currentPlayer == -1) {
            ArrayList<String> allMoves = Reader.readFile(REFEREEPATH);
            //Maybe need to constantly be re initialize moveList
            while(whoMadeLastMoveName != playerTwo) {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    break;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int[] locationToPlay = new int[2];//Minimax call, minimizing
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