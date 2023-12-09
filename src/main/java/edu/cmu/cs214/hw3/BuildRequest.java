package edu.cmu.cs214.hw3;

/**
 * This class represents a request to build on the game board.
 */
public class BuildRequest {
    private int x;
    private int y;
    private String player;

    public BuildRequest() {
    }

    public BuildRequest(int x, int y, String player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}