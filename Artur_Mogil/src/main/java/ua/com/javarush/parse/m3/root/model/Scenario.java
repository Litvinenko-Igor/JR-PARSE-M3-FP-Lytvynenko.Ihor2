package ua.com.javarush.parse.m3.root.model;

public class Scenario {
    private String text;
    private Choice choice1;
    private Choice choice2;
    private boolean gameOver;
    private boolean win;

    public Scenario(String text, Choice choice1, Choice choice2) {
        this(text, choice1, choice2, false, false);
    }

    public Scenario(String text, Choice choice1, Choice choice2, boolean gameOver, boolean win) {
        this.text = text;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.gameOver = gameOver;
        this.win = win;
    }
    public String getText() { return text; }
    public Choice getChoice1() { return choice1; }
    public Choice getChoice2() { return choice2; }
    public boolean isGameOver() { return gameOver; }
    public boolean isWin() { return win; }
}