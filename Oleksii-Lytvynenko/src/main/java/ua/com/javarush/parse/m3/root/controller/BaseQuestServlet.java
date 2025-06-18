package ua.com.javarush.parse.m3.root.controller;

import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseQuestServlet extends HttpServlet {

    protected static final String QUEST_JSP_REQUEST_PATH = "/game.jsp";
    protected static final String QUEST_RESULTS_JSP_PATH = "/result.jsp";
    protected static final String QUEST_STARTING_POINT = "starting-point";
    protected static final String DECISION_ATTRIBUTE_NAME = "decision";
    protected static final String OPTION_CHOICE_PARAMETER_NAME = "choiceIndex";
    protected static final String CHOICE_CONTEXT_PARAMETER_NAME = "choiceContext";
    protected static final String TITLE_ATTRIBUTE_NAME = "title";
    protected static final String STORY_ATTRIBUTE_NAME = "story";
    protected static final String CURRENT_STEP = "currentStep";
    protected static final String ATTEMPTS = "attempts";
    protected static final String QUEST_CONTAINER_BACKGROUND_IMAGE_NAME = "questContainerBackground";
    protected static final String DEFAULT_QUEST_FILE = "WEB-INF/DungeonQuest.json";

    protected abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
