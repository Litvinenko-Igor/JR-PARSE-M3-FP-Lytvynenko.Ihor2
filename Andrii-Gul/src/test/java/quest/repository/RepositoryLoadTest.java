package quest.repository;

import org.junit.jupiter.api.Test;
import quest.module.QuestScenario;
import quest.module.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryLoadTest {

    @Test
    void questRepository_shouldLoadScenario() {
        QuestRepository repository = QuestRepository.getInstance();
        assertNotNull(repository);

        QuestScenario scenario = repository.getScenario("forest_quest");
        assertNotNull(scenario);
        assertFalse(scenario.getSteps().isEmpty());
        assertEquals(100, scenario.getInitialPlayerHp());
        assertNotNull(scenario.getStep(1)); // Проверяем, что индексация сработала
    }

    @Test
    void questRepository_shouldThrowExceptionForNonExistentScenario() {
        QuestRepository repository = QuestRepository.getInstance();
        assertThrows(RuntimeException.class, () -> repository.getScenario("non_existent_scenario"));
    }

    @Test
    void questionRepository_shouldLoadQuestions() {
        QuestionRepository repository = QuestionRepository.getInstance();
        assertNotNull(repository);

        List<Question> questions = repository.getAllQuestions();
        assertNotNull(questions);
        assertFalse(questions.isEmpty());

        Question firstQuestion = questions.get(0);
        assertEquals(1, firstQuestion.getId());
        assertEquals("Які річки знаходяться в Африці?", firstQuestion.getText());
        assertNotNull(firstQuestion.getContinent());
    }
}
