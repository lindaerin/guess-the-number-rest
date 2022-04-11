package mastermind.model;

public class Game {
    int gameId;
    String answer;
    boolean finished;

    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((answer == null) ? 0 : answer.hashCode());
        result = prime * result + (finished ? 1231 : 1237);
        result = prime * result + gameId;
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
        Game other = (Game) obj;
        if (answer == null) {
            if (other.answer != null)
                return false;
        } else if (!answer.equals(other.answer))
            return false;
        if (finished != other.finished)
            return false;
        if (gameId != other.gameId)
            return false;
        return true;
    }
}
