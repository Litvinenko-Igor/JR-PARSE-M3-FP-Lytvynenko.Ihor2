package quest.controller;

import quest.model.PlayerStats;
import quest.util.GameLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();

        // Отримуємо статистику гравця
        PlayerStats stats = (PlayerStats) session.getAttribute("playerStats");
        
        // Перевіряємо чи є нове ім'я гравця
        String playerName = req.getParameter("playerName");
        if (playerName != null && !playerName.trim().isEmpty()) {
            stats = new PlayerStats(playerName);
            session.setAttribute("playerStats", stats);
            
            // Створюємо нову гру для нового гравця
            GameLogic logic = new GameLogic();
            session.setAttribute("logic", logic);
            
            setAttributesAndForward(req, resp, logic, "question.jsp");
            return;
        }

        // Перевіряємо чи є статистика
        if (stats == null) {
            // Якщо немає статистики, перенаправляємо на початкову сторінку
            resp.sendRedirect("index.jsp");
            return;
        }

        // Отримуємо або створюємо нову логіку гри
        GameLogic logic = (GameLogic) session.getAttribute("logic");
        if (logic == null) {
            logic = new GameLogic();
            session.setAttribute("logic", logic);
        }

        // Перевіряємо чи це рестарт гри
        if (req.getParameter("restart") != null) {
            logic = new GameLogic();
            session.setAttribute("logic", logic);
            setAttributesAndForward(req, resp, logic, "question.jsp");
            return;
        }

        // Обробляємо відповідь
        String answer = req.getParameter("answer");
        if (answer != null) {
            logic.processAnswer(answer);
        }

        // Встановлюємо атрибути для JSP
        req.setAttribute("question", logic.getQuestion());
        req.setAttribute("options", logic.getOptions());

        if (logic.isVictory() || logic.isGameOver()) {
            // Оновлюємо статистику
            stats.addGameResult(logic.isVictory());
            session.setAttribute("playerStats", stats); // Зберігаємо оновлену статистику

            req.setAttribute("question", logic.getQuestion());
            req.setAttribute("options", logic.getOptions());
            req.setAttribute("result", logic.isVictory() ? "Перемога" : "Поразка");
            req.getRequestDispatcher("result.jsp").forward(req, resp);
        } else {
            setAttributesAndForward(req, resp, logic, "question.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        HttpSession session = req.getSession();
        PlayerStats stats = (PlayerStats) session.getAttribute("playerStats");
        
        if (stats != null) {
            // Якщо гравець вже є, створюємо нову гру
            GameLogic logic = new GameLogic();
            session.setAttribute("logic", logic);
            setAttributesAndForward(req, resp, logic, "question.jsp");
        } else {
            // Якщо гравця немає, перенаправляємо на сторінку входу
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    private void setAttributesAndForward(HttpServletRequest req,
                                         HttpServletResponse resp,
                                         GameLogic logic,
                                         String jspPage)
            throws ServletException, IOException {
        req.setAttribute("question", logic.getQuestion());
        req.setAttribute("options", logic.getOptions());
        req.getRequestDispatcher(jspPage).forward(req, resp);
    }

}