package quest.module;

import lombok.Data;
import java.util.*;

@Data
public class Question {
    private int id;
    private String text;
    private List<String> options;
    private Set<String> correctAnswers;
    private String imageUrl;
    private Continent continent;
    private String country;

    public Question() {
        this.correctAnswers = new HashSet<>();
    }

    public Question(int id, String text, List<String> options, Set<String> correctAnswers, String imageUrl, Continent continent, String country) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.correctAnswers = correctAnswers;
        this.imageUrl = imageUrl;
        this.continent = continent;
        this.country = country;
    }

    public boolean isCorrect(String[] selected) {
        if (selected == null || selected.length == 0) {
            return this.correctAnswers == null || this.correctAnswers.isEmpty();
        }
        return new HashSet<>(Arrays.asList(selected)).equals(correctAnswers);
    }
}
