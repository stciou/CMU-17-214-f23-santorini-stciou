package edu.cmu.cs214.hw3.godcards;

import edu.cmu.cs214.hw3.*;

/**
 * The {@code Pan} class represents the Pan god card in the Santorini game.
 * Pan allows a player to win if their Worker moves down two or more levels.
 */
public class Pan implements GodCard {

    /**
     * Modifies the worker's move to allow the worker to win if it moves down two or more levels.
     * @param worker The worker to modify.
     * @param targetCell The target cell to move to.
     * @return {@code true} if the move is successful; {@code false} otherwise.
     */
    @Override
    public boolean modifyMove(Worker worker, Cell targetCell) {
        worker.setLastLevel(worker.getCurrentCell().getLevel());
    
        if (worker.move(targetCell)) {
            return true;
        }
        return false;
    }    

    /**
     * Modifies the worker's build to allow the worker to win if it builds down two or more levels.
     * @param worker The worker to modify.
     * @param targetCell The target cell to build on.
     * @return {@code true} if the build is successful; {@code false} otherwise.
     */
    @Override
    public boolean modifyBuild(Worker worker, Cell targetCell) {
        return true;
    }

    /**
     * Checks the win condition for a player based on the rules of the god card.
     * @param currentCell the current cell of the worker.
     * @return {@code true} if the player has won according to the god card's win condition; {@code false} otherwise.
     */
    @Override
    public boolean checkWinCondition(Cell currentCell) {
        Worker worker = currentCell.getWorker();
        if (worker != null) {
            int levelDifference = worker.getLastLevel() - currentCell.getLevel();
            return levelDifference >= 2 || currentCell.getLevel() == 3; 
        }
        return false;
    }
    
        /**
     * Resets the additional build flag at the end of the turn.
     */
    @Override
    public void resetAdditionalBuild() {
    }
}
