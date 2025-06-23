package root.questRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import root.model.Stage;
import java.io.InputStream;
import java.io.IOException;
import java.util.Map;

public class QuestRepository {
    private final Map<String, Stage> stages;

    public QuestRepository(String resourcePath) throws IOException {
        // Initializing quest repository

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Файл не найден в resources: " + resourcePath);
            }

            this.stages = mapper.readValue(inputStream, new TypeReference<Map<String, Stage>>() {});
        }
    }

    public Stage getStage(String id) {
        return stages.get(id);
    }

    public Stage getStartStage() {
        return getStage("s0");
    }
}