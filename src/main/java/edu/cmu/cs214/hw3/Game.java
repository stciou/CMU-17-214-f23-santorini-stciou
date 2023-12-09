package edu.cmu.cs214.hw3;

import edu.cmu.cs214.hw3.godcards.GodCard;

/**
 * The {@code Game} class manages the flow and state of the game.
 * It initializes the game board, players, and controls the game loop.
 */
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Cell[][] grid = new Cell[5][5];

    /**
     * Constructs a new {@code Game} instance, initializing the board and players.
     */
    public Game() {
        board = new Board();
        player1 = new Player("Player1", board);
        player2 = new Player("Player2", board);
        currentPlayer = player1;  
        grid = new Cell[5][5];
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

    /**
     * Performs a move action in the game.
     *
     * @param x        X-coordinate of the move.
     * @param y        Y-coordinate of the move.
     * @param player   The player who is making the move.
     * @return true if the move was successful, false otherwise.
     */
    public boolean performMove(int x, int y, String player) {
        if (board.isValidPosition(x, y) && !grid[x][y].isOccupied()) {
            switchCurrentPlayer();
            return true;
        }
        return false;
    }

    /**
     * Checks if there is a winner in the game.
     * @return The name of the winner, or null if there is no winner.
     */
    public String checkWinner() {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                Cell cell = grid[x][y];
                if (cell.isOccupied() && cell.getLevel() == 3) {
                    return cell.getWorker().getOwner().getName();
                }
            }
        }
        return null;
    }

    /**
     * Selects the god card for the given player.
     * @param playerName The name of the player.
     * @param godCardName The name of the god card.
     * @return true if the god card was successfully selected, false otherwise.
     */
    public boolean selectGodCard(String playerName, String godCardName) {
        Player player = getPlayerByName(playerName);
        if (player != null) {
            GodCard godCard = GodCardFactory.createGodCard(godCardName, board);
            player.setGodCard(godCard);
            return true;
        }
        return false;
    }

    /**
     * Gets the player with the given name.
     * @param playerName The name of the player.
     * @return The player with the given name, or null if no such player exists.
     */
    public Player getPlayerByName(String playerName) {
        if (player1.getName().equals(playerName)) {
            return player1;
        } else if (player2.getName().equals(playerName)) {
            return player2;
        }
        return null;
    }

    /**
     * Performs a build action in the game.
     * @param x        X-coordinate of the build.
     * @param y        Y-coordinate of the build.
     * @param player   The player who is making the build.
     * @return true if the build was successful, false otherwise.
     */
    public boolean performBuild(int x, int y, String player) {
        if (board.isValidBuildPosition(x, y)) {
            grid[x][y].increaseLevel();
            return true;
        }
        return false;
    }

}
