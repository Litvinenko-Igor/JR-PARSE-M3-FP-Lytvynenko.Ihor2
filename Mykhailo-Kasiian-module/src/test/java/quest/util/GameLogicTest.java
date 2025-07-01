package quest.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {
    private GameLogic gameLogic;

    @BeforeEach
    void setUp() {
        gameLogic = new GameLogic();
    }

    @Test
    @DisplayName("Початковий стан гри має бути START")
    void initialStateIsStart() {
        assertEquals(GameLogic.Step.START, gameLogic.getStep());
    }

    @Test
    @DisplayName("Перевірка правильного шляху до перемоги")
    void correctPathToVictory() {
        // Крок 1: Прийняти виклик
        gameLogic.processAnswer("Прийняти виклик");
        assertEquals(GameLogic.Step.CALL_ACCEPTED, gameLogic.getStep());
        assertFalse(gameLogic.isVictory());
        assertFalse(gameLogic.isGameOver());

        // Крок 2: Піднятися на місток
        gameLogic.processAnswer("Піднятися на місток");
        assertEquals(GameLogic.Step.ON_BRIDGE, gameLogic.getStep());
        assertFalse(gameLogic.isVictory());
        assertFalse(gameLogic.isGameOver());

        // Крок 3: Розповісти правду
        gameLogic.processAnswer("Розповісти правду про себе");
        assertEquals(GameLogic.Step.VICTORY, gameLogic.getStep());
        assertTrue(gameLogic.isVictory());
        assertFalse(gameLogic.isGameOver());
    }

    @Test
    @DisplayName("Перевірка неправильних відповідей на кожному кроці")
    void wrongAnswersLeadToGameOver() {
        // На старті
        gameLogic.processAnswer("Відхилити виклик");
        assertTrue(gameLogic.isGameOver());
        assertFalse(gameLogic.isVictory());

        // Після прийняття виклику
        gameLogic = new GameLogic();
        gameLogic.processAnswer("Прийняти виклик");
        gameLogic.processAnswer("Відмовитися");
        assertTrue(gameLogic.isGameOver());
        assertFalse(gameLogic.isVictory());

        // На містку
        gameLogic = new GameLogic();
        gameLogic.processAnswer("Прийняти виклик");
        gameLogic.processAnswer("Піднятися на місток");
        gameLogic.processAnswer("Збрехати про себе");
        assertTrue(gameLogic.isGameOver());
        assertFalse(gameLogic.isVictory());
    }

    @Test
    @DisplayName("Перевірка отримання правильних питань для кожного кроку")
    void correctQuestionsForEachStep() {
        // Початкове питання
        assertEquals("Ви втрачаєте пам'ять. Прийняти виклик НЛО?", gameLogic.getQuestion());

        // Після прийняття виклику
        gameLogic.processAnswer("Прийняти виклик");
        assertEquals("Ви прийняли виклик. Піднятися на капітанський місток?", gameLogic.getQuestion());

        // На містку
        gameLogic.processAnswer("Піднятися на місток");
        assertEquals("Ви піднялися на місток. Хто ви?", gameLogic.getQuestion());
    }

    @Test
    @DisplayName("Перевірка отримання правильних варіантів відповідей для кожного кроку")
    void correctOptionsForEachStep() {
        // Початкові опції
        assertArrayEquals(
                new String[]{"Прийняти виклик", "Відхилити виклик"},
                gameLogic.getOptions()
        );

        // Опції після прийняття виклику
        gameLogic.processAnswer("Прийняти виклик");
        assertArrayEquals(
                new String[]{"Піднятися на місток", "Відмовитися"},
                gameLogic.getOptions()
        );

        // Опції на містку
        gameLogic.processAnswer("Піднятися на місток");
        assertArrayEquals(
                new String[]{"Розповісти правду про себе", "Збрехати про себе"},
                gameLogic.getOptions()
        );

        // Опції після завершення гри
        gameLogic.processAnswer("Збрехати про себе");
        assertArrayEquals(new String[]{}, gameLogic.getOptions());
    }
}
