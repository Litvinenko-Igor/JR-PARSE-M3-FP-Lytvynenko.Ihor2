package com.java.classes;

import java.util.Map;

public class QuestNode {
    private int id;
    private String questionText;
    private Map<Integer, String> choices;
    private boolean isLosingNode;

    public QuestNode() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Map<Integer, String> getChoices() {
        return choices;
    }

    public void setChoices(Map<Integer, String> choices) {
        this.choices = choices;
    }

    public boolean isLosingNode() {
        return isLosingNode;
    }

    public void setLosingNode(boolean losingNode) {
        isLosingNode = losingNode;
    }
}
