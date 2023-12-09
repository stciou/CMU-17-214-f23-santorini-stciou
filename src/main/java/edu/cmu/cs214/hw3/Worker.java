package edu.cmu.cs214.hw3;

/**
 * The {@code Worker} class represents a worker in the game, managing its actions and checking its winning status.
 */
public class Worker {
    private Player owner;
    private Cell currentCell;
    private Cell previousCell;
    private int lastLevel;

    /**
     * Constructs a new {@code Worker} instance associated with a specific player.
     *
     * @param owner the {@code Player} who owns this worker.
     */
    public Worker(Player owner) {
        this.owner = owner;
    }

    /**
     * Retrieves the {@code Player} who owns this worker.
     * @return the owner of this worker.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Retrieves the {@code Player} who owns this worker.
     * @return the owner of this worker.
     */
    public Cell getCurrentCell() {
        return currentCell;
    }

    /**
     * Retrieves the {@code Player} who owns this worker.
     * @return the owner of this worker.
     */
    public Cell getPreviousCell() {
        return previousCell;
    }

    public int getLastLevel() {
        return lastLevel;
    }

    /**
     * Moves the worker to a target cell if the move is valid.
     * This method should be modified to incorporate god card logic.
     *
     * @param targetCell the target {@code Cell} to move the worker to.
     * @return {@code true} if the move is successful; {@code false} otherwise.
     */
    public boolean move(Cell targetCell) {
        if (isValidMove(targetCell)) {
            currentCell.setWorker(null);
            targetCell.setWorker(this);
            currentCell = targetCell;
            return true;
        }
        return false;
    }

    /**
     * Builds a block or dome on a target cell if the build action is valid.
     * This method should be modified to incorporate god card logic.
     *
     * @param targetCell the target {@code Cell} to build on.
     * @return {@code true} if the build is successful; {@code false} otherwise.
     */
    public boolean build(Cell targetCell) {
        if (owner.getGodCard() != null && !owner.getGodCard().modifyBuild(this, targetCell)) {
            return false;
        }
            if (isValidBuild(targetCell)) {
            if (targetCell.getLevel() == 3) {
                targetCell.placeDome();
            } else {
                targetCell.increaseLevel();
            }
            return true;
        }
        return false;
    }

    /**
     * Sets the current cell of the worker.
     * @param cell the cell to set.
     */
    public void setCurrentCell(Cell cell) {
        this.lastLevel = (this.currentCell != null) ? this.currentCell.getLevel() : 0;
        this.currentCell = cell;
    }

    /**
     * Checks whether a move to a target cell is valid.
     *
     * @param targetCell the target {@code Cell} to check.
     * @return {@code true} if the move is valid; {@code false} otherwise.
     */
    private boolean isValidMove(Cell targetCell) {
        if (targetCell == null) {
            return false;
        }
        return currentCell.isAdjacentTo(targetCell) && !targetCell.isOccupied() && Math.abs(currentCell.getLevel() - targetCell.getLevel()) <= 1;
    }

    /**
     * Checks whether a build action on a target cell is valid.
     *
     * @param targetCell the target {@code Cell} to check.
     * @return {@code true} if the build action is valid; {@code false} otherwise.
     */
    private boolean isValidBuild(Cell targetCell) {
        return currentCell.isAdjacentTo(targetCell) && !targetCell.isOccupied();
    }

    /**
     * Checks whether the worker has reached the third level of a tower and won the game.
     *
     * @return {@code true} if the worker has won; {@code false} otherwise.
     */
    public boolean hasWon() {
        return currentCell.getLevel() == 3;
    }

    /**
     * Sets the last level of the worker.
     * @param level the level to set.
     */
    public void setLastLevel(int level) {
        this.lastLevel = level;
    }
}
