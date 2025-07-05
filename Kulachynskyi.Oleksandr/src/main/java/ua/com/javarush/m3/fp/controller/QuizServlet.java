package ua.com.javarush.m3.fp.controller;

import ua.com.javarush.m3.fp.repository.QuestionRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class QuizServlet extends HttpServlet {
    protected QuestionRepository repository;

    @Override
    public void init() {
        repository = QuestionRepository.getInstance();
    }

    public QuestionRepository getRepository() {
        return repository;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String playerName = request.getParameter("playerName");

        if (playerName != null && !playerName.isEmpty()) {
            session.setAttribute("playerName", playerName);
            session.setAttribute("gamesPlayed", 0);
            session.setAttribute("wins", 0);
            session.setAttribute("currentState", "start");
            request.setAttribute("gameState", repository.getQuestion("start"));
        } else if ("restart".equals(action)) {
            session.setAttribute("currentState", "start");
            Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
            session.setAttribute("gamesPlayed", gamesPlayed != null ? gamesPlayed + 1 : 1);
            request.setAttribute("gameState", repository.getQuestion("start"));
        } else {
            String choice = request.getParameter("choice");
            if (choice != null) {
                String currentState = (String) session.getAttribute("currentState");
                String nextState = choice;

                if ("start".equals(currentState)) {
                    if ("door".equals(choice)) nextState = "hall";
                    if ("backyard".equals(choice)) nextState = "backyard";
                } else if ("backyard".equals(currentState)) {
                    if ("door".equals(choice)) nextState = "hall";
                    if ("basement".equals(choice)) nextState = "basementLose";
                } else if ("hall".equals(currentState)) {
                    if ("book".equals(choice)) nextState = "chase";
                    if ("stairs".equals(choice)) nextState = "stairs";
                    if ("freeze".equals(choice)) nextState = "freezeLose";
                } else if ("chase".equals(currentState)) {
                    if ("wardrobe".equals(choice)) nextState = "wardrobeLose";
                    if ("bed".equals(choice)) nextState = "ritual";
                    if ("door".equals(choice)) nextState = "doorLose";
                } else if ("stairs".equals(currentState)) {
                    if ("down".equals(choice)) nextState = "ritual";
                    if ("stay".equals(choice)) nextState = "stayLose";
                } else if ("ritual".equals(currentState)) {
                    if ("lux".equals(choice)) nextState = "win";
                    if ("tenebris".equals(choice)) nextState = "tenebrisLose";
                    if ("ignis".equals(choice)) nextState = "ignisLose";
                    if ("run".equals(choice)) nextState = "runLose";
                }

                session.setAttribute("currentState", nextState);
                request.setAttribute("gameState", repository.getQuestion(nextState));

                if ("win".equals(nextState)) {
                    Integer wins = (Integer) session.getAttribute("wins");
                    session.setAttribute("wins", wins != null ? wins + 1 : 1);
                }
            }
        }
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}