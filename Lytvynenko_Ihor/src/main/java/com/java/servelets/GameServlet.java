package com.java.servelets;

import com.java.classes.GameSession;
import com.java.classes.QuestNode;
import com.java.classes.QuestRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    QuestRepository questRepository = QuestRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");
        String playerName = req.getParameter("firstName");
        if (playerName != null && !playerName.trim().isEmpty()) {
            Integer count = (Integer) session.getAttribute("count");
            if (count == null){
                count = 1;
            }else count++;

            gameSession = new GameSession(playerName, 0, count);
            session.setAttribute("gameSession", gameSession);
            session.setAttribute("count", count);
        }
        if (gameSession == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }
        int currentId = gameSession.getCurrentNodeId();
        QuestNode questNode = questRepository.getNode(currentId);
        if (questNode == null) {
            resp.getWriter().println("Помилка гри!");
            return;
        }

        if (questNode.isLosingNode()) {
            req.setAttribute("questNode", questNode);
            Integer count = (Integer) session.getAttribute("count");
            session.invalidate();
            HttpSession newSession = req.getSession(true);
            newSession.setAttribute("count", count);
        } else {
            req.setAttribute("questNode", questNode);
        }

        req.setAttribute("gameSession", gameSession);
        req.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String choiceIdStr = req.getParameter("choiceId");
        if (choiceIdStr != null && !choiceIdStr.isEmpty()) {
            try {
                int nextNodeId = Integer.parseInt(choiceIdStr);
                HttpSession session = req.getSession();
                GameSession gameSession = (GameSession) session.getAttribute("gameSession");
                if (gameSession != null) {
                    gameSession.setCurrentNodeId(nextNodeId);
                }
            } catch (NumberFormatException e) {
                System.err.println("Помилка: отримано нечисловий choiceId: " + choiceIdStr );
            }
        }
        resp.sendRedirect(req.getContextPath() + "/game");
    }
}
