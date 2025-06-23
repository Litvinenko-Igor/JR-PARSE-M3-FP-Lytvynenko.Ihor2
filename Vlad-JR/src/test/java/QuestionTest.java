
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import root.model.Question;


@ExtendWith(MockitoExtension.class)
class QuestionTest {
    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.text = "Some text";
        Question.Answer a = new Question.Answer();
        a.text = "First";
        a.next = "next1";
        question.answers = List.of(a);
    }

    @Test
    void testAnswerMethods() {
        assertEquals("First", question.getAnswerText(0));
        assertEquals("next1", question.getAnswersNext(0));
    }
}
