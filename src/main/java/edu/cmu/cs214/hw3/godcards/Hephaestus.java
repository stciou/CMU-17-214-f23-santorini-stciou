package edu.cmu.cs214.hw3.godcards;

import edu.cmu.cs214.hw3.*;

/**
 * The {@code Hephaestus} class represents the Hephaestus god card in the Santorini game.
 * Hephaestus allows a worker to build one additional block (not dome) on top of the first block.
 */
public class Hephaestus implements GodCard {
    private boolean usedAdditionalBuild;

    public Hephaestus() {
        this.usedAdditionalBuild = false;
    }

    @Override
    public boolean modifyMove(Worker worker, Cell targetCell) {
        return true;
    }

    @Override
    public boolean modifyBuild(Worker worker, Cell targetCell) {
        if (!usedAdditionalBuild && targetCell.getLevel() < 3) {
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
