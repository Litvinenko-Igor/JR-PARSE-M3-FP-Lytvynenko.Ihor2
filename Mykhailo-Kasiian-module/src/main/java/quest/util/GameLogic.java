package quest.util;

public class GameLogic {
    public enum Step {
        START, CALL_ACCEPTED, ON_BRIDGE, GAME_OVER, VICTORY
    }

    private Step step = Step.START;

    public Step getStep() {
        return step;
    }

    public void processAnswer(String answer) {
        switch (step) {
            case START -> step = "Прийняти виклик".equals(answer) ? Step.CALL_ACCEPTED : Step.GAME_OVER;
            case CALL_ACCEPTED -> step = "Піднятися на місток".equals(answer) ? Step.ON_BRIDGE : Step.GAME_OVER;
            case ON_BRIDGE -> step = "Розповісти правду про себе".equals(answer) ? Step.VICTORY : Step.GAME_OVER;
            default -> step = Step.GAME_OVER;
        }
    }


    public boolean isVictory() {
        return step == Step.VICTORY;
    }

    public boolean isGameOver() {
        return step == Step.GAME_OVER;
    }

    public String getQuestion() {
        return switch (step) {
            case START -> "Ви втрачаєте пам'ять. Прийняти виклик НЛО?";
            case CALL_ACCEPTED -> "Ви прийняли виклик. Піднятися на капітанський місток?";
            case ON_BRIDGE -> "Ви піднялися на місток. Хто ви?";
            default -> "Гра завершена.";
        };
    }

    public String[] getOptions() {
        return switch (step) {
            case START -> new String[]{"Прийняти виклик", "Відхилити виклик"};
            case CALL_ACCEPTED -> new String[]{"Піднятися на місток", "Відмовитися"};
            case ON_BRIDGE -> new String[]{"Розповісти правду про себе", "Збрехати про себе"};
            default -> new String[]{};
        };
    }
}