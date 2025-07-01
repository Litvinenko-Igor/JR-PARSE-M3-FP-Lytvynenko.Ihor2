package quest.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private Question singleAnswerQuestion;
    private Question multipleAnswerQuestion;

    @BeforeEach
    void setUp() {
        singleAnswerQuestion = new Question(
                1, "Столиця Франції?", List.of("Париж", "Лондон"),
                Set.of("Париж"), null, Continent.Europe, "Франція"
        );

        multipleAnswerQuestion = new Question(
                2, "Які річки в Африці?", List.of("Ніл", "Амазонка", "Конго"),
                Set.of("Ніл", "Конго"), null, Continent.Africa, null
        );
    }

    @Test
    void isCorrect_shouldReturnTrueForCorrectSingleAnswer() {
        assertTrue(singleAnswerQuestion.isCorrect(new String[]{"Париж"}));
    }

    @Test
    void isCorrect_shouldReturnFalseForIncorrectSingleAnswer() {
        assertFalse(singleAnswerQuestion.isCorrect(new String[]{"Лондон"}));
    }

    @Test
    void isCorrect_shouldReturnTrueForCorrectMultipleAnswers() {
        assertTrue(multipleAnswerQuestion.isCorrect(new String[]{"Ніл", "Конго"}));
        assertTrue(multipleAnswerQuestion.isCorrect(new String[]{"Конго", "Ніл"}));
    }

    @Test
    void isCorrect_shouldReturnFalseForPartiallyCorrectMultipleAnswers() {
        assertFalse(multipleAnswerQuestion.isCorrect(new String[]{"Ніл"}));
    }

    @Test
    void isCorrect_shouldReturnFalseForIncorrectMultipleAnswers() {
        assertFalse(multipleAnswerQuestion.isCorrect(new String[]{"Амазонка"}));
    }

    @Test
    void isCorrect_shouldReturnFalseForNullOrEmptyInput() {
        assertFalse(singleAnswerQuestion.isCorrect(null));
        assertFalse(singleAnswerQuestion.isCorrect(new String[]{}));
    }
}
