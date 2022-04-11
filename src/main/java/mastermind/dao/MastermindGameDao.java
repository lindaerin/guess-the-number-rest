package mastermind.dao;

import java.util.List;

import mastermind.model.Game;

public interface MastermindGameDao {
    List<Game> getAllGames();
    Game getGameById(int id);
    Game addGame(Game game);
    void updateGame(Game game);
    void deleteGameById(int id);
}
