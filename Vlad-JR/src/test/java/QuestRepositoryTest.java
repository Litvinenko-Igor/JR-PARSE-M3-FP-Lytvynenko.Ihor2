

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import root.model.Stage;
import root.questRepository.QuestRepository;


class QuestRepositoryTest {

    @Test
    void testLoadAndGetStage_notNull() throws IOException {
        QuestRepository repo = new QuestRepository("quest.json");
        Stage start = repo.getStartStage();
        assertNotNull(start, "Start stage should not be null");
    }

    @Test
    void testGetStageById_notNull() throws IOException {
        QuestRepository repo = new QuestRepository("quest.json");
        Stage stage = repo.getStage("s1");
        assertNotNull(stage, "Stage with ID 's1' should not be null if defined in quest.json");
    }
}