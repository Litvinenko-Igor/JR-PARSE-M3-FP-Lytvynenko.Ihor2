package quest.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class QuestScenarioTest {

    private QuestScenario scenario;

    @BeforeEach
    void setUp() {
        scenario = new QuestScenario();
    }

    private GameStep createTestStep(int id, String text) {
        GameStep step = new GameStep();
        step.setId(id);
        step.setText(text);
        return step;
    }

    @Test
    void indexSteps_withValidList_shouldCreateIndexCorrectly() {
        GameStep step1 = createTestStep(1, "Start");
        GameStep step2 = createTestStep(2, "Middle");
        scenario.setSteps(Arrays.asList(step1, step2));

        scenario.indexSteps();

        assertNotNull(scenario.getStepMap());
        assertEquals(2, scenario.getStepMap().size());
        assertEquals(step1, scenario.getStep(1));
        assertEquals(step2, scenario.getStep(2));
    }

    @Test
    void indexSteps_withEmptyList_shouldCreateEmptyMap() {
        scenario.setSteps(Collections.emptyList());

        scenario.indexSteps();

        assertNotNull(scenario.getStepMap());
        assertTrue(scenario.getStepMap().isEmpty());
    }

    @Test
    void indexSteps_withNullList_shouldNotThrowExceptionAndMapIsNull() {
        scenario.setSteps(null);

        assertDoesNotThrow(() -> scenario.indexSteps());
        assertNull(scenario.getStepMap());
    }

    @Test
    void indexSteps_withDuplicateIds_shouldThrowIllegalStateException() {
        GameStep step1 = createTestStep(1, "First Step");
        GameStep step2_duplicate = createTestStep(1, "Duplicate Step");
        scenario.setSteps(Arrays.asList(step1, step2_duplicate));

        assertThrows(IllegalStateException.class, () -> {
            scenario.indexSteps();
        });
    }

    @Test
    void getStep_whenCalledBeforeIndexing_shouldThrowNullPointerException() {

        assertThrows(NullPointerException.class, () -> {
            scenario.getStep(1);
        });
    }

    @Test
    void getStep_withNonExistentId_shouldReturnNull() {
        scenario.setSteps(Collections.singletonList(createTestStep(1, "Only Step")));
        scenario.indexSteps();

        GameStep result = scenario.getStep(999);

        assertNull(result);
    }
}
