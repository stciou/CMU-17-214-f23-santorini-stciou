package edu.cmu.cs214.hw3;

/**
 * The {@code Cell} class represents a single cell on the game board.
 * It contains information about its position, the {@code Tower} on it,
 * and the {@code Worker} that may occupy it.
 */
public class Cell {
    private Tower tower;
    private Worker worker;
    private int x;
    private int y;

    /**
     * Constructs a new {@code Cell} with specified x and y coordinates.
     * Initializes the cell with a new {@code Tower} and no {@code Worker}.
     *
     * @param x the x-coordinate of the cell.
     * @param y the y-coordinate of the cell.
     */
    public Cell(int x, int y) {
        this.tower = new Tower();
        this.worker = null;
        this.x = x;
        this.y = y;
    }

    /**
     * Retrieves the {@code Tower} on the cell.
     * @return the tower on the cell.
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the {@code Tower} on the cell.
     * @return the tower on the cell.
     */
    public int getY() {
        return y;
    }

    /**
     * Retrieves the current level of the {@code Tower} on the cell.
     *
     * @return the level of the tower.
     */
    public int getLevel() {
        return tower.getLevel();
    }

    /**
     * Increases the level of the {@code Tower} on the cell by 1.
     */
    public void increaseLevel() {
        tower.increaseLevel();
    }

    /**
     * Checks if the {@code Tower} on the cell has a dome.
     *
     * @return {@code true} if the tower has a dome; {@code false} otherwise.
     */
    public boolean hasDome() {
        return tower.hasDome();
    }

    /**
     * Places a dome on the {@code Tower} of the cell.
     */
    public void placeDome() {
        tower.placeDome();
    }

    /**
     * Retrieves the {@code Worker} that occupies the cell.
     *
     * @return the worker on the cell; {@code null} if no worker is present.
     */
    public Worker getWorker() {
        return worker;
    }

    /**
     * Sets the {@code Worker} that occupies the cell.
     *
     * @param worker the worker to be placed on the cell.
     */
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /**
     * Checks if the cell is occupied by a {@code Worker} or has a dome.
     *
     * @return {@code true} if the cell is occupied; {@code false} otherwise.
     */
    public boolean isOccupied() {
        return worker != null || hasDome();
    }

    /**
     * Checks if the cell is adjacent to another specified cell.
     *
     * @param otherCell the cell to check adjacency with.
     * @return {@code true} if the cells are adjacent; {@code false} otherwise.
     */
    public boolean isAdjacentTo(Cell otherCell) {
        int dx = Math.abs(this.x - otherCell.x);
        int dy = Math.abs(this.y - otherCell.y);
        return dx <= 1 && dy <= 1 && (dx + dy != 0);
    }
}
