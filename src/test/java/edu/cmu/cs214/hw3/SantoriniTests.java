package edu.cmu.cs214.hw3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SantoriniTests {
    private Board board;
    private Player player;
    private Worker worker;

    @Before
    public void setUp() {
        board = new Board();
        player = new Player(board);
        player.placeWorkers(0, 0, 0, 1);
        worker = player.getWorker1();
    }

    @Test
    public void testWorkerMove() {
        assertTrue(worker.move(board.getCell(1, 0)));
        assertFalse(worker.move(board.getCell(5, 5)));
    }

    @Test
    public void testWorkerBuild() {
        assertTrue(worker.build(board.getCell(1, 0)));
        assertFalse(worker.build(board.getCell(0, 1)));
    }

    @Test
    public void testBoardPlaceWorker() {
        Worker anotherWorker = new Worker(player);

        board.placeWorker(2, 2, anotherWorker);
        assertEquals(anotherWorker, board.getCell(2, 2).getWorker());

        Worker yetAnotherWorker = new Worker(player);
        board.placeWorker(2, 2, yetAnotherWorker);
        assertNotEquals(yetAnotherWorker, board.getCell(2, 2).getWorker());
    }

    @Test
    public void testTowerFunctions() {
        Tower tower = new Tower();

        tower.increaseLevel();
        assertEquals(1, tower.getLevel());

        tower.placeDome();
        assertFalse(tower.hasDome());

        tower.increaseLevel();
        tower.increaseLevel();
        tower.placeDome();
        assertTrue(tower.hasDome());
    }

    @Test
    public void testGamePlayWinningSequence() {
        Game game = new Game();
        Player player1 = game.getPlayer1();

        assertTrue(player1.takeTurn(1, 1, 0, 1, 1));
        assertTrue(player1.takeTurn(1, 2, 0, 2, 1));
        assertTrue(player1.takeTurn(1, 3, 0, 3, 1));
        assertTrue(player1.takeTurn(1, 3, 1, 3, 0));
    }

    @Test
    public void testGamePlayInvalidSequence() {
        Game game = new Game();
        Player player1 = game.getPlayer1();

        assertFalse(player1.takeTurn(1, 5, 5, 1, 1));
    }
}
