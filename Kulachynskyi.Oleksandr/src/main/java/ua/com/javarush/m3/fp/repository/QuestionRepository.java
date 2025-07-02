package ua.com.javarush.m3.fp.repository;

import ua.com.javarush.m3.fp.model.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class QuestionRepository {
    private static Map<String, Question> questions;

    static {
        questions = new HashMap<>();
        loadQuestionsFromJson();
    }

    private QuestionRepository() {

    }

    private static void loadQuestionsFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = QuestionRepository.class.getClassLoader().getResourceAsStream("questions.json")) {
            if (inputStream == null) {
                System.err.println("Помилка: файл questions.json не знайдено!");
                initializeDefaultQuestions();
                return;
            }
            @SuppressWarnings("unchecked")
            Map<String, Map<String, Object>> jsonData = mapper.readValue(inputStream, Map.class);
            jsonData.forEach((state, data) -> {
                String description = (String) data.get("description");
                boolean isGameOver = (boolean) data.get("isGameOver");
                String result = (String) data.get("result");
                @SuppressWarnings("unchecked")
                Map<String, String> choices = (Map<String, String>) data.get("choices");
                questions.put(state, new Question(description, isGameOver, result, choices));
            });
        } catch (IOException e) {
            System.err.println("Помилка при завантаженні питань з JSON: " + e.getMessage());
            initializeDefaultQuestions();
        }
    }

    private static void initializeDefaultQuestions() {
        Map<String, String> startChoices = new HashMap<>();
        startChoices.put("door", "Відчинити скрипучі головні двері");
        startChoices.put("backyard", "Обійти будинок, шукаючи інший шлях");
        questions.put("start", new Question(
                "Ти опинився перед старовинним будинком, оточеним густим туманом. Вітер гудить у віконницях, а шепоти лоскочуть вуха. Що робиш?",
                false, null, startChoices
        ));
    }

    public static QuestionRepository getInstance() {
        return new QuestionRepository();
    }

    public Question getQuestion(String state) {
        return questions.getOrDefault(state, questions.get("start"));
    }
}