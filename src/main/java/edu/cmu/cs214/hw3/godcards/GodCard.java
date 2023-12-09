package edu.cmu.cs214.hw3.godcards;

import edu.cmu.cs214.hw3.*;

/**
 * The {@code GodCard} interface represents a god card in the Santorini game.
 * Each god card can modify move and build actions and define its own win condition.
 */
public interface GodCard {
    /**
     * Modifies the move action for a worker based on the rules of the god card.
     *
     * @param worker     the worker attempting to move.
     * @param targetCell the target cell where the worker wants to move.
     * @return {@code true} if the move is allowed and successful; {@code false} otherwise.
     */
    boolean modifyMove(Worker worker, Cell targetCell);

    /**
     * Modifies the build action for a worker based on the rules of the god card.
     *
     * @param worker     the worker attempting to build.
     * @param targetCell the target cell where the worker wants to build.
     * @return {@code true} if the build is allowed and successful; {@code false} otherwise.
     */
    boolean modifyBuild(Worker worker, Cell targetCell);

    /**
     * Checks the win condition for a player based on the rules of the god card.
     *
     * @param currentCell the current cell of the worker.
     * @return {@code true} if the player has won according to the god card's win condition; {@code false} otherwise.
     */
    boolean checkWinCondition(Cell currentCell);

    /**
     * Resets any additional build flags at the end of the turn.
     */
    void resetAdditionalBuild();
}
