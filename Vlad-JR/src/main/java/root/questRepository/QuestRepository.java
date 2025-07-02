package root.questRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import root.model.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class QuestRepository {

    private final Map<String, Stage> stages;

    public QuestRepository(String resourcePath) throws IOException {
        this.stages = loadStagesFromJson(resourcePath);
    }

    private Map<String, Stage> loadStagesFromJson(String resourcePath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("File not found in resources: " + resourcePath);
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(inputStream);

        Map<String, Stage> result = new HashMap<>();

        for (Map.Entry<String, JsonNode> entry : rootNode.properties()) {
            String key = entry.getKey();
            if (isStageId(key)) {
                Stage stage = mapper.treeToValue(entry.getValue(), Stage.class);
                stage.setId(key);
                result.put(key, stage);
            }
        }

        return result;
    }

    private boolean isStageId(String key) {
        return key.matches("\\d+");
    }

    public Stage getStage(String id) {
        return stages.get(id);
    }

    public Stage getStartStage() {
        return getStage("0");
    }
}
