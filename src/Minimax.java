import java.util.ArrayList;
import java.util.HashMap;

public class Minimax {

    static HashMap<String, Double> evals = new HashMap<String, Double>();

    static int RUNS = 0;
    static int MOVES = 0;

    static int player2Number = -1;
    static int player1Number = 1;

    static int currentTurn = 1;
    //static int bestMove = -1;
    public static int currentBoard = 4;
    Integer[] bestScore = new Integer[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

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

    /**
     * @param position
     * @param //boardToPlay // int
     * @param depth
     * @param alpha
     * @param beta        // pruning
     * @param maxPlayer
     * @return HashMap<String, Integer>
     */
    public static HashMap<String, Double> minimax(int[][] position, int boardTurn, int depth, Double alpha, Double beta,
                                                   boolean maxPlayer) {
        RUNS++;
        //HashMap evals = new HashMap<String, Integer>();

        double possibleScore = -1;
        Double infinity = Double.MAX_VALUE;
        Double negInf = Double.MIN_VALUE;

        double calc = evaluateGame(position, boardTurn);

        String xC = "X";
        String yT = "Y";

        if (depth <= 0 || Math.abs(calc) > 5000) {
            evals.put(xC, calc);
            evals.put(yT, possibleScore);

            return evals;
        }
        // if board to play on = -1, play on any board
        if ((boardTurn != -1) && (checkWinCondition(position[boardTurn]) != 0)) {
            boardTurn = -1;
        }

        // if board is full
        boolean contains = false; //= IntStream.of(position[boardTurn]).anyMatch(x -> x == 0);// https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java

        if(boardTurn >= 0) {
            for(int i = 0; i < position[boardTurn].length; i++) {
                if(position[boardTurn][i] == 0) {
                    contains = true;
                }
            }
        }

        if (boardTurn != -1 && !(contains)) {
            boardTurn = -1;
        }

        if (maxPlayer) {
            Double maxEval = negInf;
            for (int x = 0; x < 9; x++) {
                Double evalu = negInf;
                // if you can play on any board, check every position
                if (boardTurn == -1) {
                    for (int y = 0; y < 9; y++) {
                        // except the boards that have victories
                        if (checkWinCondition(position[x]) == 0) {
                            if (position[x][y] == 0) {
                                position[x][y] = player2Number;
                                evals = minimax(position, y, depth - 1, alpha, beta, false);
                                try {
                                    evalu = evals.get(xC);
                                    System.out.println("Evals: " + evals.get(evalu));
                                } catch (NullPointerException e) {
                                    evalu = Double.MIN_VALUE;
                                    evals.put("X", evalu);

                                    System.out.println("Getting minval???");
                                }


                                position[x][y] = 0;
                            }
                            if (evalu > maxEval) {
                                maxEval = evalu;
                                evals.put("X", maxEval);
                                possibleScore = x;
                            }
                            alpha = Math.max(alpha, evalu);
                        }
                    }
                    // alphabeta pruning
                    if (beta <= alpha) {
                        break;
                    }
                    // if specific board to play on, iterate through it's squares
                } else {
                    if (position[boardTurn][x] == 0) {
                        position[boardTurn][x] = player2Number;
                        evals = minimax(position, x, depth - 1, alpha, beta, false);
                        position[boardTurn][x] = 0;
                    }

                    Double play = evals.get(xC); // var blop = evalut.mE;
                    if (play > maxEval) {
                        maxEval = play;
                        evals.put(yT, possibleScore);
                        //outputMap.put("Y", maxEval);
                        // saves the board you should play on, so that it can be passed on when AI can
                        // play on any board
                        try {
                            possibleScore = evals.get(yT); // tmpPlay = evalut.tP;
                        } catch(NullPointerException e) {
                            System.out.println("Templay in hashmap is null");
                        }
                    }
                    alpha = Math.max(alpha, play);
                    // alphabeta pruning
                    if (beta <= alpha) {
                        break;
                    }
                }
//                outputMap.put("X", maxEval);
//                outputMap.put("Y", templay);
                evals.put("X", maxEval);
                evals.put("Y", possibleScore);
//                System.out.println("Test Here:");
                System.out.println("MaxEval: " + maxEval);
                System.out.println("Templay: " + possibleScore);
                return (evals);
            }
        } else { // same but for minimizing player
            for (int x = 0; x < 9; x++) {
                Double evaluo = infinity;
                if (boardTurn == -1) {
                    for (int y = 0; y < 9; y++) {
                        // except the boards that have victories
                        if (checkWinCondition(position[x]) == 0) {
                            if (position[x][y] == 0) {
                                position[x][y] = player1Number;
                                evals = minimax(position, y, depth - 1, alpha, beta, false);
                                try {
                                    evaluo = evals.get(xC);
                                    evals.put("X", evaluo);
                                } catch (NullPointerException e) {
                                    evaluo = Double.MIN_VALUE;
                                    evals.put("X", evaluo);
                                }

                                position[x][y] = 0;
                            }
                            if (evaluo > infinity) {
                                negInf = evaluo;
                                possibleScore = x;
                                evals.put("Y", possibleScore);

                            }
                            beta = Math.min(beta, evaluo);
                        }
                    }
                    // alphabeta pruning
                    if (beta <= alpha) {
                        break;
                    } else {
                        if (position[boardTurn][x] == 0) {
                            position[boardTurn][x] = player1Number;
                            evals = minimax(position, x, depth - 1, alpha, beta, true);
                            evaluo = evals.get(xC);
                            position[boardTurn][x] = 0;
                        }
                        Double blay = evaluo;
                        if (blay < negInf) {
                            negInf = blay;
                            possibleScore = evals.get(yT);
                            evals.put("Y", possibleScore);
                        }
                        beta = Math.min(beta, blay);
                        // alphabeta pruning
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
        }
        return (evals);
    }

    public static int[] boardToRow(int[][] board, int row) {
        int[] rtnAr = new int[board[row].length];
        for(int i = 0; i < board[row].length; i++) {
            rtnAr[i] = board[row][i];
        }
        return rtnAr;
    }

    public static void game() {
        int[][] boardArrayCopy = Board.boardArray.clone();
        Double[] bestScore = new Double[]{Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
        if(Reader.ourTurn()) {
            int bestMove = -1;
            bestScore = new Double[]{Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};

            int[] checkWinners = new int[9];
            int count = 0;
            for(int i = 0; i < Board.boardArray.length; i++) {
                for(int j = 0; j < Board.boardArray[i].length; j++) {
                    checkWinners[j] = Board.boardArray[i][j];
                }
                if(checkWinCondition(checkWinners) == 0) {
                    for(int t = 0; t < checkWinners.length; t++) {
                        if(checkWinners[t] == 0) {
                            count++;
                        }
                    }
                }
            }

            if(currentBoard == -1 || checkWinCondition(boardToRow(Board.boardArray, currentBoard)) != 0) {
                HashMap<String, Double> savedMap = new HashMap<>();
                Double savedNumber;

                if(MOVES < 10) {
                    savedMap = minimax(Board.boardArray, -1, Math.min(4, count), Double.MIN_VALUE, Double.MAX_VALUE, true);
                } else if(MOVES < 18) {
                    savedMap = minimax(Board.boardArray, -1, Math.min(5, count), Double.MIN_VALUE, Double.MAX_VALUE, true);
                } else {
                    savedMap = minimax(Board.boardArray, -1, Math.min(6, count), Double.MIN_VALUE, Double.MAX_VALUE, true);
                }
                savedNumber = savedMap.get("Y");
            }

            int[] boardRow = boardToRow(Board.boardArray, currentBoard);
            for(int p = 0; p < boardRow.length; p++) {
                if(boardRow[p] == 0) {
                    bestMove = p;
                    break;
                }
            }

            System.out.println("bestMove: " + bestMove);
            if(bestMove != -1) {

                for(int i = 0; i < 9; i++) {
                    if(boardRow[i] == 0) {
                        //TODO Update evaluatePos
                        double score = evaluatePos(boardRow, i)*45;
                        //int score = evaluatePos(boardRow, i,  currentTurn)*45;
                    }
                }

                for(int b = 0; b < 9; b++) {
                    if(checkWinCondition(boardRow) == 0) {
                        if(Board.boardArray[currentBoard][b] == 0) {
                            //TODO
                            boardArrayCopy[currentBoard][b] = Main.game.player1.playerNumber;
                            //Board.boardArray[currentBoard][b] == ai;
                            int savedNumber = 0;
                            HashMap<String, Double> savedMap = new HashMap<>();
                            if(MOVES < 20) {
                                savedMap = minimax(Board.boardArray, -1, Math.min(4, count), Double.MIN_VALUE, Double.MAX_VALUE, true);
                            } else if(MOVES < 18) {
                                savedMap = minimax(Board.boardArray, -1, Math.min(5, count), Double.MIN_VALUE, Double.MAX_VALUE, true);
                            } else {
                                savedMap = minimax(Board.boardArray, -1, Math.min(6, count), Double.MIN_VALUE, Double.MAX_VALUE, true);
                            }

                            Double score2 = savedMap.get("X");
                            Double score4 = savedMap.get("Y");
                            //Board.boardArray[currentBoard][b] = 0;
                            boardArrayCopy[currentBoard][b] = Main.game.player1.playerNumber;
                            bestScore[b] += score2;

                        }
                    }
                }

                for(int i = 0; i < bestScore.length; i++) {
                    if(bestScore[i] > bestScore[bestMove]) {
                        bestMove = i;
                    }
                }

                //System.out.println("boardArrayCopy[currentBoard][bestMove]: " + boardArrayCopy[currentBoard][bestMove]);
                System.out.println("Best move is: " + "CB: " + currentBoard + " BM: " + bestMove);
                if(boardArrayCopy[currentBoard][bestMove] == 0) {
                    System.out.println("Best move is: " + "CB: " + currentBoard + " BM: " + bestMove);
                    //Board.boardArray = boardArrayCopy;
                }


            }


        }
        currentTurn = -currentTurn;
    }
}