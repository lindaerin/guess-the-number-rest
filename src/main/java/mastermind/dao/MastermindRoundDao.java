package mastermind.dao;

import java.util.List;

import mastermind.model.Round;

public interface MastermindRoundDao {
    List<Round> getAllRounds();
    Round getRoundById(int id);
    Round addRound(Round round);
    // void updateRound(Round round);
    void deleteRoundById(int id);
}
