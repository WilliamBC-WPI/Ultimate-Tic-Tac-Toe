import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    //To figure out who goes first, a function must be able to find the .go file in the directory. Player 1 will get that name and
    //player 2 will get their name, the remaining name from the first_four_moves file

//    String initialMovesFile = "first_four_moves";
//    String filename = "move_file";

    public static ArrayList<String> readFile(String pathname){
        File firstFourMovesFile = new File(pathname);
        ArrayList<String> output = new ArrayList<>();
        if (firstFourMovesFile.exists()) {
            try {
                Scanner fileReader = new Scanner(firstFourMovesFile);
                while (fileReader.hasNextLine()) {
                    String nextLine = fileReader.nextLine();
                    output.add(nextLine);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found");
            }
        } else {
            System.out.println("That don't exist my guy!");
        }
        return output;
    }

    public static String getPlayerName(int playerPosition) {
        playerPosition = playerPosition % 2;
        return Reader.readFile(Main.FIRST_FOUR_MOVES).get(playerPosition).split(" ")[0];
    }

    public static String getOtherPlayerName() {
        File movesFile = new File(Main.MOVES_PATH);
        if (movesFile.exists()) {
            try {
                Scanner fileReader = new Scanner(movesFile);
                if(fileReader.hasNext()) {
                    return getPlayerNameFromMoveString(fileReader.nextLine());
                } else {
                    return getPlayerName(1);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File not found my guy");
            }
        }

        return "AI";
    }

    public static String getPlayerNameFromMoveString(String moveString) {
        return moveString.split(" ")[0];
    }

    /**
     * @return An integer array where the first element is the player number, the second element is
     * the local board where the move should be played, and the last element is the position on
     * that board that we're playing on
     */
    public static int[] parseMove(String move) {
        int localBoardPosition = Integer.parseInt(move.split(" ")[1]);
        int positionToPlay = Integer.parseInt(move.split(" ")[2]);

        if (Main.game.player1.playerName.equals(getPlayerNameFromMoveString(move))) {
            int[] output = {1, localBoardPosition, positionToPlay};
            return output;
        } else {
            int[] output = {-1, localBoardPosition, positionToPlay};
            return output;
        }
    }

    public static void writeToMoveFile(String move) {
    }
}
