import java.util.Scanner;

public class Game {

    public Game() {

    }

    public int turn() {
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextInt()) {
            System.out.println("Error Enter amount as an int.");
            scan.nextLine();
        }

    }
}
