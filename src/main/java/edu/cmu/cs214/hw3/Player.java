package edu.cmu.cs214.hw3;

/**
 * The {@code Player} class represents a player in the game, managing the player's workers and actions.
 */
public class Player {
    private Worker worker1;
    private Worker worker2;
    private Board board;

    /**
     * Constructs a new {@code Player} instance, initializing the workers and associating the player with a game board.
     *
     * @param board the game board on which the player will play.
     */
    public Player(Board board) {
        this.board = board;
        this.worker1 = new Worker(this);
        this.worker2 = new Worker(this);
    }

    /**
     * Retrieves the first worker of the player.
     *
     * @return the first worker.
     */
    public Worker getWorker1() {
        return worker1;
    }

    /**
     * Retrieves the second worker of the player.
     *
     * @return the second worker.
     */
    public Worker getWorker2() {
        return worker2;
    }

    /**
     * Chooses a worker based on the specified worker number.
     *
     * @param workerNum the number of the worker to choose (1 or 2).
     * @return the chosen worker.
     */
    public Worker chooseWorker(int workerNum) {
        if (workerNum == 1) {
            return worker1;
        } else {
            return worker2;
        }
    }

    /**
     * Places the player's workers on the specified cells of the game board.
     *
     * @param x1 the x-coordinate of the first worker's position.
     * @param y1 the y-coordinate of the first worker's position.
     * @param x2 the x-coordinate of the second worker's position.
     * @param y2 the y-coordinate of the second worker's position.
     */
    public void placeWorkers(int x1, int y1, int x2, int y2) {
        board.placeWorker(x1, y1, worker1);
        board.placeWorker(x2, y2, worker2);
    }

    /**
     * Takes a turn for the player, moving and building with a chosen worker.
     *
     * @param workerNum the number of the worker to use (1 or 2).
     * @param moveToX   the x-coordinate to move the worker to.
     * @param moveToY   the y-coordinate to move the worker to.
     * @param buildAtX  the x-coordinate to build at.
     * @param buildAtY  the y-coordinate to build at.
     * @return {@code true} if the turn was successful; {@code false} otherwise.
     */
    public boolean takeTurn(int workerNum, int moveToX, int moveToY, int buildAtX, int buildAtY) {
        Worker chosenWorker = chooseWorker(workerNum);

        if (!chosenWorker.move(board.getCell(moveToX, moveToY))) {
            return false;
        }

        if (chosenWorker.hasWon()) {
            System.out.println("Player has won!");
            return true;
        }

        if (!chosenWorker.build(board.getCell(buildAtX, buildAtY))) {
            return false;
        }

        return true;
    }
}
