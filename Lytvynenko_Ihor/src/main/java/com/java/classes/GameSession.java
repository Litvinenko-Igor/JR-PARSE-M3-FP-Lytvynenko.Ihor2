package com.java.classes;

public class GameSession {
    String name;
    Integer currentNodeId;
    int count;

    public GameSession(String name, Integer currentNodeId, int count) {
        this.name = name;
        this.currentNodeId = currentNodeId;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCurrentNodeId() {
        return currentNodeId;
    }

    public void setCurrentNodeId(Integer currentNodeId) {
        this.currentNodeId = currentNodeId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
