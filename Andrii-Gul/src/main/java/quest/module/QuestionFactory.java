package quest.module;

import quest.repository.QuestionRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFactory {

    public static List<Question> generateQuestions(Continent continent, String country, int numberOfQuestions) {
        List<Question> allQuestions = QuestionRepository.getInstance().getAllQuestions();

        List<Question> filteredQuestions = allQuestions.stream()
                .filter(q -> q.getContinent() == continent)
                .filter(q -> (country == null || country.trim().isEmpty()) ||
                        country.equalsIgnoreCase(q.getCountry()))
                .collect(Collectors.toList());

        Collections.shuffle(filteredQuestions);

        return filteredQuestions.stream()
                .limit(numberOfQuestions)
                .collect(Collectors.toList());
    }
}
