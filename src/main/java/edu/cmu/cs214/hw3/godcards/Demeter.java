package edu.cmu.cs214.hw3.godcards;

import edu.cmu.cs214.hw3.*;

/**
 * The {@code Demeter} class represents the Demeter god card in the Santorini game.
 * Demeter allows a worker to build one additional time, but not on the same space.
 */
public class Demeter implements GodCard {
    private boolean usedAdditionalBuild;

    /**
     * Constructs a new {@code Demeter} instance.
     */
    public Demeter() {
        this.usedAdditionalBuild = false;
    }

    @Override
    public boolean modifyMove(Worker worker, Cell targetCell) {
        return true;
    }

    @Override
    public boolean modifyBuild(Worker worker, Cell targetCell) {
        if (!usedAdditionalBuild) {
            usedAdditionalBuild = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean checkWinCondition(Cell currentCell) {
        return false;
    }

    /**
     * Resets the additional build flag at the end of the turn.
     */
    public void resetAdditionalBuild() {
        this.usedAdditionalBuild = false;
    }
}
