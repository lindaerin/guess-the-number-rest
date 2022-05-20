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

import mastermind.model.Round;

@Repository
public class MastermindRoundDaoDB implements MastermindRoundDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Round> getAllRounds() {
        final String SELECT_ALL_ROUNDS = "SELECT * FROM round";
        return jdbc.query(SELECT_ALL_ROUNDS, new RoundMapper());
    }

    @Override
    public Round getRoundById(int id) {
        try {
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE id = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), id);
        }catch(DataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional 
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO round(guess, timeOfGuess, result, gameId) VALUES(?, ?, ?, ?)";
        jdbc.update(INSERT_ROUND,
                round.getGuess(),
                round.getTimeOfGuess(),
                round.getResult(),
                round.getGameId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setId(newId);
        return round;
    }

    @Override
    public void updateRound(Round round) {
        final String UPDATE_ROUND = "UPDATE round SET guess = ?, timeOfGuess = ?, result = ?, gameId = ? WHERE id = ?";
        jdbc.update(UPDATE_ROUND,
                round.getGuess(),
                round.getTimeOfGuess(),
                round.getResult(),
                round.getGameId(),
                round.getId());
    }

    @Override
    @Transactional
    public void deleteRoundById(int id) {
        final String DELETE_ROUND = "DELETE FROM round WHERE id = ?";
        jdbc.update(DELETE_ROUND, id);
    }
    
    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setId(rs.getInt("id"));
            round.setGuess(rs.getString("guess"));
            round.setTimeOfGuess((rs.getTimestamp("timeOfGuess").toLocalDateTime()));
            round.setResult(rs.getString("result"));
            round.parseResult();
            round.setGameId(rs.getInt("gameId"));
            return round;
        }
    }
    
}
