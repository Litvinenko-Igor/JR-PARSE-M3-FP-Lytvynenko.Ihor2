package quest.module;

import lombok.Data;
import quest.repository.QuestRepository;

@Data
public class GameState {
    private transient QuestScenario scenario;
    private String scenarioName;
    private int currentStepId;
    private int playerHp;

    public GameState(String scenarioName) {
        this.scenarioName = scenarioName;
        this.scenario = QuestRepository.getInstance().getScenario(scenarioName);
        this.currentStepId = scenario.getStartStepId();
        this.playerHp = scenario.getInitialPlayerHp();
    }

    public GameStep getCurrentStep() {
        if (scenario == null) {
            scenario = QuestRepository.getInstance().getScenario(scenarioName);
        }
        return scenario.getStep(currentStepId);
    }

    public void processAnswer(int optionIndex) {
        GameStep currentStep = getCurrentStep();
        if (currentStep.isTerminal() || optionIndex < 0 || optionIndex >= currentStep.getOptions().size()) {
            return;
        }

        GameOption selectedOption = currentStep.getOptions().get(optionIndex);
        this.playerHp += selectedOption.getHpChange();

        if (this.playerHp <= 0) {
            this.currentStepId = 999; // ID шага "Game Over"
        } else {
            this.currentStepId = selectedOption.getNextStepId();
        }
    }
}
