
import org.junit.jupiter.api.Test;
import root.model.Question;
import root.model.Result;
import root.model.Stage;
import root.questRepository.QuestRepository;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class QuestRepositoryTest {

    @Test
    void testLoadStagesSuccessfully() throws IOException {
        QuestRepository repo = new QuestRepository("test_quest.json");

        Stage stage0 = repo.getStage("0");
        assertNotNull(stage0);
        assertEquals("0", stage0.getId());
        assertTrue(stage0 instanceof Question);

        Question q = (Question) stage0;
        assertEquals("Start?", q.getText());
        List<Question.Answer> answers = q.answers;
        assertEquals(2, answers.size());
        assertEquals("Yes", answers.get(0).text);
        assertEquals("1", answers.get(0).next);
    }

    @Test
    void testStartStageIsStageZero() throws IOException {
        QuestRepository repo = new QuestRepository("test_quest.json");

        Stage start = repo.getStartStage();
        assertNotNull(start);
        assertEquals("0", start.getId());
    }

    @Test
    void testIgnoreNonNumericKeys() throws IOException {
        QuestRepository repo = new QuestRepository("test_quest.json");
        assertNull(repo.getStage("non_numeric"));
    }

    @Test
    void testStageResultTypeParsedCorrectly() throws IOException {
        QuestRepository repo = new QuestRepository("test_quest.json");

        Stage stage1 = repo.getStage("1");
        assertNotNull(stage1);
        assertTrue(stage1 instanceof Result);

        Result result = (Result) stage1;
        assertTrue(result.getResult());
        assertEquals("You chose yes.", result.getText());
    }

    @Test
    void testThrowsExceptionIfFileNotFound() {
        Exception exception = assertThrows(IOException.class, () -> {
            new QuestRepository("nonexistent.json");
        });

        assertTrue(exception.getMessage().contains("Файл не найден"));
    }
}
