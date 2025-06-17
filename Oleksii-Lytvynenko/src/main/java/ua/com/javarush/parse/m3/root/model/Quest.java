package ua.com.javarush.parse.m3.root.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Quest{
    private String title;
    private String story;
    private Map<String, Decision> decisions;

    @Data
    public static class Decision {
        private String prompt;
        private List<Option> options;
    }

    @Data
    public static class Option{
        private String title;
        private String story;
        private String next;
    }
}
