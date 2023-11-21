package edu.cmu.cs214.hw3;

/**
 * The {@code Tower} class represents a tower in the game, managing its level and dome status.
 */
public class Tower {
    private int level;
    private boolean hasDome;

    /**
     * Constructs a new {@code Tower} instance, initializing the level to 0 and dome status to false.
     */
    public Tower() {
        this.level = 0;
        this.hasDome = false;
    }

    /**
     * Retrieves the current level of the tower.
     *
     * @return the current level of the tower.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Increases the level of the tower by 1, up to a maximum of 3.
     */
    public void increaseLevel() {
        if (level < 3) {
            level++;
        }
    }

    /**
     * Checks whether the tower has a dome.
     *
     * @return {@code true} if the tower has a dome; {@code false} otherwise.
     */
    public boolean hasDome() {
        return hasDome;
    }

    /**
     * Places a dome on the tower if it is at level 3 and does not already have a dome.
     */
    public void placeDome() {
        if (level == 3 && !hasDome) {
            hasDome = true;
        }
    }
}
