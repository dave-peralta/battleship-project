import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Scanner scanner;

    public Game() {
        player1 = new Player();
        player2 = new Player();
        scanner = new Scanner(System.in);
        setupShips(player1);
        setupShips(player2);
    }

    private void setupShips(Player player) {
        System.out.println("Player setup:");
//        placeShip(player, "Carrier", 5);
//        placeShip(player, "Battleship", 4);
//        placeShip(player, "Cruiser", 3);
//        placeShip(player, "Cruiser", 3);
        placeShip(player, "Submarine", 2);
        placeShip(player, "Submarine", 2);
    }

    private void placeShip(Player player, String type, int size) {
        boolean placed = false;
        while (!placed) {
            System.out.println("Place a " + type + " of size " + size);
            player.getBoard().display();
            System.out.print("Enter row and column (e.g., 5 7): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            System.out.print("Horizontal (h) or vertical (v)? ");
            char orientation = scanner.next().charAt(0);
            placed = player.getBoard().placeShip(new Position(row, col), size, orientation == 'h');
            if (!placed) {
                System.out.println("Invalid placement, try again.");
            }
        }
    }

    public void play() {
        boolean player1Turn = true;
        while (player1.getBoard().hasRemainingShips() && player2.getBoard().hasRemainingShips()) {
            Player active = player1Turn ? player1 : player2;
            Player opponent = player1Turn ? player2 : player1;
            takeTurn(active, opponent);
            player1Turn = !player1Turn;
        }
        if (!player1.getBoard().hasRemainingShips()) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("Player 1 wins!");
        }
    }

    private void takeTurn(Player active, Player opponent) {
        System.out.println(active == player1 ? "Player 1's turn" : "Player 2's turn");
        opponent.getBoard().display();
        System.out.print("Enter row and column to hit (e.g., 4 8): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        boolean hit = opponent.getBoard().fireAt(new Position(row, col));
        if (hit) {
            System.out.println("Hit!");
        } else {
            System.out.println("Miss!");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
