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
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Quest initialization
        Quest quest = new Quest();
        request.getSession().setAttribute("quest", quest);

        // Quest repository initialization
        QuestRepository questRepository = createRepository("quest.json");
        request.getSession().setAttribute("questRepository", questRepository);

        // Getting first quest stage
        Stage stage = questRepository.getStartStage();

        // Processing stage
        if (stage instanceof Question question) {
            request.getSession().setAttribute("question", question);

            getServletContext()
                    .getRequestDispatcher("/question.jsp")
                    .forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Getting quest repository
        QuestRepository questRepository = (QuestRepository) request.getSession().getAttribute("questRepository");

        // Getting answer index
        Question curentQuestion = (Question) request.getSession().getAttribute("question");
        String selected = request.getParameter("answer");
        int index = Integer.parseInt(selected);

        // Getting next stage
        String nextStageID = curentQuestion.getAnswersNext(index);
        Stage stage = questRepository.getStage(nextStageID);

        // Processing stage
        if (stage instanceof Question question) {
            request.getSession().setAttribute("question", question);

            getServletContext()
                    .getRequestDispatcher("/question.jsp")
                    .forward(request, response);

        } else if (stage instanceof Result result) {
            request.getSession().setAttribute("result", result);

            getServletContext()
                    .getRequestDispatcher("/result.jsp")
                    .forward(request, response);
        }
    }

    public QuestRepository createRepository(String path) throws IOException {
        return new QuestRepository(path);
    }
}
