package mastermind.controller;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mastermind.model.Game;
import mastermind.model.Round;
import mastermind.service.InvalidGuessException;
import mastermind.service.MastermindService;

@RestController
@RequestMapping("/api/mastermind")
public class MastermindController {

    private final MastermindService service;

    public MastermindController(MastermindService service){
        this.service = service;
    }
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity beginGame(){
        // "begin" - POST – Starts a game, generates an answer, and sets the correct status.
        // Should return a 201 CREATED message

        Game game = new Game();

        // call service layer to generate a unique number
        game.setAnswer(service.generateAnswer());
        game.setFinished(false);
        service.addGame(game);
        
        String message = "New game created: Game Id #: " + game.getGameId();

        System.out.println("Number:" + game.getAnswer());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/game")
    public List<Game> getAllGames() {
        // game" – GET – Returns a list of all games. Be sure in-progress games do not display their answer.
        
        List<Game> games = service.getAllGames();
        for (Game game : games) {

            if (!game.isFinished()) {
                game.setAnswer("Game still in progress. Finish the game to see answer.");
            }
        }
        return games;
    }

    @GetMapping("/game/{gameId}")
    // game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
    public ResponseEntity<Game>  getGameById(@PathVariable int gameId) {
        String message = String.format("Game #%s does not exist. ", gameId);
        Game game = service.getGameById(gameId);
        if (game == null) {
            return new ResponseEntity(message, HttpStatus.NOT_FOUND);
        }

        if (!game.isFinished()) {
            game.setAnswer("Game still in progress. Finish the game to see answer.");
        }

        return ResponseEntity.ok(game);
    }

    @PostMapping("/guess")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Round> create(@RequestBody Round round) throws InvalidGuessException {

        round.setTimeOfGuess(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        
        Game game = service.getGameById(round.getGameId());

        if (game == null) {
            return new ResponseEntity("Game not found.", HttpStatus.NOT_FOUND);
        }

        if (game.isFinished()){
            return new ResponseEntity("You cannot guess for a finished game.", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String result = service.calculateResult(game.getAnswer(), round.getGuess());
        round.setResult(result);
        round.parseResult();

        // if all digits match set game status to finish
        if (round.getNumExactMatch() == 4) {
            game.setFinished(true);
            service.updateGame(game);
        }
        service.addRound(round);
        return ResponseEntity.ok(round);
    }

}
