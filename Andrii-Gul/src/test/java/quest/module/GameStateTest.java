package quest.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quest.repository.QuestRepository;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState gameState;
    private static final String TEST_SCENARIO_NAME = "forest_quest";

    @BeforeEach
    void setUp() {
        gameState = new GameState(TEST_SCENARIO_NAME);
    }

    @Test
    void constructor_shouldInitializeGameStateCorrectly() {
        QuestScenario scenario = QuestRepository.getInstance().getScenario(TEST_SCENARIO_NAME);
        assertNotNull(gameState);
        assertEquals(TEST_SCENARIO_NAME, gameState.getScenarioName());
        assertEquals(scenario.getInitialPlayerHp(), gameState.getPlayerHp());
        assertEquals(scenario.getStartStepId(), gameState.getCurrentStepId());
    }

    @Test
    void processAnswer_shouldChangeStepAndHpCorrectly() {
        GameStep initialStep = gameState.getCurrentStep();
        assertEquals(1, initialStep.getId());

        gameState.processAnswer(0);
        assertEquals(2, gameState.getCurrentStepId());
        assertEquals(100, gameState.getPlayerHp()); // HP не изменилось
    }

    @Test
    void processAnswer_shouldDecreaseHpAndChangeStep() {
        gameState.processAnswer(0);
        assertEquals(2, gameState.getCurrentStepId());

        gameState.processAnswer(0);
        assertEquals(101, gameState.getCurrentStepId());
        assertEquals(70, gameState.getPlayerHp()); // 100 - 30 = 70
    }

    @Test
    void processAnswer_shouldLeadToGameOverWhenHpIsZeroOrLess() {
        gameState.processAnswer(1);
        assertEquals(3, gameState.getCurrentStepId());

        gameState.processAnswer(0);
        assertEquals(102, gameState.getCurrentStepId());
        assertEquals(50, gameState.getPlayerHp());

        gameState.setPlayerHp(10);
        gameState.setCurrentStepId(2);
        gameState.processAnswer(0);

        assertEquals(999, gameState.getCurrentStepId());
        assertTrue(gameState.getPlayerHp() <= 0);
    }

    @Test
    void processAnswer_shouldDoNothingOnTerminalStep() {
        gameState.setCurrentStepId(6);
        assertEquals(6, gameState.getCurrentStepId());
        assertTrue(gameState.getCurrentStep().isTerminal());

        gameState.processAnswer(0);

        assertEquals(6, gameState.getCurrentStepId());
    }
}
