package ua.com.javarush.parse.m3.root.model;

import ua.com.javarush.parse.m3.root.repository.QuestData;

import java.util.Map;

public class QuestGame {

    private String playerName;
    private int currentStep;
    private boolean isGameOver;
    private boolean isWin;
    private final Map<Integer, Scenario> scenarios;

    public QuestGame(String playerName) {
        this.playerName = playerName;
        this.currentStep = 1;
        this.isGameOver = false;
        this.isWin = false;
        this.scenarios = QuestData.getScenarios();
    }

    public String getPlayerName() {
        return playerName;
    }

    public Scenario getCurrentScenario() {
        return scenarios.get(currentStep);
    }
    public int getCurrentStep() {
        return currentStep;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isWin() {
        return isWin;
    }

    public void makeChoice(int choiceId) {
        Scenario current = getCurrentScenario();
        if (current != null && !isGameOver) {
            Choice selectedChoice = null;
            if (current.getChoice1() != null && current.getChoice1().getId() == choiceId) {
                selectedChoice = current.getChoice1();
            } else if (current.getChoice2() != null && current.getChoice2().getId() == choiceId) {
                selectedChoice = current.getChoice2();
            }

            if (selectedChoice != null) {
                this.currentStep = selectedChoice.getNextStepId();
                Scenario newScenario = scenarios.get(currentStep);
                if (newScenario != null) {
                    this.isGameOver = newScenario.isGameOver();
                    this.isWin = newScenario.isWin();
                } else {
                    this.isGameOver = true;
                    this.isWin = false;
                    System.err.println("Error: Scenario with ID " + currentStep + " not found!");
                }
            } else {
                System.err.println("Invalid choice ID " + choiceId + " for current scenario " + current.getText());
            }
        }
    }
}