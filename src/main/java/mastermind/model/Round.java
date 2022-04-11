package mastermind.model;

import java.time.LocalDateTime;

public class Round {
    int id;    
    String guess;   
    LocalDateTime timeOfGuess;
    int numExactMatch; 
    int numPartialMatch;
    String result; 
    int gameId;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getGuess() {
        return guess;
    }
    public void setGuess(String guess) {
        this.guess = guess;
    }
    public LocalDateTime getTimeOfGuess() {
        return timeOfGuess;
    }
    public void setTimeOfGuess(LocalDateTime timeOfGuess) {
        this.timeOfGuess = timeOfGuess;
    }
    public int getNumExactMatch() {
        return numExactMatch;
    }
    public void setNumExactMatch(int numExactMatch) {
        this.numExactMatch = numExactMatch;
    }
    public int getNumPartialMatch() {
        return numPartialMatch;
    }
    public void setNumPartialMatch(int numPartialMatch) {
        this.numPartialMatch = numPartialMatch;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void parseResult() {
        String[] tokens = this.result.split(":");
        this.setNumExactMatch(Integer.parseInt(tokens[1]));
        this.setNumPartialMatch(Integer.parseInt(tokens[3]));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + gameId;
        result = prime * result + ((guess == null) ? 0 : guess.hashCode());
        result = prime * result + id;
        result = prime * result + numExactMatch;
        result = prime * result + numPartialMatch;
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        result = prime * result + ((timeOfGuess == null) ? 0 : timeOfGuess.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Round other = (Round) obj;
        if (gameId != other.gameId)
            return false;
        if (guess == null) {
            if (other.guess != null)
                return false;
        } else if (!guess.equals(other.guess))
            return false;
        if (id != other.id)
            return false;
        if (numExactMatch != other.numExactMatch)
            return false;
        if (numPartialMatch != other.numPartialMatch)
            return false;
        if (result == null) {
            if (other.result != null)
                return false;
        } else if (!result.equals(other.result))
            return false;
        if (timeOfGuess == null) {
            if (other.timeOfGuess != null)
                return false;
        } else if (!timeOfGuess.equals(other.timeOfGuess))
            return false;
        return true;
    }

    
    


}
