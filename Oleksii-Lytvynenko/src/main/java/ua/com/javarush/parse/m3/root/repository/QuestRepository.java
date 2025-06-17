package ua.com.javarush.parse.m3.root.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import ua.com.javarush.parse.m3.root.model.Quest;

import java.io.File;

@Getter
public class QuestRepository {

    private final Quest quest;


    public QuestRepository(String pathToQuest) {
        this.quest = loadQuestData(pathToQuest);
    }

    private Quest loadQuestData(String path) {
        ObjectMapper mapper = new ObjectMapper();
        Quest mappedQuest = null;
        try {
            mappedQuest = mapper.readValue(new File(path), Quest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mappedQuest;
    }

}
