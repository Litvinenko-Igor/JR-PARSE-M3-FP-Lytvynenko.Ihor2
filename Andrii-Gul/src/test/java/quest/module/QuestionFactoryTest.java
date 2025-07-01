package quest.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import quest.repository.QuestionRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class QuestionFactoryTest {

    private List<Question> sampleQuestions;

    @BeforeEach
    void setUp() {
        sampleQuestions = List.of(
                new Question(1, "Столиця Франції?", List.of("Париж", "Рим"), null, null, Continent.Europe, "Франція"),
                new Question(2, "Найбільший порт Європи?", List.of("Роттердам", "Гамбург"), null, null, Continent.Europe, null),
                new Question(3, "Де знаходиться гора Фудзі?", List.of("Японія"), null, null, Continent.Asia, "Японія"),
                new Question(4, "Яка річка найдовша в Азії?", List.of("Янцзи"), null, null, Continent.Asia, null),
                new Question(5, "Яка тварина є символом Австралії?", List.of("Кенгуру"), null, null, Continent.Oceania, "Австралія")
        );
    }

    @Test
    void generateQuestions_shouldFilterByContinentOnly_whenCountryIsNull() {
        int expectedSize = 2;
        Continent continent = Continent.Europe;

        try (MockedStatic<QuestionRepository> mockedRepo = mockStatic(QuestionRepository.class)) {
            QuestionRepository repositoryMock = mock(QuestionRepository.class);
            mockedRepo.when(QuestionRepository::getInstance).thenReturn(repositoryMock);
            when(repositoryMock.getAllQuestions()).thenReturn(sampleQuestions);

            List<Question> result = QuestionFactory.generateQuestions(continent, null, 10);

            assertEquals(expectedSize, result.size());
            assertTrue(result.stream().allMatch(q -> q.getContinent() == continent));
        }
    }

    @Test
    void generateQuestions_shouldFilterByContinentAndCountry() {
        int expectedSize = 1;
        Continent continent = Continent.Europe;
        String country = "Франція";

        try (MockedStatic<QuestionRepository> mockedRepo = mockStatic(QuestionRepository.class)) {
            QuestionRepository repositoryMock = mock(QuestionRepository.class);
            mockedRepo.when(QuestionRepository::getInstance).thenReturn(repositoryMock);
            when(repositoryMock.getAllQuestions()).thenReturn(sampleQuestions);

            List<Question> result = QuestionFactory.generateQuestions(continent, country, 10);

            assertEquals(expectedSize, result.size());
            assertEquals(country, result.get(0).getCountry());
        }
    }

    @Test
    void generateQuestions_shouldReturnEmptyList_whenNoMatchingCountry() {
        Continent continent = Continent.Europe;
        String country = "Німеччина";

        try (MockedStatic<QuestionRepository> mockedRepo = mockStatic(QuestionRepository.class)) {
            QuestionRepository repositoryMock = mock(QuestionRepository.class);
            mockedRepo.when(QuestionRepository::getInstance).thenReturn(repositoryMock);
            when(repositoryMock.getAllQuestions()).thenReturn(sampleQuestions);

            List<Question> result = QuestionFactory.generateQuestions(continent, country, 10);

            assertTrue(result.isEmpty());
        }
    }

    @Test
    void generateQuestions_shouldRespectLimit() {
        int expectedSize = 1;
        Continent continent = Continent.Asia;

        try (MockedStatic<QuestionRepository> mockedRepo = mockStatic(QuestionRepository.class)) {
            QuestionRepository repositoryMock = mock(QuestionRepository.class);
            mockedRepo.when(QuestionRepository::getInstance).thenReturn(repositoryMock);
            when(repositoryMock.getAllQuestions()).thenReturn(sampleQuestions);

            List<Question> result = QuestionFactory.generateQuestions(continent, null, expectedSize);

            assertEquals(expectedSize, result.size());
        }
    }

    @Test
    void generateQuestions_shouldReturnAllAvailable_ifLimitIsHigher() {
        int expectedSize = 1;
        Continent continent = Continent.Oceania;

        try (MockedStatic<QuestionRepository> mockedRepo = mockStatic(QuestionRepository.class)) {
            QuestionRepository repositoryMock = mock(QuestionRepository.class);
            mockedRepo.when(QuestionRepository::getInstance).thenReturn(repositoryMock);
            when(repositoryMock.getAllQuestions()).thenReturn(sampleQuestions);

            List<Question> result = QuestionFactory.generateQuestions(continent, null, 5);

            assertEquals(expectedSize, result.size());
        }
    }
}
