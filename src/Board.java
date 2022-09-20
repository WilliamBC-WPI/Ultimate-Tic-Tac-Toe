import java.util.ArrayList;

public class Board {


    ArrayList<int[][]> gameBoard = new ArrayList<>();

    /**
     * A Board initializes with an ArrayList of length
     */
    /**
     * Gameboard constructor, creates an ArrayList of length 9 full of 2D Arrays,
     * each 2D array is 3x3 and full of 9 0's
     */
    public Board() {
        //Gameboard, Empty = 0, X = 1, O = 2
        int[][] playSpace = new int[3][3];
        for(int i = 0; i < 9; i++) {
            int[][] spaces = new int[3][3];
            for(int j = 0; j < spaces.length; j++) {
                for(int k = 0; k < spaces[j].length; k++) {
                    spaces[j][k] = 0;
                }
            }
            gameBoard.add(spaces);
        }

    }

    /**
     * Function to print the gameBoard
     * Grabs all 9 boards from gameBoard and prints them out
     */
    public void printBoard() {
        for(int i = 0; i < this.gameBoard.size(); i++) {
            System.out.println("Board #" + i);
            int[][] looper = this.gameBoard.get(i);
            int c = 0;
            for(int j = 0; j < looper.length; j++) {
                for(int k = 0; k < looper[j].length; k++) {
                    c++;
                    if(c % 3 == 0) {
                        System.out.println(" | " + looper[j][k] + " |");
                        if (c != 9) {
                            System.out.println("-------------");
                        }
                    }
                    else {
                        System.out.print(" | " + looper[j][k]);
                    }

                }
            }
        }
    }

    /**
     * Helper function for checkWinner, checks if 3 values are equal
     * @return
     */
    public boolean equals3() { }

    /**
     * Function that checks a board to see if there is any winners.
     */
//    public boolean checkWinner(int boardNumber) {
//        gameBoard.get(boardNumber);
//        boolean winner = false;
//
//
//        int openSpots = 0;
//
//    }

}
