package edu.cmu.cs214.hw3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The GameController class is the controller for the game.
 * It handles all the requests from the client and updates the game state accordingly.
 * It also returns the game state to the client.
 */
@RestController
@RequestMapping("/api/game")
public class GameController {

    private Game game;

    public GameController() {
        this.game = new Game();
    }

    @GetMapping("/reset")
    public ResponseEntity<?> resetGame() {
        game = new Game();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGame(@RequestParam String playerName) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinGame(@RequestParam String playerName) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/start")
    public ResponseEntity<?> startGame(@RequestParam String player1, @RequestParam String player2) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/move")
    public ResponseEntity<?> moveWorker(@RequestParam int workerId, @RequestParam int x, @RequestParam int y) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/build")
    public ResponseEntity<?> buildBlock(@RequestParam int x, @RequestParam int y) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/selectGodCard")
    public ResponseEntity<?> selectGodCard(@RequestParam String playerName, @RequestParam String godCardName) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/state")
    public ResponseEntity<?> getGameState() {
        return ResponseEntity.ok(game);
    }
}
