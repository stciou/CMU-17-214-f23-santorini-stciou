package edu.cmu.cs214.hw3;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.cs214.hw3.godcards.Demeter;
import edu.cmu.cs214.hw3.godcards.GodCard;
import edu.cmu.cs214.hw3.godcards.Hephaestus;
import edu.cmu.cs214.hw3.godcards.Minotaur;
import edu.cmu.cs214.hw3.godcards.Pan;

public class SantoriniTests {
    private Board board;
    private Player player1, player2;
    private Worker worker1, worker2;

    @Before
    public void setUp() {
        board = new Board();
        player1 = new Player("Player1", board);
        player2 = new Player("Player2", board);
    
        player1.placeWorkers(0, 0, 0, 1); 
        player2.placeWorkers(4, 4, 3, 4);
        worker1 = player1.getWorker1();
        worker2 = player2.getWorker1();
    }

    @Test
    public void testWorkerMove() {
        assertTrue(worker1.move(board.getCell(1, 0)));
        assertFalse(worker1.move(board.getCell(5, 5)));
    }

    @Test
    public void testWorkerBuild() {
        assertTrue(worker1.build(board.getCell(1, 0)));
        assertFalse(worker1.build(board.getCell(4, 4)));
    }      

    @Test
    public void testBoardPlaceWorker() {
        Worker anotherWorker = new Worker(player1);

        board.placeWorker(2, 2, anotherWorker);
        assertEquals(anotherWorker, board.getCell(2, 2).getWorker());

        Worker yetAnotherWorker = new Worker(player1);
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
    public void testDemeterBuild() {
        GodCard demeter = new Demeter();
        player1.setGodCard(demeter);
        player1.placeWorkers(1, 0, 2, 0);
        Worker worker = player1.getWorker1();
    
        assertTrue(worker.move(board.getCell(1, 1)));
        assertTrue(worker.build(board.getCell(2, 1)));
        demeter.resetAdditionalBuild();
        assertTrue(worker.build(board.getCell(2, 2)));
    }

    @Test
    public void testHephaestusBuild() {
        GodCard hephaestus = new Hephaestus();
        player2.setGodCard(hephaestus);
        
        assertFalse(board.getCell(4, 3).isOccupied());
        assertTrue(player2.getWorker1().move(board.getCell(4, 3)));
        assertTrue(player2.getWorker1().build(board.getCell(4, 4)));
        hephaestus.resetAdditionalBuild();
        assertTrue(player2.getWorker1().build(board.getCell(4, 4)));
    }
    
    @Test
    public void testMinotaurMove() {
        GodCard minotaur = new Minotaur(board);
        player2.setGodCard(minotaur);
    
        board.placeWorker(3, 2, worker2);
        board.placeWorker(3, 3, worker1);
    
        assertTrue(board.isValidPosition(3, 4));
        assertEquals(board.getCell(3, 3), player1.getWorker1().getCurrentCell());
    }
    
    @Test
    public void testPanWinCondition() {
        GodCard pan = new Pan();
        player1.setGodCard(pan);
    
        board.getCell(0, 0).increaseLevel();
        board.getCell(0, 0).increaseLevel();
        worker1.setCurrentCell(board.getCell(0, 0));
    
        assertTrue(board.getCell(0, 1).getLevel() <= 0);
    }
}
