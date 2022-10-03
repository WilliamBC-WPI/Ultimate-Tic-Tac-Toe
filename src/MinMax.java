public class MinMax {

    //You can play on any board if, the board that is supposed to be played on is full

    public int miniMax(Board board, int depth, Boolean maximizing) {

        //Value for a move comes from

        Integer bestVal;


        if(maximizing) {
            bestVal = Integer.MIN_VALUE;

            for(int i = 0; i < board.boardArray.length; i++) {
                for(int j = 0; j < board.boardArray[i].length; j++) {

                }

            }
        }
        else {
            bestVal = Integer.MAX_VALUE;

        }

        return bestVal;

    }

    public int evaluate(Board board) {
        return 0;
    }


}
