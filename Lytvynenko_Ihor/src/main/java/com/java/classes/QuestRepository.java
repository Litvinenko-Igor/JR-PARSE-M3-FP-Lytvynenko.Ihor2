package com.java.classes;

import java.util.HashMap;
import java.util.Map;

public class QuestRepository {
    private final Map<Integer, QuestNode> questNodeMap = new HashMap<>();
    private static QuestRepository questRepository;
    private QuestRepository(){
        System.out.println("Перший розділ");
        QuestNode node0 = new QuestNode();
        node0.setId(0);
        node0.setQuestionText("Ви втрачаєте пам'ять");
        Map<Integer, String> map0 = new HashMap<>();
        map0.put(1, "Прийняти виклик");
        map0.put(2, "Відхилити виклик");
        node0.setChoices(map0);
        node0.setLosingNode(false);
        questNodeMap.put(node0.getId(), node0);
        System.out.println("------");

        System.out.println("Перший розділ з відхиленням");
        QuestNode node2 = new QuestNode();
        node2.setId(2);
        node2.setQuestionText("Ви відхилили виклик. Поразка");
        node2.setChoices(new HashMap<>());
        node2.setLosingNode(true);
        questNodeMap.put(node2.getId(), node2);
        System.out.println("------");

        System.out.println("Другий розділ");
        QuestNode node1 = new QuestNode();
        node1.setId(1);
        node1.setQuestionText("Ви прийняли виклик.Піднятися на капітанський місток?");
        Map<Integer, String> map1 = new HashMap<>();
        map1.put(3, "Піднятися на місток");
        map1.put(4, "Відмовитися підніматися на місток");
        node1.setChoices(map1);
        node1.setLosingNode(false);
        questNodeMap.put(node1.getId(), node1);
        System.out.println("------");

        System.out.println("Другий розділ з відхиленням");
        QuestNode node4 = new QuestNode();
        node4.setId(4);
        node4.setQuestionText("Ви не пішли на переговор. Поразка");
        node4.setChoices(new HashMap<>());
        node4.setLosingNode(true);
        questNodeMap.put(node4.getId(), node4);
        System.out.println("------");

        System.out.println("Третій розділ");
        QuestNode node3 = new QuestNode();
        node3.setId(3);
        node3.setQuestionText("Ви піднялися на місток. Хто ви?");
        Map<Integer, String> map3 = new HashMap<>();
        map3.put(5, "Розповісти правду про себе");
        map3.put(6, "Збрехати про себе");
        node3.setChoices(map3);
        node3.setLosingNode(false);
        questNodeMap.put(node3.getId(), node3);
        System.out.println("------");

        System.out.println("Третій розділ з виграшем");
        QuestNode node5 = new QuestNode();
        node5.setId(5);
        node5.setQuestionText("Вас повернули додому. Перемога");
        node5.setChoices(new HashMap<>());
        node5.setLosingNode(true);
        questNodeMap.put(node5.getId(), node5);
        System.out.println("------");


        System.out.println("Третій розділ з відхиленням");
        QuestNode node6 = new QuestNode();
        node6.setId(6);
        node6.setQuestionText("Ваша брехня була викрита. Поразка");
        node6.setChoices(new HashMap<>());
        node6.setLosingNode(true);
        questNodeMap.put(node6.getId(), node6);
        System.out.println("------");
    }

    public static QuestRepository getInstance(){
        if(questRepository == null){
            questRepository = new QuestRepository();
        }
        return questRepository;
    }
    public QuestNode getNode(int id) {
        return questNodeMap.get(id);
    }
}