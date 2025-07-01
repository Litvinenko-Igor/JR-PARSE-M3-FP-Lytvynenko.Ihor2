package quest.module;

import lombok.Data;

@Data
public class Player {
    private int id;
    private String playerName;
    private int score;
    private int gamesPlayed;

    public Player(String playerName) {
        this.playerName = playerName;
        this.score = 0;
        this.gamesPlayed = 1;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }
}
