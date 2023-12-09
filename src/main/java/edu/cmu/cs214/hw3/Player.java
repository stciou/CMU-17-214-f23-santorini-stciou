package edu.cmu.cs214.hw3;

import edu.cmu.cs214.hw3.godcards.GodCard;

/**
 * The {@code Player} class represents a player in the game, managing the player's workers and actions.
 */
public class Player {
    private Worker worker1;
    private Worker worker2;
    private Board board;
    private GodCard godCard;
    private String name;

    public Player(String name, Board board) {
        this.worker1 = new Worker(this);
        this.worker2 = new Worker(this);
        this.board = board;
        this.name = name;
    }

    /**
     * Gets the god card assigned to the player.
     *
     * @return the god card of the player.
     */
    public GodCard getGodCard() {
        return godCard;
    }

    /**
     * Gets the name of the player.
     * @return the name of the player.
     */
    public String getName() {
        return name;
    }

        /**
     * Sets the god card for the player.
     *
     * @param godCard the god card to be assigned to the player.
     */
    public void setGodCard(GodCard godCard) {
        this.godCard = godCard;
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
     * Places the player's workers on the board.
     *
     * @param x1 the x-coordinate of the first worker.
     * @param y1 the y-coordinate of the first worker.
     * @param x2 the x-coordinate of the second worker.
     * @param y2 the y-coordinate of the second worker.
     */
    public void placeWorkers(int x1, int y1, int x2, int y2) {
        Cell cell1 = board.getCell(x1, y1);
        Cell cell2 = board.getCell(x2, y2);
        
        this.worker1.setCurrentCell(cell1);
        cell1.setWorker(worker1); 
    
        this.worker2.setCurrentCell(cell2);
        cell2.setWorker(worker2);
    }
    
    

    /**
     * Takes a turn for the player, moving and building with the specified workers.
     *
     * @param workerNum the number of the worker to move and build with (1 or 2).
     * @param moveToX the x-coordinate of the cell to move to.
     * @param moveToY the y-coordinate of the cell to move to.
     * @param buildAtX the x-coordinate of the cell to build on.
     * @param buildAtY the y-coordinate of the cell to build on.
     * @return {@code true} if the turn is successful; {@code false} otherwise.
     */
    public boolean takeTurn(int workerNum, int moveToX, int moveToY, int buildAtX, int buildAtY) {
        Worker chosenWorker = chooseWorker(workerNum);
    
        if (!chosenWorker.move(board.getCell(moveToX, moveToY))) {
            return false;
        }
    
        if (godCard != null && godCard.checkWinCondition(chosenWorker.getCurrentCell())) {
            System.out.println(name + " has won!");
            return true;
        }
    
        return chosenWorker.build(board.getCell(buildAtX, buildAtY));
    }
}
