package ua.com.javarush.parse.m3.root.controller;


import ua.com.javarush.parse.m3.root.model.Quest;
import ua.com.javarush.parse.m3.root.repository.QuestRepository;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "QuestServlet", urlPatterns = "/game")
public class QuestServlet extends BaseQuestServlet {

    private Quest quest;

    @Override
    public void init() {
        String filePath = getServletContext().getRealPath(DEFAULT_QUEST_FILE);
        var questRepository = new QuestRepository(filePath);
        quest = questRepository.getQuest();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String decisionKey = request.getParameter("next");
        if (decisionKey == null || !quest.getDecisions().containsKey(decisionKey)) {
            decisionKey = (String) session.getAttribute(CURRENT_STEP);
        }
        if (decisionKey == null || !quest.getDecisions().containsKey(decisionKey)) {
            decisionKey = QUEST_STARTING_POINT;
        }
        String chosenTitle = null;
        String chosenStory = null;
        String choiceIndexStr = request.getParameter(OPTION_CHOICE_PARAMETER_NAME);
        if (choiceIndexStr != null) {
            try {
                int choiceIndex = Integer.parseInt(choiceIndexStr);
                Quest.Decision prevDecision = quest.getDecisions().get((String) session.getAttribute(CURRENT_STEP));
                if (prevDecision != null && choiceIndex >= 0 && choiceIndex < prevDecision.getOptions().size()) {
                    Quest.Option chosenOption = prevDecision.getOptions().get(choiceIndex);
                    chosenTitle = chosenOption.getTitle();
                    chosenStory = chosenOption.getStory();
                    decisionKey = chosenOption.getNext();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (decisionKey.equals("DEAD") || decisionKey.equals("WIN")) {
            redirectToResultPage(request, response, session, decisionKey);
            return;
        }
        session.setAttribute(CURRENT_STEP, decisionKey);
        Map<String, Quest.Decision> decisions = quest.getDecisions();
        Quest.Decision decision = decisions.get(decisionKey);
        setRequestAttributes(request, decisionKey, decision, chosenTitle, chosenStory);
        request.getRequestDispatcher(QUEST_JSP_REQUEST_PATH).forward(request, response);
    }

    private static void redirectToResultPage(HttpServletRequest request, HttpServletResponse response, HttpSession session, String decisionKey) throws ServletException, IOException {
        Object attemptsObj = session.getAttribute(ATTEMPTS);
        int attempts = (attemptsObj instanceof Integer) ? (Integer) attemptsObj : 0;
        attempts++;
        session.setAttribute(ATTEMPTS, attempts);
        request.setAttribute(ATTEMPTS, attempts);

        session.removeAttribute(CURRENT_STEP);
        request.setAttribute("outcome", decisionKey.equals("DEAD") ? "dead" : "win");
        request.getRequestDispatcher(QUEST_RESULTS_JSP_PATH).forward(request, response);
    }

    private void setRequestAttributes(HttpServletRequest request, String decisionKey, Quest.Decision decision, String chosenTitle, String chosenStory) {
        request.setAttribute("title", quest.getTitle());
        request.setAttribute("story", quest.getStory());
        request.setAttribute("decisionKey", decisionKey);
        request.setAttribute("decision", decision);
        request.setAttribute("chosenTitle", chosenTitle);
        request.setAttribute("chosenStory", chosenStory);
    }
}
