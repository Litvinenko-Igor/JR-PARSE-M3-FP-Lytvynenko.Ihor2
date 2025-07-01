package ua.com.javarush.parse.m3.root.controller;

import ua.com.javarush.parse.m3.root.model.QuestGame;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "QuestServlet", urlPatterns = {"/start", "/game"})
public class QuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession session = request.getSession(true);

        if ("/start".equals(path)) {
            Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
            if (gamesPlayed == null) {
                gamesPlayed = 0;
            }
            request.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").forward(request, response);

        } else if ("/game".equals(path)) {
            QuestGame game = (QuestGame) session.getAttribute("questGame");
            if (game == null) {
                response.sendRedirect(request.getContextPath() + "/start");
                return;
            }

            request.setAttribute("currentScenario", game.getCurrentScenario());
            request.setAttribute("isGameOver", game.isGameOver());
            request.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession session = request.getSession();

        if ("/start".equals(path)) {
            String playerName = request.getParameter("playerName");
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Мандрівник";
            }
            session.setAttribute("playerName", playerName);

            Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
            if (gamesPlayed == null) {
                gamesPlayed = 0;
            }
            gamesPlayed++;
            session.setAttribute("gamesPlayed", gamesPlayed);

            QuestGame game = new QuestGame(playerName);
            session.setAttribute("questGame", game);

            response.sendRedirect(request.getContextPath() + "/game");
        } else if ("/game".equals(path)) {
            QuestGame game = (QuestGame) session.getAttribute("questGame");
            if (game == null || game.isGameOver()) {
                response.sendRedirect(request.getContextPath() + "/start");
                return;
            }

            String choiceParam = request.getParameter("choice");
            if (choiceParam != null) {
                try {
                    int choiceId = Integer.parseInt(choiceParam);
                    game.makeChoice(choiceId);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid choice parameter: " + choiceParam);
                }
            }
            response.sendRedirect(request.getContextPath() + "/game");
        }
    }
}