import java.util.ArrayList;
import java.util.Arrays;

public class Minimax {

    static double MAX_VALUE = Double.MAX_VALUE;
    static double MIN_VALUE = -Double.MAX_VALUE;

    static int player2Number = -1;
    static int player1Number = 1;

    public static boolean tallyBoard(int[] board, int tallyTotal) {
        if (board[0] + board[1] + board[2] == tallyTotal
                || board[3] + board[4] + board[5] == tallyTotal
                || board[6] + board[7] + board[8] == tallyTotal
                || board[0] + board[3] + board[6] == tallyTotal
                || board[1] + board[4] + board[7] == tallyTotal
                || board[2] + board[5] + board[8] == tallyTotal
                || board[0] + board[4] + board[8] == tallyTotal
                || board[2] + board[4] + board[6] == tallyTotal) {
            return true;
        }
        return false;
    }

    public static boolean tallyBoardByType(int[] board, int tallyTotal, VictoryType victoryType) {
        switch (victoryType) {
            case HORIZONTAL -> {
                if (board[0] + board[1] + board[2] == tallyTotal
                        || board[3] + board[4] + board[5] == tallyTotal
                        || board[6] + board[7] + board[8] == tallyTotal) {
                    return true;
                }
            }
            case VERTICAL -> {
                if (board[0] + board[3] + board[6] == tallyTotal
                        || board[1] + board[4] + board[7] == tallyTotal
                        || board[6] + board[7] + board[8] == tallyTotal) {
                    return true;
                }
            }
            case DIAGONAL -> {
                if (board[0] + board[4] + board[8] == tallyTotal
                        || board[2] + board[4] + board[6] == tallyTotal) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean someOtherTally(int[] board, int tallyTotalBase) {
        if((board[0] + board[1] == 2*tallyTotalBase && board[2] == -tallyTotalBase)
                || (board[1] + board[2] == 2*tallyTotalBase && board[0] == -tallyTotalBase)
                || (board[0] + board[2] == 2*tallyTotalBase && board[1] == -tallyTotalBase)
                || (board[3] + board[4] == 2*tallyTotalBase && board[5] == -tallyTotalBase)
                || (board[3] + board[5] == 2*tallyTotalBase && board[4] == -tallyTotalBase)
                || (board[5] + board[4] == 2*tallyTotalBase && board[3] == -tallyTotalBase)
                || (board[6] + board[7] == 2*tallyTotalBase && board[8] == -tallyTotalBase)
                || (board[6] + board[8] == 2*tallyTotalBase && board[7] == -tallyTotalBase)
                || (board[7] + board[8] == 2*tallyTotalBase && board[6] == -tallyTotalBase)
                || (board[0] + board[3] == 2*tallyTotalBase && board[6] == -tallyTotalBase)
                || (board[0] + board[6] == 2*tallyTotalBase && board[3] == -tallyTotalBase)
                || (board[3] + board[6] == 2*tallyTotalBase && board[0] == -tallyTotalBase)
                || (board[1] + board[4] == 2*tallyTotalBase && board[7] == -tallyTotalBase)
                || (board[1] + board[7] == 2*tallyTotalBase && board[4] == -tallyTotalBase)
                || (board[4] + board[7] == 2*tallyTotalBase && board[1] == -tallyTotalBase)
                || (board[2] + board[5] == 2*tallyTotalBase && board[8] == -tallyTotalBase)
                || (board[2] + board[8] == 2*tallyTotalBase && board[5] == -tallyTotalBase)
                || (board[5] + board[8] == 2*tallyTotalBase && board[2] == -tallyTotalBase)
                || (board[0] + board[4] == 2*tallyTotalBase && board[8] == -tallyTotalBase)
                || (board[0] + board[8] == 2*tallyTotalBase && board[4] == -tallyTotalBase)
                || (board[4] + board[8] == 2*tallyTotalBase && board[0] == -tallyTotalBase)
                || (board[2] + board[4] == 2*tallyTotalBase && board[6] == -tallyTotalBase)
                || (board[2] + board[6] == 2*tallyTotalBase && board[4] == -tallyTotalBase)
                || (board[4] + board[6] == 2*tallyTotalBase && board[2] == -tallyTotalBase)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if a normal tic tac toe game has been won
     * checks columns, rows & diagonals for victory
     * 
     * @param board
     * @return 1 or -1 if a specific player has won, returns 0 if no one has won
     */
    public static int checkWinCondition(int[] board) {
        if (tallyBoard(board, 3)) {
            return 1;
        } else if (tallyBoard(board, -3)) {
            return -1;
        }

        return 0;
    }

    /** Evaluates a position on a board and assigns that specific position a value
     *
     * @param board
     * @param position
     * @return
     */
    public static double evaluatePos(int[] board, int position) {
        board[position] = 1;
        double eval = 0;

        // heuristic values
        double[] points = new double[] { 0.2, 0.17, 0.2, 0.17, 0.22, 0.17, 0.2, 0.17, 0.2 };
        eval += points[position];

        if (tallyBoard(board, 2)) {
            eval += 1;
        }
        if (tallyBoard(board, 3)) {
            eval += 5;
        }

        board[position] = -1;
        if (tallyBoard(board, -3)) {
            eval+=2;
        }
        board[position] = 1;
        eval += checkWinCondition(board) * 15;
        board[position] = 0;

        return eval;
    }

    /**
     * Evaluates a board fairly - realEvaluateSquare HEURISTIC
     * 
     * @param board on basic Tic-Tac-Toe board
     */
    public static double evaluateBoard(int[] board) {
        double eval = 0;
        double[] points = { 0.2, 0.17, 0.2, 0.17, 0.22, 0.17, 0.2, 0.17, 0.2 }; // heuristic

        // tallies up the heuristic points for a board ?
        for (int i = 0; i < 9; i++) {
            eval -= board[i] * points[i];
        }

        // Decrease points for rival pairs
        if (tallyBoardByType(board, -2, VictoryType.HORIZONTAL)) {
            eval -= 6;
        }
        if (tallyBoardByType(board, -2, VictoryType.VERTICAL)) {
            eval -= 6;
        }
        if (tallyBoardByType(board, -2, VictoryType.DIAGONAL)) {
            eval -= 7;
        }

        // Decrease points for something
        if (someOtherTally(board, 1)) {
            eval -= 9;
        }

        // Add points for our pairs
        if (tallyBoardByType(board, 2, VictoryType.HORIZONTAL)) {
            eval += 6;
        }
        if (tallyBoardByType(board, 2, VictoryType.VERTICAL)) {
            eval += 6;
        }
        if (tallyBoardByType(board, 2, VictoryType.DIAGONAL)) {
            eval += 7;
        }

        // Add points for something
        if (someOtherTally(board, -1)) {
            eval += 9;
        }

        eval += checkWinCondition(board) * 12; // why tf is it 12

        return eval;
    }

    public static double evaluateGame(int[][] gameBoard, int currentBoard) {
        double eval = 0;
        ArrayList<Integer> mainBoard = new ArrayList<>();
        double[] evaluator = {1.4, 1, 1.4, 1, 1.75, 1, 1.4, 1, 1.4};

        for (int i = 0; i < 9; i++) {
            eval += evaluateBoard(gameBoard[i]) * 1.5 * evaluator[i];
            if (i == currentBoard) {
                eval += evaluateBoard(gameBoard[i])*evaluator[i];
            }
            int potentialWinner = checkWinCondition(gameBoard[i]);
            eval -= potentialWinner * evaluator[i];
            mainBoard.add(potentialWinner);
        }

        int[] mainBoardArray = mainBoard.stream().mapToInt(i->i).toArray();

        eval += checkWinCondition(mainBoardArray) * 5000;
        eval += evaluateBoard(mainBoardArray)*150;

        return eval;
    }

    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    public static double minimax(int depth, int boardToPlayOn, boolean maximizingUs, int [][] board, double alpha, double beta) {
        double score = evaluateGame(board, boardToPlayOn);

        if (depth <= 0 || Math.abs(score) > 5000) {
            return score;
        }

        if (boardToPlayOn != -1 && checkWinCondition(board[boardToPlayOn]) != 0) {
            boardToPlayOn = -1;
        }

        if (boardToPlayOn != -1 && !contains(board[boardToPlayOn], 0)) {
            boardToPlayOn = -1;
        }

        if(maximizingUs) {
            double maxEval = MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                double eval = MIN_VALUE;

                if (boardToPlayOn == -1) {
                    for (int j = 0; j < 9; j++) {
                        if (checkWinCondition(board[i]) == 0) {
                            if (board[i][j] == 0) {
                                board[i][j] = 1;
                                eval = minimax(depth - 1, j, false, board, alpha, beta);

                                board[i][j] = 0;
                            }
                            maxEval = Math.max(maxEval, eval);
                            alpha = Math.max(alpha, maxEval);
                        }
                    }
                    if(beta <= alpha) {
                        break;
                    }
                } else {
                    if (board[boardToPlayOn][i] == 0) {
                        board[boardToPlayOn][i] = 1;
                        eval = minimax(depth - 1, i, false, board, alpha, beta);

                        board[boardToPlayOn][i] = 0;
                    }

                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(maxEval, alpha);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }

            return maxEval;
        } else {
            double minEval = MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                double eval = MIN_VALUE;
                if (boardToPlayOn == -1) {
                    for (int j = 0; j < 9; j++) {
                        if (checkWinCondition(board[i]) == 0) {
                            if (board[i][j] == 0) {
                                board[i][j] = -1;
                                eval = minimax(depth - 1, j, true, board, alpha, beta);

                                board[i][j] = 0;
                            }
                            minEval = Math.min(minEval, eval);
                            beta = Math.min(beta, minEval);
                        }
                    }
                    if (beta < alpha) {
                        break;
                    }
                } else {
                    if (board[boardToPlayOn][i] == 0) {
                        board[boardToPlayOn][i] = -1;
                        eval = minimax(depth - 1, i, true, board, alpha, beta);

                        board[boardToPlayOn][i] = 0;
                    }

                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, minEval);
                    if (beta < alpha) {
                        break;
                    }
                }
            }

            return minEval;
        }
    }

    public static int[] findBestMove(int[][] board, int boardToPlayOn) {
        double bestValue = MIN_VALUE;
        int[] bestMoveArray = {-1, -1};

        if (boardToPlayOn != -1 && checkWinCondition(board[boardToPlayOn]) != 0) {
            System.out.println("Board is " + Arrays.toString(board[boardToPlayOn]));
            System.out.println("Game thinks win condition is " + checkWinCondition(board[boardToPlayOn]));
            boardToPlayOn = -1;
            System.out.println("Game thinks board is won in find best move");
        }

        if (boardToPlayOn != -1 && !contains(board[boardToPlayOn], 0)) {
            boardToPlayOn = -1;
            System.out.println("Game thinks board is full in find best move");
        }

        for (int i = 0; i < 9; i++) {
            if (boardToPlayOn == -1) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                        if (checkWinCondition(board[i]) == 0) {
                            double moveValue = minimax(2, boardToPlayOn, true, board, MIN_VALUE, MAX_VALUE);

                            System.out.println("move value is " + moveValue);

                            if (moveValue > bestValue) {
                                bestMoveArray = new int[]{1, i, j};
                                bestValue = moveValue;
                                System.out.println(bestValue);
                            }

                        }
                        board[i][j] = 0;
                    }
                }
            } else {
                if (board[boardToPlayOn][i] == 0) {
                    board[boardToPlayOn][i] = 1;

                    double moveValue = minimax(2, boardToPlayOn, true, board, MIN_VALUE, MAX_VALUE);

                    System.out.println("move value is " + moveValue);

                    if (moveValue > bestValue) {
                        bestMoveArray = new int[]{1, boardToPlayOn, i};
                        bestValue = moveValue;
                        System.out.println("best value is " + bestValue);
                    }

                    board[boardToPlayOn][i] = 0;
                }
            }
        }

        if (bestMoveArray[1] == -1) {
            for (var i = 0; i < 9; i++) {
                if (boardToPlayOn == -1) {
                    for (int j = 0; j < 9; j++) {
                        if (board[i][j] == 0 && checkWinCondition(board[i]) == 0) {
                            bestMoveArray = new int[]{1, i, j};
                            break;
                        }
                    }
                } else {
                    if (board[boardToPlayOn][i] == 0 && checkWinCondition(board[i]) == 0) {
                        bestMoveArray = new int[]{1, boardToPlayOn, i};
                        break;
                    }
                }
            }
        }
        return bestMoveArray;
    }
}