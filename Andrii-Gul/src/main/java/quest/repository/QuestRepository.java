package quest.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import quest.module.QuestScenario;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuestRepository {
    private static volatile QuestRepository instance;
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<String, QuestScenario> scenarios = new ConcurrentHashMap<>();

    private QuestRepository() {}

    public static QuestRepository getInstance() {
        if (instance == null) {
            synchronized (QuestRepository.class) {
                if (instance == null) {
                    instance = new QuestRepository();
                }
            }
        }
        return instance;
    }

    public QuestScenario getScenario(String scenarioName) {
        return scenarios.computeIfAbsent(scenarioName, this::loadScenario);
    }

    private QuestScenario loadScenario(String name) {
        try (InputStream stream = getClass().getResourceAsStream("/" + name + ".json")) {
            if (stream == null) {
                throw new IOException("Сторінка не знайдена: " + name);
            }
            QuestScenario scenario = mapper.readValue(stream, QuestScenario.class);
            scenario.indexSteps();
            return scenario;
        } catch (IOException e) {
            throw new RuntimeException("Помилка завантаження сценарія: " + name, e);
        }
    }
}
