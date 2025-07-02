package root.model;

import java.util.List;

public class Question extends Stage {
    public String text;
    public List<Answer> answers;

    public static class Answer {
        public String text;
        public String next;
    }

    public String getAnswerText(int id) {
        return answers.get(id).text;
    }

    public String getAnswersNext(int id) {
        return answers.get(id).next;
    }

    public String getText() {
        return text;
    }
}