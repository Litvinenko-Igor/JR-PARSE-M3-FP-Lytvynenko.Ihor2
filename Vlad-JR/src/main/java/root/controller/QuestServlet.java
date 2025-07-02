package root.controller;

import root.model.Quest;
import root.model.Question;
import root.model.Result;
import root.model.Stage;
import root.questRepository.QuestRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestServlet", value = "/game")
public class QuestServlet extends HttpServlet {

    // Handles GET requests to /game (entry point to the quest)
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // If a quest is already in progress, resume it
        Quest quest = (Quest) request.getSession().getAttribute("quest");
        if (quest != null) {
            // Show the current question or result if available
            Question currentQuestion = (Question) request.getSession().getAttribute("question");
            Result currentResult = (Result) request.getSession().getAttribute("result");

            if (currentQuestion != null) {
                getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
                return;
            } else if (currentResult != null) {
                getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
                return;
            }

            // If no stage in progress, return to quest selection
            response.sendRedirect(request.getContextPath() + "/quests");
            return;
        }

        // If no quest in progress, initialize a new one
        String questPath = (String) request.getSession().getAttribute("currentQuestPath");

        // If user hasn't selected a quest, redirect to selection
        if (questPath == null) {
            response.sendRedirect(request.getContextPath() + "/quests");
            return;
        }

        // Create a new Quest object and store it in session
        quest = new Quest();
        request.getSession().setAttribute("quest", quest);

        // Load quest data from JSON using QuestRepository
        QuestRepository questRepository = new QuestRepository(questPath);
        request.getSession().setAttribute("questRepository", questRepository);

        // Get the first stage (usually a question)
        Stage stage = questRepository.getStartStage();

        // Save the stage in session and forward to the corresponding JSP
        if (stage instanceof Question question) {
            request.getSession().setAttribute("question", question);
            getServletContext().getRequestDispatcher("/question.jsp").forward(request, response);
        } else if (stage instanceof Result result) {
            // If quest starts with a result (edge case)
            request.getSession().setAttribute("result", result);
            request.getSession().setAttribute("quest", null);
            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }

    // Handles POST requests when the user selects an answer
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get current quest state and question
        QuestRepository questRepository = (QuestRepository) request.getSession().getAttribute("questRepository");
        Question currentQuestion = (Question) request.getSession().getAttribute("question");

        // Get the selected answer index from the form
        String selected = request.getParameter("answer");
        int index = Integer.parseInt(selected);

        // Find the ID of the next stage
        String nextStageID = currentQuestion.getAnswersNext(index);
        Stage stage = questRepository.getStage(nextStageID);

        // Clear previous stage from session
        request.getSession().removeAttribute("question");
        request.getSession().removeAttribute("result");

        if (stage instanceof Question question) {
            // Save next question and redirect to refresh state
            request.getSession().setAttribute("question", question);
            response.sendRedirect(request.getContextPath() + "/game");

        } else if (stage instanceof Result result) {
            // If user won, increase the win counter
            if (result.getResult()) {
                Integer user_wins = (Integer) request.getSession().getAttribute("user_wins");
                if (user_wins == null) user_wins = 0;
                user_wins++;
                request.getSession().setAttribute("user_wins", user_wins);
            }

            // Store result and end quest
            request.getSession().setAttribute("result", result);
            request.getSession().setAttribute("quest", null);

            // Show result page
            getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }
}
