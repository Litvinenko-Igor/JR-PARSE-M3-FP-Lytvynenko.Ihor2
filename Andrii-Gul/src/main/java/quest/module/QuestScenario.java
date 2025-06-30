package quest.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestScenario {
    private int startStepId;
    private int initialPlayerHp;
    private List<GameStep> steps;
    private transient Map<Integer, GameStep> stepMap;

    public void indexSteps() {
        if (steps != null) {
            this.stepMap = steps.stream().collect(Collectors.toMap(GameStep::getId, Function.identity()));
        }
    }

    public GameStep getStep(int id) {
        return stepMap.get(id);
    }
}
