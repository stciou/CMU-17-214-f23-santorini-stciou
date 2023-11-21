package edu.cmu.cs214.hw3;

/**
 * The {@code Game} class manages the flow and state of the game.
 * It initializes the game board, players, and controls the game loop.
 */
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    /**
     * Constructs a new {@code Game} instance, initializing the board and players.
     */
    public Game() {
        board = new Board();
        player1 = new Player(board);
        player2 = new Player(board);
        currentPlayer = player1; 
    }

    /**
     * Manages a single turn for the current player.
     *
     * @param workerNum The worker number (1 or 2).
     * @param moveToX   X-coordinate to move to.
     * @param moveToY   Y-coordinate to move to.
     * @param buildAtX  X-coordinate to build at.
     * @param buildAtY  Y-coordinate to build at.
     * @return {@code true} if the turn was successful; {@code false} otherwise.
     */
    public boolean takeTurn(int workerNum, int moveToX, int moveToY, int buildAtX, int buildAtY) {
        boolean turnSuccessful = currentPlayer.takeTurn(workerNum, moveToX, moveToY, buildAtX, buildAtY);
        if (turnSuccessful) {
            switchCurrentPlayer();
        }
        return turnSuccessful;
    }

    /**
     * Switches the current player.
     */
    private void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }
}
