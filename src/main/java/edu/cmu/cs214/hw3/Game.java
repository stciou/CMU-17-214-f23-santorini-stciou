package edu.cmu.cs214.hw3;

import java.util.Scanner;

/**
 * The {@code Game} class manages the flow and state of the game.
 * It initializes the game board, players, and controls the game loop,
 * ensuring that players take turns and the game state is updated accordingly.
 */
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    /**
     * Returns the game board.
     * @return the game board.
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Constructs a new {@code Game} instance, initializing the board and players,
     * and setting the starting player.
     */
    public Game() {
        board = new Board();
        player1 = new Player(board);
        player2 = new Player(board);
        currentPlayer = player1; 

        player1.placeWorkers(0, 0, 0, 1);
        player2.placeWorkers(4, 4, 4, 3);
    }

    /**
     * Starts the game loop, allowing players to take turns until the game ends.
     * Announces the winner when the game is over.
     */
    public void startGame() {
        while (true) {
            boolean turnSuccessful = takeTurn();

            if (!turnSuccessful) {
                System.out.println("Game over! Player " + (currentPlayer == player1 ? "2" : "1") + " wins!");
                break;
            }

            if (currentPlayer == player1) {
                currentPlayer = player2;
            } else {
                currentPlayer = player1;
            }
        }
    }

    /**
     * Manages a single turn for the current player, prompting them to make moves and build.
     * Validates the player's input and updates the game state accordingly.
     *
     * @return {@code true} if the turn was successful; {@code false} otherwise.
     */
    public boolean takeTurn() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Player " + (currentPlayer == player1 ? "1" : "2") + "'s turn.");
    
        int workerNum;
        do {
            System.out.print("Choose a worker (1 or 2): ");
            workerNum = scanner.nextInt();
        } while (workerNum != 1 && workerNum != 2);
    
        int moveToX, moveToY, buildAtX, buildAtY;
        do {
            System.out.print("Enter x-coordinate to move to: ");
            moveToX = scanner.nextInt();
    
            System.out.print("Enter y-coordinate to move to: ");
            moveToY = scanner.nextInt();
        } while (!board.isValidPosition(moveToX, moveToY) || board.getCell(moveToX, moveToY).isOccupied());
    
        do {
            System.out.print("Enter x-coordinate to build at: ");
            buildAtX = scanner.nextInt();
    
            System.out.print("Enter y-coordinate to build at: ");
            buildAtY = scanner.nextInt();
        } while (!board.isValidPosition(buildAtX, buildAtY) || board.getCell(buildAtX, buildAtY).isOccupied());
    
        return currentPlayer.takeTurn(workerNum, moveToX, moveToY, buildAtX, buildAtY);
    }

    /**
     * The entry point of the application. Creates a new game instance and starts it.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
