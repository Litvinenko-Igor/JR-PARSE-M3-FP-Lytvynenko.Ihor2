package ua.com.javarush.m3.fp.model;

import java.util.Map;

public class Question {
    private String description;
    private boolean isGameOver;
    private String result;
    private Map<String, String> choices;

    public Question(String description, boolean isGameOver, String result, Map<String, String> choices) {
        this.description = description;
        this.isGameOver = isGameOver;
        this.result = result;
        this.choices = choices;
    }

    public String getDescription() {
        return description;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    // Явний геттер для EL
    public boolean getIsGameOver() {
        return isGameOver;
    }

    public String getResult() {
        return result;
    }

    public Map<String, String> getChoices() {
        return choices;
    }
}