package quest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class PlayerStatsTest {
    private PlayerStats stats;

    @BeforeEach
    void setUp() {
        stats = new PlayerStats("TestPlayer");
    }

    @Test
    @DisplayName("Перевірка початкового стану статистики")
    void initialStateIsCorrect() {
        assertEquals("TestPlayer", stats.getPlayerName());
        assertEquals(0, stats.getGamesPlayed());
        assertEquals(0, stats.getVictories());
        assertEquals(0, stats.getDefeats());
        assertEquals(0, stats.getCurrentStreak());
        assertEquals(0, stats.getBestStreak());
        assertEquals(0.0, stats.getWinRate());
    }

    @Test
    @DisplayName("Перевірка підрахунку перемог та поразок")
    void correctlyCountsVictoriesAndDefeats() {
        stats.addGameResult(true);  // Перемога
        assertEquals(1, stats.getVictories());
        assertEquals(0, stats.getDefeats());
        assertEquals(1, stats.getGamesPlayed());

        stats.addGameResult(false);  // Поразка
        assertEquals(1, stats.getVictories());
        assertEquals(1, stats.getDefeats());
        assertEquals(2, stats.getGamesPlayed());
    }

    @Test
    @DisplayName("Перевірка обчислення відсотка перемог")
    void correctlyCalculatesWinRate() {
        stats.addGameResult(true);   // 100%
        assertEquals(100.0, stats.getWinRate());

        stats.addGameResult(false);  // 50%
        assertEquals(50.0, stats.getWinRate());

        stats.addGameResult(true);   // 66.66...%
        assertEquals(66.66666666666666, stats.getWinRate());
    }

    @Test
    @DisplayName("Перевірка підрахунку серій перемог")
    void correctlyTracksStreaks() {
        stats.addGameResult(true);
        assertEquals(1, stats.getCurrentStreak());
        assertEquals(1, stats.getBestStreak());

        stats.addGameResult(true);
        assertEquals(2, stats.getCurrentStreak());
        assertEquals(2, stats.getBestStreak());

        stats.addGameResult(false);
        assertEquals(0, stats.getCurrentStreak());
        assertEquals(2, stats.getBestStreak());

        stats.addGameResult(true);
        assertEquals(1, stats.getCurrentStreak());
        assertEquals(2, stats.getBestStreak());

        // Перевершуємо попередню найкращу серію
        stats.addGameResult(true);
        stats.addGameResult(true);
        assertEquals(3, stats.getCurrentStreak());
        assertEquals(3, stats.getBestStreak());
    }
}

