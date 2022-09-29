public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Game newGame = new Game("Bob", "Zane");
        newGame.makeMove(newGame.player1, new int[]{5, 2});
        newGame.makeMove(newGame.player2, new int[]{6, 3});
        Board.printBoardArray();
    }
}