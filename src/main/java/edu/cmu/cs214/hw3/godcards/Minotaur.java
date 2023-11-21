package edu.cmu.cs214.hw3.godcards;

import edu.cmu.cs214.hw3.*;

/**
 * The {@code Minotaur} class represents the Minotaur god card in the Santorini game.
 * Minotaur allows a worker to move into an opponent Worker’s space,
 * if their Worker can be forced one space straight backwards to an unoccupied space at any level.
 */
public class Minotaur implements GodCard {
    private Board board;

    public Minotaur(Board board) {
        this.board = board;
    }

    @Override
    public boolean modifyMove(Worker worker, Cell targetCell) {
        if (targetCell.isOccupied() && targetCell.getWorker() != worker) {
            Cell behindCell = calculateBehindCell(targetCell, worker.getCurrentCell());
            if (behindCell != null && !behindCell.isOccupied() && behindCell.getLevel() <= 3) {
                targetCell.getWorker().setCurrentCell(behindCell);
                worker.setCurrentCell(targetCell);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean modifyBuild(Worker worker, Cell targetCell) {
        return true;
    }

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
        if (isValidCoordinate(behindX) && isValidCoordinate(behindY)) {
            return board.getCell(behindX, behindY);
        }
        return null;
    }

    /**
     * Checks if the given coordinate is within the board boundaries.
     * @param coordinate The coordinate to check.
     * @return True if the coordinate is valid, false otherwise.
     */
    private boolean isValidCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < Board.BOARD_SIZE;
    }
}
