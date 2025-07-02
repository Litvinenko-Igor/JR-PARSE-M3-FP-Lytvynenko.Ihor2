package quest.model;

import lombok.Getter;

@Getter
public class PlayerStats {
    private String playerName;
    private int gamesPlayed;
    private int victories;
    private int defeats;
    private int currentStreak;
    private int bestStreak;

    public PlayerStats(String playerName) {
        this.playerName = playerName;
        this.gamesPlayed = 0;
        this.victories = 0;
        this.defeats = 0;
        this.currentStreak = 0;
        this.bestStreak = 0;
    }

    public void addGameResult(boolean isVictory) {
        gamesPlayed++;
        if (isVictory) {
            victories++;
            currentStreak++;
            if (currentStreak > bestStreak) {
                bestStreak = currentStreak;
            }
        } else {
            defeats++;
            currentStreak = 0;
        }
    }


    public double getWinRate() {
        return gamesPlayed == 0 ? 0 : (double) victories / gamesPlayed * 100;
    }
}