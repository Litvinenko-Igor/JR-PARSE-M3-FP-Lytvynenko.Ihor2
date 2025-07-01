package quest.controller;

import quest.util.GameLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (req.getParameter("playerName") != null) {
            session.setAttribute("player", req.getParameter("playerName"));
            Integer games = (Integer) session.getAttribute("games");
            session.setAttribute("games", (games == null ? 0 : games) + 1);
            session.setAttribute("logic", new GameLogic());
        }

        GameLogic logic = (GameLogic) session.getAttribute("logic");

        if (req.getParameter("restart") != null) {
            logic = new GameLogic();
            session.setAttribute("logic", logic);
            req.getRequestDispatcher("question.jsp").forward(req, resp);
            return;
        }

        String answer = req.getParameter("answer");
        if (logic != null && answer != null) logic.processAnswer(answer);

        if (logic.isVictory() || logic.isGameOver()) {
            req.setAttribute("result", logic.isVictory() ? "Перемога" : "Поразка");
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("question.jsp").forward(req, resp);
        }
    }
}
