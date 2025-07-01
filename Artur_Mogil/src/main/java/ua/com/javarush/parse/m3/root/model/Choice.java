package ua.com.javarush.parse.m3.root.model;

public class Choice {
    private int id;
    private String description;
    private int nextStepId;

    public Choice(int id, String description, int nextStepId) {
        this.id = id;
        this.description = description;
        this.nextStepId = nextStepId;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public int getNextStepId() { return nextStepId; }
}