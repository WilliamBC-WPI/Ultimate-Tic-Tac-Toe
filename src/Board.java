import java.io.File;
import java.util.ArrayList;

public class Board {

    public Reader reader = new Reader();

    String firstPlayer;
    int playerOne = 1;
    int playerTwo = -1;
    int[][] board = new int[9][9];
    int[] mainBoard = new int[9];

    public Board() {
        for(int i = 0; i < 81; i++) {
            for(int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
        for(int i = 0; i < 9; i++) {
            mainBoard[i] = 0;
        }
    }

    public void writeToBoard(String player, int loc) {
        firstPlayer =

    }

    /**
     * A Board initializes with an ArrayList of length
     */
    /**
     * Gameboard constructor, creates an ArrayList of length 9 full of 2D Arrays,
     * each 2D array is 3x3 and full of 9 0's
     */
//    public Board() {
//        //Gameboard, Empty = 0, X = 1, O = 2
//        int[][] playSpace = new int[3][3];
//        for(int i = 0; i < 9; i++) {
//            int[][] spaces = new int[3][3];
//            for(int j = 0; j < spaces.length; j++) {
//                for(int k = 0; k < spaces[j].length; k++) {
//                    spaces[j][k] = 0;
//                }
//            }
//            gameBoard.add(spaces);
//        }
//
//    }
//
//    /**
//     * Function to print the gameBoard
//     * Grabs all 9 boards from gameBoard and prints them out
//     */
//    public void printBoard() {
//        for(int i = 0; i < this.gameBoard.size(); i++) {
//            System.out.println("Board #" + i);
//            int[][] looper = this.gameBoard.get(i);
//            int c = 0;
//            for(int j = 0; j < looper.length; j++) {
//                for(int k = 0; k < looper[j].length; k++) {
//                    c++;
//                    if(c % 3 == 0) {
//                        System.out.println(" | " + looper[j][k] + " |");
//                        if (c != 9) {
//                            System.out.println("-------------");
//                        }
//                    }
//                    else {
//                        System.out.print(" | " + looper[j][k]);
//                    }
//
//                }
//            }
//        }
//    }

    /**
     * Helper function for checkWinner, checks if 3 values are equal
     * @return
     */
    public boolean equals3(int a, int b, int c) {
        return a == b && b == c && a != 0;
    }

    /**
     * Function that checks a board to see if there is any winners.
     * @return
     * Int: 0 if no winner yet,
     * 1 if X wins,
     * 2 if O wins,
     * 3 if tie
     */
    public int checkWinner(int boardNumber) {
        int[][] testBoard = gameBoard.get(boardNumber);
        int winner = 0;

        //Horizontal
        for(int i = 0; i < 3; i++) {
            if(equals3(testBoard[i][0], testBoard[i][1], testBoard[i][2])) {
                winner = testBoard[i][0];
            }
        }

        //Vertical
        for(int i = 0; i < 3; i++) {
            if(equals3(testBoard[0][i], testBoard[1][i], testBoard[2][i])) {
                winner = testBoard[0][i];
            }
        }

        //Diagonal
        for(int i = 0; i < 3; i++) {
            if(equals3(testBoard[0][0], testBoard[1][1], testBoard[2][2])) {
                winner = testBoard[0][0];
            }
            if(equals3(testBoard[0][2], testBoard[1][1], testBoard[2][0])) {
                winner = testBoard[0][0];
            }
        }

        //Checks for a full board with no winners, would result in a tie
        //I'm not sure if you can tie in Ultimate Tic-Tac-Toe but this is just in case
        int openSpots = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(testBoard[i][j] ==  0) {
                    openSpots++;
                    //If open spots == 0 and no winner, return tie.
                    if(openSpots == 9 && winner == 0) {
                        winner = 3;
                    }
                }
            }
        }
        return winner;
    }

}
