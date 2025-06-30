package quest.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import quest.module.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Getter
public class QuestionRepository {

    private static volatile QuestionRepository instance;
    private final List<Question> allQuestions;

    private QuestionRepository() {
        this.allQuestions = loadQuestions();
    }

    public static QuestionRepository getInstance() {
        if (instance == null) {
            synchronized (QuestionRepository.class) {
                if (instance == null) {
                    instance = new QuestionRepository();
                }
            }
        }
        return instance;
    }

    private List<Question> loadQuestions() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Question>> typeReference = new TypeReference<List<Question>>() {};

        InputStream inputStream = TypeReference.class.getResourceAsStream("/questions.json");

        try {
            return mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            throw new IllegalStateException("Критична помилка: неможливо завантажити питання гри.", e);
        }
    }
}
