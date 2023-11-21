@RestController
@RequestMapping("/game")
public class GameController {

    @PostMapping("/start")
    public ResponseEntity<GameStatus> startGame() {
    }

    @PostMapping("/move")
    public ResponseEntity<GameStatus> makeMove(@RequestBody Move move) {
    }
}
