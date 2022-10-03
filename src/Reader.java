import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {

    //To figure out who goes first, a function must be able to find the .go file in the directory. Player 1 will get that name and
    //player 2 will get their name, the remaining name from the first_four_moves file
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
        if(doesFileContain(movesFile)) {
            try {
                Scanner fileReader = new Scanner(movesFile);
                return getPlayerNameFromMoveString(fileReader.nextLine());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            return getPlayerName(1);
        }
    }

    public static Boolean doesFileContain(File file) {
        if(file.exists()) {
            try {
                Scanner fileReader = new Scanner(file);
                if (fileReader.hasNext()) {
                    return true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File Not found");
            }
        }
        else {
            System.out.println("File doesnt exist!!");
        }
        return false;
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

    public static void writeToFile(String s) {
        try {
            File moveFile = new File(Main.MOVES_PATH);
            FileWriter writer = new FileWriter(moveFile, false);
            if (s.length() > 0) {
                writer.write(s);
                writer.close();
            } else {
                System.out.println("Empty String");
            }
        } catch (IOException e) {
            System.out.println("File to write to does not exist my guy!");
        }
    }

    public static String getGoFileName() {
        return "../Referee/uttt_referee_v7/" + Main.game.player1.playerName+ ".go";
    }

    public static boolean ourTurn() {
        File goFile = new File(getGoFileName());
        if (goFile.exists()){
            return  true;
        }
        return false;
    }

    public static boolean gameOver() {
        File endFile = new File(Main.END_GAME_PATH);
        if (endFile.exists()) {
            return true;
        }
        return false;
    }

    public static int whosTurn() {
        int whosTurn = 0;
        String playerOne = Main.game.player1.playerName;
        String playerTwo = Main.game.player2.playerName;
        try {
            File directory = new File(Main.FILEPATH);
            String[] flist = directory.list();
            int flag = 0;
            if (flist == null) {
                System.out.println("Empty directory");
                System.exit(0);
            } else {
                for (int i = 0; i < flist.length; i++) {
                    String currentFileName = flist[i];
                    if (currentFileName.equals(playerOne + ".go")) {
                        whosTurn = Main.game.player1.playerNumber;

                    } else if (currentFileName.equals(playerTwo + ".go")) {
                        whosTurn = Main.game.player2.playerNumber;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return whosTurn;
    }

    public static String readMove() {
        Scanner userScanner = new Scanner(System.in);
        System.out.println("Enter User Move in 'Name # #' format: ");
        return userScanner.nextLine();
    }

}
