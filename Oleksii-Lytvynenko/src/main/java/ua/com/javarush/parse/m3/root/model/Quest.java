package ua.com.javarush.parse.m3.root.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest{
    private String title;
    private String story;
    private Map<String, Decision> decisions;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Decision {
        private String prompt;
        private List<Option> options;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Option{
        private String title;
        private String story;
        private String next;
    }


}
