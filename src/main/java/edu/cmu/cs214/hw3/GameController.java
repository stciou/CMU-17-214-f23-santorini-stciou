package edu.cmu.cs214.hw3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The GameController class is the controller for the game.
 * It handles all the requests from the client and updates the game state accordingly.
 * It also returns the game state to the client.
 */
@RestController
@RequestMapping("/api")
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
    public ResponseEntity<Game> move(@RequestBody MoveRequest moveRequest) {
        boolean moveSuccess = game.performMove(moveRequest.getX(), moveRequest.getY(), moveRequest.getPlayer());

        if (moveSuccess) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.badRequest().body(game);
        }
    }

    @PostMapping("/performMove")
    public ResponseEntity<?> performMove(@RequestBody MoveRequest moveRequest) {
        boolean success = game.performMove(moveRequest.getX(), moveRequest.getY(), moveRequest.getPlayer());

        if (success) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Move not possible");
        }
    }

    @GetMapping("/checkWinner")
    public ResponseEntity<String> checkWinner() {
        String winner = game.checkWinner();
        if (winner != null) {
            return ResponseEntity.ok(winner);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    
    @PostMapping("/build")
    public ResponseEntity<Game> build(@RequestBody BuildRequest buildRequest) { 
        boolean buildSuccess = game.performBuild(buildRequest.getX(), buildRequest.getY(), buildRequest.getPlayer());

        if (buildSuccess) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.badRequest().body(game);
        }
    }

    @PostMapping("/selectGodCard")
    public ResponseEntity<?> selectGodCard(@RequestBody GodCardSelectionRequest request) {
        boolean success = game.selectGodCard(request.getPlayerName(), request.getGodCardName());
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("God card selection failed");
        }
    }

    @GetMapping("/state")
    public ResponseEntity<?> getGameState() {
        return ResponseEntity.ok(game);
    }
}
