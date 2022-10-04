import java.util.HashMap;
import java.util.stream.IntStream;

public class Minimax {

    static HashMap<String, Integer> evals = new HashMap<String, Integer>();

    static int RUNS = 0;
    static int MOVES = 0;

    static int bot = -1;
    static int player = 1;

    static int currentTurn = 1;
    //static int bestMove = -1;
    public static int currentBoard = 4;
    Integer[] bestScore = new Integer[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

    /**
     * helper that checks if a normal tic tac toe game has been won
     * checks columns, rows & diagonals for victory
     * 
     * @param position
     * @return 1 or -1 if a specific player has won, returns 0 if no one has won
     */
    public static int checkWinCondition(int[] position) {
        int a = 1;
        // SINGLE ARRAY
        if (position[0] + position[1] + position[2] == a * 3 || position[3] + position[4] + position[5] == a * 3
                || position[6] + position[7] + position[8] == a * 3 || position[0] + position[3] + position[6] == a * 3
                || position[1] + position[4] + position[7] == a * 3 ||
                position[2] + position[5] + position[8] == a * 3 || position[0] + position[4] + position[8] == a * 3
                || position[2] + position[4] + position[6] == a * 3) {
            return a;
        }
        // DOUBLE ARRAY
        // if (boardArray[0][0] + boardArray[0][1] + boardArray[0][2] == a * 3
        // || boardArray[1][0] + boardArray[1][1] + boardArray[1][2] == a * 3
        // || boardArray[2][0] + boardArray[2][1] + boardArray[2][2] == a * 3
        // || boardArray[0][0] + boardArray[1][0] + boardArray[2][0] == a * 3
        // || boardArray[0][1] + boardArray[1][1] + boardArray[2][1] == a * 3
        // || boardArray[0][2] + boardArray[1][2] + boardArray[2][2] == a * 3
        // || boardArray[0][0] + boardArray[1][1] + boardArray[2][2] == a * 3
        // || boardArray[0][2] + boardArray[1][1] + boardArray[2][0] == a * 3) {
        // return a;
        // }
        a = -1;
        // SINGLE ARRAY
        if (position[0] + position[1] + position[2] == a * 3 || position[3] + position[4] + position[5] == a * 3
                || position[6] + position[7] + position[8] == a * 3 || position[0] + position[3] + position[6] == a * 3
                || position[1] + position[4] + position[7] == a * 3 ||
                position[2] + position[5] + position[8] == a * 3 || position[0] + position[4] + position[8] == a * 3
                || position[2] + position[4] + position[6] == a * 3) {
            return a;
        }
        // DOUBLE ARRAY
        // if (boardArray[0][0] + boardArray[0][1] + boardArray[0][2] == a * 3
        // || boardArray[1][0] + boardArray[1][1] + boardArray[1][2] == a * 3
        // || boardArray[2][0] + boardArray[2][1] + boardArray[2][2] == a * 3
        // || boardArray[0][0] + boardArray[1][0] + boardArray[2][0] == a * 3
        // || boardArray[0][1] + boardArray[1][1] + boardArray[2][1] == a * 3
        // || boardArray[0][2] + boardArray[1][2] + boardArray[2][2] == a * 3
        // || boardArray[0][0] + boardArray[1][1] + boardArray[2][2] == a * 3
        // || boardArray[0][2] + boardArray[1][1] + boardArray[2][0] == a * 3) {
        // return a;
        // }
        return 0;
    }

    public static int evaluatePos(int[] position, int sq) {
        position[sq] = bot;
        int eval = 0;
        // heuristic values
        double[] points = new double[] { 0.2, 0.17, 0.2, 0.17, 0.22, 0.17, 0.2, 0.17, 0.2 };

        int a = 2;
        if (position[0] + position[1] + position[2] == a || position[3] + position[4] + position[5] == a
                || position[6] + position[7] + position[8] == a || position[0] + position[3] + position[6] == a
                || position[1] + position[4] + position[7] == a ||
                position[2] + position[5] + position[8] == a || position[0] + position[4] + position[8] == a
                || position[2] + position[4] + position[6] == a) {
            eval += 1; // 😭
        }

        // take victories
        a = -3;
        if (position[0] + position[1] + position[2] == a || position[3] + position[4] + position[5] == a
                || position[6] + position[7] + position[8] == a || position[0] + position[3] + position[6] == a
                || position[1] + position[4] + position[7] == a ||
                position[2] + position[5] + position[8] == a || position[0] + position[4] + position[8] == a
                || position[2] + position[4] + position[6] == a) {
            eval += 5;
        }

        // block a player's turn
        position[sq] = player;

        a = 3;
        if (position[0] + position[1] + position[2] == a || position[3] + position[4] + position[5] == a
                || position[6] + position[7] + position[8] == a || position[0] + position[3] + position[6] == a
                || position[1] + position[4] + position[7] == a ||
                position[2] + position[5] + position[8] == a || position[0] + position[4] + position[8] == a
                || position[2] + position[4] + position[6] == a) {
            eval += 2;
        }

        position[sq] = bot;

        eval -= checkWinCondition(position) * 15;

        position[sq] = 0;

        return eval;
    }

    /**
     * evaluate a board fairly - realEvaluateSquare HEURISTIC
     * 
     * @param position on basic tictactoe board
     */
    public static int evaluateSquare(int[] position) {
        int eval = 0;
        double[] points = { 0.2, 0.17, 0.2, 0.17, 0.22, 0.17, 0.2, 0.17, 0.2 }; // heuristic

        // multiplies heuristic pts by position on board ?
        for (int i = 0; i < 9; i++) {
            eval -= position[i] * points[i];
        }
        // assigns 6 to horizontal victory, 6 to vertical victory, 7 to diagonal victory
        int a = 2;
        // horizontal
        if (position[0] + position[1] + position[2] == a || position[3] + position[4] + position[5] == a
                || position[6] + position[7] + position[8] == a) {
            eval -= 6;
        }
        // vertical
        if (position[0] + position[3] + position[6] == a || position[1] + position[4] + position[7] == a
                || position[6] + position[7] + position[8] == a) {
            eval -= 6;
        }
        // diagonal
        if (position[0] + position[4] + position[8] == a || position[2] + position[4] + position[6] == a) {
            eval -= 7;
        }

        a = -1;
        // evaluation heuristic - did we win the entire row/column/diagonal
        if ((position[0] + position[1] == 2 * a && position[2] == -a)
                || (position[1] + position[2] == 2 * a && position[0] == -a)
                || (position[0] + position[2] == 2 * a && position[1] == -a)
                || (position[3] + position[4] == 2 * a && position[5] == -a)
                || (position[3] + position[5] == 2 * a && position[4] == -a)
                || (position[5] + position[4] == 2 * a && position[3] == -a)
                || (position[6] + position[7] == 2 * a && position[8] == -a)
                || (position[6] + position[8] == 2 * a && position[7] == -a)
                || (position[7] + position[8] == 2 * a && position[6] == -a)
                || (position[0] + position[3] == 2 * a && position[6] == -a)
                || (position[0] + position[6] == 2 * a && position[3] == -a)
                || (position[3] + position[6] == 2 * a && position[0] == -a)
                || (position[1] + position[4] == 2 * a && position[7] == -a)
                || (position[1] + position[7] == 2 * a && position[4] == -a)
                || (position[4] + position[7] == 2 * a && position[1] == -a)
                || (position[2] + position[5] == 2 * a && position[8] == -a)
                || (position[2] + position[8] == 2 * a && position[5] == -a)
                || (position[5] + position[8] == 2 * a && position[2] == -a)
                || (position[0] + position[4] == 2 * a && position[8] == -a)
                || (position[0] + position[8] == 2 * a && position[4] == -a)
                || (position[4] + position[8] == 2 * a && position[0] == -a)
                || (position[2] + position[4] == 2 * a && position[6] == -a)
                || (position[2] + position[6] == 2 * a && position[4] == -a)
                || (position[4] + position[6] == 2 * a && position[2] == -a)) {
            eval -= 9;
        }

        a = -2;
        if (position[0] + position[1] + position[2] == a || position[3] + position[4] + position[5] == a
                || position[6] + position[7] + position[8] == a) {
            eval += 6;
        }
        if (position[0] + position[3] + position[6] == a || position[1] + position[4] + position[7] == a
                || position[2] + position[5] + position[8] == a) {
            eval += 6;
        }
        if (position[0] + position[4] + position[8] == a || position[2] + position[4] + position[6] == a) {
            eval += 7;
        }

        a = 1;
        // same as above but for opposite player
        if ((position[0] + position[1] == 2 * a && position[2] == -a)
                || (position[1] + position[2] == 2 * a && position[0] == -a)
                || (position[0] + position[2] == 2 * a && position[1] == -a)
                || (position[3] + position[4] == 2 * a && position[5] == -a)
                || (position[3] + position[5] == 2 * a && position[4] == -a)
                || (position[5] + position[4] == 2 * a && position[3] == -a)
                || (position[6] + position[7] == 2 * a && position[8] == -a)
                || (position[6] + position[8] == 2 * a && position[7] == -a)
                || (position[7] + position[8] == 2 * a && position[6] == -a)
                || (position[0] + position[3] == 2 * a && position[6] == -a)
                || (position[0] + position[6] == 2 * a && position[3] == -a)
                || (position[3] + position[6] == 2 * a && position[0] == -a)
                || (position[1] + position[4] == 2 * a && position[7] == -a)
                || (position[1] + position[7] == 2 * a && position[4] == -a)
                || (position[4] + position[7] == 2 * a && position[1] == -a)
                || (position[2] + position[5] == 2 * a && position[8] == -a)
                || (position[2] + position[8] == 2 * a && position[5] == -a)
                || (position[5] + position[8] == 2 * a && position[2] == -a)
                || (position[0] + position[4] == 2 * a && position[8] == -a)
                || (position[0] + position[8] == 2 * a && position[4] == -a)
                || (position[4] + position[8] == 2 * a && position[0] == -a)
                || (position[2] + position[4] == 2 * a && position[6] == -a)
                || (position[2] + position[6] == 2 * a && position[4] == -a)
                || (position[4] + position[6] == 2 * a && position[2] == -a)) {
            eval += 9;
        }

        eval -= checkWinCondition(position) * 12; // why tf is it 12

        return eval;
    }

    /**
     * MOST IMPORTANT HELPER
     * 
     * @param position array
     * @param board    int
     * @return numerical evaluation of the entire game board in it's current state
     */
    public static int evalGame(int[][] position, int board) {
        int val = 0;
        int[] mainboard = new int[9];
        double[] evaluator = { 1.4, 1, 1.4, 1, 1.75, 1, 1.4, 1, 1.4 }; // heuristics we can change later?

        for (int i = 0; i < 9; i++) {
            val += evaluateSquare(position[i]) * (1.5 * evaluator[i]); // why 1.5 lol
            if (i == board) {
                val += evaluateSquare(position[i]) * evaluator[i];
            }
            int tempEval = checkWinCondition(position[i]);
            val -= tempEval * evaluator[i];
            mainboard[i] = tempEval; // same as mainboard.push(tempEval) ?
        }

        val -= checkWinCondition(mainboard) * 5000;
        val += evaluateSquare(mainboard) * 150;
        return val;
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
    public static HashMap<String, Integer> minimax(int[][] position, int boardTurn, int depth, int alpha, int beta,
                                                   boolean maxPlayer) {
        RUNS++;
        //HashMap evals = new HashMap<String, Integer>();

        int templay = -1;
        Integer infinity = Integer.MAX_VALUE;
        Integer negInf = Integer.MIN_VALUE;

        int calc = evalGame(position, boardTurn);

        String xC = "X";
        String yT = "Y";

        if (depth <= 0 || Math.abs(calc) > 5000) {
            evals.put(xC, calc);
            evals.put(yT, templay);

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
            int maxEval = negInf;
            for (int x = 0; x < 9; x++) {
                Integer evalu = negInf;
                // if you can play on any board, check every position
                if (boardTurn == -1) {
                    for (int y = 0; y < 9; y++) {
                        // except the boards that have victories
                        if (checkWinCondition(position[x]) == 0) {
                            if (position[x][y] == 0) {
                                position[x][y] = bot;
                                evals = minimax(position, y, depth - 1, alpha, beta, false);
                                try {
                                    evalu = (int) evals.get(xC);
                                    System.out.println("Evals: " + evals.get(evalu));
                                } catch (NullPointerException e) {
                                    evalu = Integer.MIN_VALUE;
                                    evals.put("X", evalu);

                                    System.out.println("Getting minval???");
                                }


                                position[x][y] = 0;
                            }
                            if (evalu > maxEval) {
                                maxEval = evalu;
                                evals.put("X", maxEval);
                                templay = x;
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
                        position[boardTurn][x] = bot;
                        evals = minimax(position, x, depth - 1, alpha, beta, false);
                        position[boardTurn][x] = 0;
                    }

                    int play = (int) evals.get(xC); // var blop = evalut.mE;
                    if (play > maxEval) {
                        maxEval = play;
                        evals.put(yT, templay);
                        //outputMap.put("Y", maxEval);
                        // saves the board you should play on, so that it can be passed on when AI can
                        // play on any board
                        try {
                            templay = (int) evals.get(yT); // tmpPlay = evalut.tP;
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
                evals.put("Y", templay);
//                System.out.println("Test Here:");
                System.out.println("MaxEval: " + maxEval);
                System.out.println("Templay: " + templay);
                return (evals);
            }
        } else { // same but for minimizing player
            for (int x = 0; x < 9; x++) {
                int evaluo = infinity;
                if (boardTurn == -1) {
                    for (int y = 0; y < 9; y++) {
                        // except the boards that have victories
                        if (checkWinCondition(position[x]) == 0) {
                            if (position[x][y] == 0) {
                                position[x][y] = player;
                                evals = minimax(position, y, depth - 1, alpha, beta, false);
                                try {
                                    evaluo = (int) evals.get(xC);
                                    evals.put("X", evaluo);
                                } catch (NullPointerException e) {
                                    evaluo = Integer.MIN_VALUE;
                                    evals.put("X", evaluo);
                                }

                                position[x][y] = 0;
                            }
                            if (evaluo > infinity) {
                                negInf = evaluo;
                                templay = x;
                                evals.put("Y", templay);

                            }
                            beta = Math.min(beta, evaluo);
                        }
                    }
                    // alphabeta pruning
                    if (beta <= alpha) {
                        break;
                    } else {
                        if (position[boardTurn][x] == 0) {
                            position[boardTurn][x] = player;
                            evals = minimax(position, x, depth - 1, alpha, beta, true);
                            evaluo = (int) evals.get(xC);
                            position[boardTurn][x] = 0;
                        }
                        int blay = evaluo;
                        if (blay < negInf) {
                            negInf = blay;
                            templay = (int) evals.get(yT);
                            evals.put("Y", templay);
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
        Integer[] bestScore = new Integer[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        if(Reader.ourTurn()) {
            int bestMove = -1;
            bestScore = new Integer[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

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
                HashMap<String, Integer> savedMap = new HashMap<>();
                int savedNumber;

                if(MOVES < 10) {
                    savedMap = minimax(Board.boardArray, -1, Math.min(4, count), Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                } else if(MOVES < 18) {
                    savedMap = minimax(Board.boardArray, -1, Math.min(5, count), Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                } else {
                    savedMap = minimax(Board.boardArray, -1, Math.min(6, count), Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                }
                savedNumber = (int) savedMap.get("Y");
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
                        int score = evaluatePos(boardRow, i)*45;
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
                            HashMap<String, Integer> savedMap = new HashMap<>();
                            if(MOVES < 20) {
                                savedMap = minimax(Board.boardArray, -1, Math.min(4, count), Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                            } else if(MOVES < 18) {
                                savedMap = minimax(Board.boardArray, -1, Math.min(5, count), Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                            } else {
                                savedMap = minimax(Board.boardArray, -1, Math.min(6, count), Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                            }

                            int score2 = savedMap.get("X");
                            int score4 = savedMap.get("Y");
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

    public static void game2() {

        int bestMove = -1;
        Integer[] bestScore = new Integer[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

        int count = 0;

        for(int i = 0; i < 9; i++) {
            int[] boardRow = boardToRow(Board.boardArray, i);
            for(int j = 0; j < boardRow.length; j++) {
                if(boardRow[j] == 0) {
                    count++;
                }
            }
        }

        HashMap<String, Integer> savedMap = new HashMap<>();

        savedMap = minimax(Board.boardArray, -1, 4, Integer.MIN_VALUE, Integer.MIN_VALUE, true);

        currentBoard = savedMap.get("Y");
        System.out.println("CurrentBoard: " + currentBoard);

        int[] boardRow = boardToRow(Board.boardArray, currentBoard);
        for(int p = 0; p < boardRow.length; p++) {
            if(boardRow[p] == 0) {
                bestMove = p;
                break;
            }
        }

        if(bestMove != -1) {
            for(int a = 0; a < 9; a++) {
                if(Board.boardArray[currentBoard][a] == 0) {
                    int score = evaluatePos(Board.boardArray[currentBoard], a)*45;
                    bestScore[a] = score;
                }
            }
        }

        for(int b = 0; b < 9; b++) {
            if(checkWinCondition(Board.boardArray[currentBoard]) == 0) {
                if(Board.boardArray[currentBoard][b] == 0) {
                    //Board.boardArray
                }
            }
        }



    }
}