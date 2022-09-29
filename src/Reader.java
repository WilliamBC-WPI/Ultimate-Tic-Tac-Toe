import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {

    //To figure out who goes first, a function must be able to find the .go file in the directory. Player 1 will get that name and
    //player 2 will get their name, the remaining name from the first_four_moves file

//    String initialMovesFile = "first_four_moves";
//    String filename = "move_file";

    public Reader() {

    }


    public void readFirstMoves(Board gb) throws FileNotFoundException {
        File initialMove = new File("/Users/will/Documents/Intro to AI Projects/uttt_referee_v4/first_four_moves");
        if (initialMove.exists()) {
            Scanner fileReader = new Scanner(initialMove);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String player = getPlayer(data);
                int location = getLocation(data);
                gb.writeToBoard(player, location);
                //TODO function call to: writeToBoard(String player, int location)
                //Adds a specified players move to the board
            }
        }
    }

    /**
     * getPlayer(String S)
     * @Param: S is a string representing a turn ex: "Zane 5 4"
     * @Return: String representing a player ex: "Zane"
     */
    public String getPlayer(String s) {
        String playerID = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                playerID = s.substring(0, i);
            }
        }
        return playerID;
    }


    /**
     * getLocation(String S)
     * @Param: S is a string representing a turn ex: "Zane 5 4"
     * @Return: Integer x location on board where 0<x<80
     */
    public int getLocation(String s) {
        int mbLoc = -1;
        int boardLoc = -1;
        int returnLocation;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                mbLoc = Integer.parseInt(s.substring(i + 1, i + 2));
                boardLoc = Integer.parseInt(s.substring(s.length() - 1));
            }
        }
        if(mbLoc > -1) {
            returnLocation = mbLoc * 9 + boardLoc;
            return returnLocation;
        }
        else {
            return -1;
        }
    }


    public String getTurn() throws IOException {
        File initialMove = new File("/Users/will/Documents/Intro to AI Projects/uttt_referee_v4/first_four_moves");
        String whosTurn = "";
        if(initialMove.exists()) {
            Scanner fileReader = new Scanner(initialMove);

            String playerOne = "";
            String playerTwo = "";


            for(int i = 0; i < 2; i++) {
                if(i == 0) {
                    playerOne = getPlayer(fileReader.nextLine());
                } else {
                    playerTwo = getPlayer(fileReader.nextLine());
                }
            }

            try {
                File directory = new File("/Users/will/Documents/Intro to AI Projects/uttt_referee_v4");
                String[] flist = directory.list();
                int flag = 0;
                if(flist == null) {
                    System.out.println("Empty directory");
                    System.exit(0);
                }
                else {
                    for(int i = 0; i < flist.length; i++) {
                        String currentFileName = flist[i];
                        if(currentFileName == playerOne + ".go") {
                            whosTurn = playerOne;
                        }
                        else if(currentFileName == playerTwo + ".go") {
                            whosTurn = playerTwo;
                        }
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return whosTurn;
    }


}
