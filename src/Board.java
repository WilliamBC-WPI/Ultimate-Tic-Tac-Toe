import java.util.Arrays;

public class Board {

    static int[][] boardArray = new int[9][9];
    static int[] mainBoard = new int[9];

    public static void initializeBoards() {
        for (int i = 0; i < mainBoard.length; i++) {
            mainBoard[i] = 0;
        }

        for (int i = 0; i < boardArray.length; i++) {
            for (int j = 0; j < boardArray[i].length; j++) {
                boardArray[i][j] = 0;
            }
        }
    }

    public static void printBoardArray() {
        System.out.println(Arrays.deepToString(boardArray)
                .replace("], ", "]\n")
                .replace("[[", "[")
                .replace("]]", "]"));
        System.out.println("------------------------------");
    }
}
