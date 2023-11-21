package edu.cmu.cs214.hw3;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Board} class represents the game board where players move and build.
 * It consists of a 5x5 grid of {@code Cell} objects.
 */
public class Board {
    public static final int BOARD_SIZE = 5;
    private Cell[][] grid = new Cell[5][5];

    /**
     * Constructs a new {@code Board} and initializes each grid position with a new {@code Cell}.
     */
    public Board() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Retrieves the {@code Cell} object at the specified grid position.
     *
     * @param x the x-coordinate of the cell.
     * @param y the y-coordinate of the cell.
     * @return the {@code Cell} object at position (x, y) if the position is valid; {@code null} otherwise.
     */
    public Cell getCell(int x, int y) {
        if (isValidPosition(x, y)) {
            return grid[x][y];
        }
        return null;
    }

    /**
     * Retrieves a list of adjacent {@code Cell} objects to the specified grid position.
     *
     * @param x the x-coordinate of the cell.
     * @param y the y-coordinate of the cell.
     * @return a {@code List} of adjacent {@code Cell} objects.
     */
    public List<Cell> getAdjacentCells(int x, int y) {
        List<Cell> adjacentCells = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (isValidPosition(x + i, y + j)) {
                    adjacentCells.add(grid[x + i][y + j]);
                }
            }
        }
        return adjacentCells;
    }

    /**
     * Checks if the specified grid position is valid.
     *
     * @param x the x-coordinate of the cell.
     * @param y the y-coordinate of the cell.
     * @return {@code true} if the position (x, y) is within the grid bounds; {@code false} otherwise.
     */
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }

    /**
     * Places a {@code Worker} on the specified grid position if the position is valid and not occupied.
     *
     * @param x      the x-coordinate of the cell.
     * @param y      the y-coordinate of the cell.
     * @param worker the {@code Worker} to be placed.
     */
    public void placeWorker(int x, int y, Worker worker) {
        if (isValidPosition(x, y) && !grid[x][y].isOccupied()) {
            grid[x][y].setWorker(worker);
            worker.setCurrentCell(grid[x][y]);
        }
    }

    /**
     * Checks if a move action is allowed by a god card.
     * This method should be called by the god card's logic.
     *
     * @param fromX the x-coordinate of the worker's current position.
     * @param fromY the y-coordinate of the worker's current position.
     * @param toX   the x-coordinate of the worker's new position.
     * @param toY   the y-coordinate of the worker's new position.
     * @return {@code true} if the move is allowed; {@code false} otherwise.
     */
    public boolean isMoveAllowedByGodCard(int fromX, int fromY, int toX, int toY) {
        return true;
    }

    /**
     * Checks if a build action is allowed by a god card.
     * This method should be called by the god card's logic.
     *
     * @param x the x-coordinate of the build position.
     * @param y the y-coordinate of the build position.
     * @return {@code true} if the build is allowed; {@code false} otherwise.
     */
    public boolean isBuildAllowedByGodCard(int x, int y) {
        return true;
    }
}
