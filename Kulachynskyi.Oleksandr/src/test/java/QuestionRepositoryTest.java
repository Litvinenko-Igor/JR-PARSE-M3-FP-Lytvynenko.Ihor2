
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.javarush.m3.fp.model.Question;
import ua.com.javarush.m3.fp.repository.QuestionRepository;


import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryTest {

    private QuestionRepository repository;

    @BeforeEach
    void setUp() {
        repository = QuestionRepository.getInstance();
    }

    @Test
    void testGetQuestionReturnsStartStateWhenStateExists() {
        Question question = repository.getQuestion("start");
        assertNotNull(question, "Питання для стану 'start' має бути не null");
        assertFalse(question.isGameOver(), "Стан 'start' не повинен бути кінцевим");
        assertNotNull(question.getChoices(), "Опції вибору для 'start' мають бути присутні");
        assertTrue(question.getChoices().containsKey("door"), "Опція 'door' має бути доступна");
        assertTrue(question.getChoices().containsKey("backyard"), "Опція 'backyard' має бути доступна");
    }

    @Test
    void testGetQuestionReturnsDefaultWhenStateNotFound() {
        Question question = repository.getQuestion("nonExistentState");
        assertNotNull(question, "Питання для неіснуючого стану має повернутися (за замовчуванням 'start')");
        assertEquals("Ти опинився перед старовинним будинком, оточеним густим туманом. Вітер гудить у віконницях, а шепоти лоскочуть вуха. Що робиш?",
                question.getDescription(), "Опис має відповідати стану 'start'");
    }
}