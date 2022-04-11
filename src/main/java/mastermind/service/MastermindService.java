package mastermind.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mastermind.dao.MastermindGameDao;
import mastermind.dao.MastermindRoundDao;
import mastermind.model.Game;
import mastermind.model.Round;

@Component
public class MastermindService {

    @Autowired
    MastermindRoundDao roundDao;

    @Autowired
    MastermindGameDao gameDao;

    // generate string of random unique 4 digit number
    public String generateAnswer() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        String generatedNum = "";
        for (int i = 0; i < 4; i++) {
            generatedNum += list.get(i);
        }

        return generatedNum;
    }

    // calculate the result based on guess
    public String calculateResult(String answer, String guess) {
        int exactMatch = 0;
        int partialMatch = 0;

        String[] ansToken = answer.split("");
        String[] guessToken = guess.split("");

        for (int i = 0; i < answer.length(); i++) {
            if (ansToken[i].equals(guessToken[i])) {
                exactMatch += 1;
            } else if (answer.contains(guessToken[i])) {
                partialMatch += 1;
            }
        }

        String result = "e:" + exactMatch + ":" + "p:" + partialMatch;

        return result;
    }

    // methods for game
    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    public Game getGameById(int gameId) {
        return gameDao.getGameById(gameId);
    }

    public void updateGame(Game game) {
        gameDao.updateGame(game);
    }
    

    // methods for round
    public void validateGuess(Round round) throws InvalidGuessException {
        if (Arrays.stream(round.getGuess().split("")).collect(Collectors.toSet()).size() < 4) {
            throw new InvalidGuessException("Guess should contain 4 unique digits.");
        }
    }

    public Round addRound(Round round) throws InvalidGuessException {
        validateGuess(round);
        return roundDao.addRound(round);
    }

    // public void editRound(Round round) throws InvalidGuessException {
    //     validateGuess(round);
    //     roundDao.updateRound(round);
    // }

    public List<Round> getAllRounds() {
        return roundDao.getAllRounds();
    }

    public Round getRoundById(int id) {
        return roundDao.getRoundById(id);
    }

    public void deleteRoundById(int id) {
        roundDao.deleteRoundById(id);
    }

}
