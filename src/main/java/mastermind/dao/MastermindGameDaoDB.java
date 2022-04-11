package mastermind.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mastermind.model.Game;

@Repository
public class MastermindGameDaoDB implements MastermindGameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new MastermindGameDaoDB.GameMapper());
    }

    @Override
    public Game getGameById(int id) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE id = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new MastermindGameDaoDB.GameMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional 
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game (answer, finished) VALUES(?, ?)";
        jdbc.update(INSERT_GAME,
                game.getAnswer(),
                game.isFinished());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        return game;
    }

    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET answer = ?, finished = ? WHERE id = ?";
        jdbc.update(UPDATE_GAME,
                game.getAnswer(),
                game.isFinished(),
                game.getGameId());
    }

    @Override
    @Transactional
    public void deleteGameById(int id) {
        final String DELETE_GAME = "DELETE FROM game WHERE id = ?";
        jdbc.update(DELETE_GAME, id);
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();

            // names: id, answer, finished
            game.setGameId(rs.getInt("id"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("finished"));
            return game;
        }
    }
    
}
 