package edu.cmu.cs214.hw3.godcards;

import edu.cmu.cs214.hw3.*;

/**
 * The {@code Minotaur} class represents the Minotaur god card in the Santorini game.
 * Minotaur allows a worker to move into an opponent Worker’s space,
 * if their Worker can be forced one space straight backwards to an unoccupied space at any level.
 */
public class Minotaur implements GodCard {
    private Board board;

    /**
     * Constructs an instance of {@code Minotaur} with a reference to the game board.
     * @param board The game board.
     */
    public Minotaur(Board board) {
        this.board = board;
    }

    /**
     * Does not modify the move action.
     * @param worker The worker attempting to move.
     * @param targetCell The target cell where the worker wants to move.
     * @return {@code true} if the move is allowed and successful; {@code false} otherwise.
     */
    @Override
    public boolean modifyMove(Worker worker, Cell targetCell) {
        if (targetCell.isOccupied() && targetCell.getWorker() != worker) {
            Cell behindCell = calculateBehindCell(targetCell, worker.getCurrentCell());
            if (behindCell != null && !behindCell.isOccupied() && behindCell.getLevel() <= 3) {
                Worker opponentWorker = targetCell.getWorker();
    
                opponentWorker.setCurrentCell(behindCell);
                behindCell.setWorker(opponentWorker);
    
                worker.setCurrentCell(targetCell);
                targetCell.setWorker(worker);
    
                return true;
            }
        }
        return false;
    }
    

    /**
     * Does not modify the build action.
     * @param worker The worker attempting to build.
     * @param targetCell The target cell where the worker wants to build.
     * @return {@code true} if the build is allowed and successful; {@code false} otherwise.
     */
    @Override
    public boolean modifyBuild(Worker worker, Cell targetCell) {
        return true;
    }

    /**
     * Checks if the player has won by moving up two levels.
     * @param currentCell The current cell of the worker.
     * @return {@code true} if the player has won; {@code false} otherwise.
     */
    @Override
    public boolean checkWinCondition(Cell currentCell) {
        return false;
    }

    /**
     * Calculates the cell behind the opponent's worker relative to the current worker's position.
     * @param targetCell The cell of the opponent's worker.
     * @param currentCell The current cell of the worker using Minotaur.
     * @return The cell behind the opponent's worker, or null if not valid.
     */
    private Cell calculateBehindCell(Cell targetCell, Cell currentCell) {
        int dx = targetCell.getX() - currentCell.getX();
        int dy = targetCell.getY() - currentCell.getY();
        int behindX = targetCell.getX() + dx;
        int behindY = targetCell.getY() + dy;
    
        if (board.isValidCoordinate(behindX) && board.isValidCoordinate(behindY)) {
            return board.getCell(behindX, behindY);
        }
        return null;
    }    

    @Override
    public void resetAdditionalBuild() {
    }
}
