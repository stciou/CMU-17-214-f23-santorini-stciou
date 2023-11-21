package edu.cmu.cs214.hw3.godcards;

import edu.cmu.cs214.hw3.*;

/**
 * The {@code Pan} class represents the Pan god card in the Santorini game.
 * Pan allows a player to win if their Worker moves down two or more levels.
 */
public class Pan implements GodCard {
    @Override
    public boolean modifyMove(Worker worker, Cell targetCell) {
        return true;
    }

    @Override
    public boolean modifyBuild(Worker worker, Cell targetCell) {
        return true;
    }

    @Override
    public boolean checkWinCondition(Cell currentCell) {
        Worker worker = currentCell.getWorker();
        if (worker != null) {
            int levelDifference = worker.getLastLevel() - currentCell.getLevel();
            return levelDifference >= 2 || currentCell.getLevel() == 3; // Assuming level 3 is the top level
        }
        return false;
    }   
}
