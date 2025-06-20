package ua.com.javarush.parse.m3.root.controller;


import lombok.Getter;
import lombok.Setter;
import ua.com.javarush.parse.m3.root.model.Quest;
import ua.com.javarush.parse.m3.root.repository.QuestRepository;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "QuestServlet", urlPatterns = "/game")
public class QuestServlet extends BaseQuestServlet {

    @Setter
    @Getter
    private Quest quest;

    @Override
    public void init() {
        String filePath = getServletContext().getRealPath(DEFAULT_QUEST_FILE);
        var questRepository = new QuestRepository(filePath);
        quest = questRepository.getQuest();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String decisionKey = getDecisionKey(request, session);
        Quest.Decision decision = quest.getDecisions().get(decisionKey);
        String chosenTitle = null;
        String chosenStory = null;

        if (isChoiceSubmitted(request)) {
            var result = handleUserChoice(request, session, decisionKey);
            chosenTitle = result.title();
            chosenStory = result.story();
            decisionKey = result.next();
            decision = quest.getDecisions().get(decisionKey);
        }
        if (isFinalDecision(decisionKey)) {
            redirectToResultPage(request, response, session, decisionKey);
            return;
        }
        session.setAttribute(CURRENT_STEP, decisionKey);
        setRequestAttributes(request, decisionKey, decision, chosenTitle, chosenStory);
        request.getRequestDispatcher(QUEST_JSP_REQUEST_PATH).forward(request, response);
    }

    private String getDecisionKey(HttpServletRequest request, HttpSession session) {
        String decisionKey = request.getParameter("next");
        if (decisionKey == null || !quest.getDecisions().containsKey(decisionKey)) {
            decisionKey = (String) session.getAttribute(CURRENT_STEP);
        }
        if (decisionKey == null || !quest.getDecisions().containsKey(decisionKey)) {
            decisionKey = QUEST_STARTING_POINT;
        }
        return decisionKey;
    }

    private static boolean isFinalDecision(String decisionKey) {
        return decisionKey.equals("DEAD") || decisionKey.equals("WIN");
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

    private OptionResult handleUserChoice(HttpServletRequest request, HttpSession session, String currentKey) {
        try {
            int index = Integer.parseInt(request.getParameter(OPTION_CHOICE_PARAMETER_NAME));
            Quest.Decision prevDecision = quest.getDecisions().get((String) session.getAttribute(CURRENT_STEP));
            if (prevDecision != null && index >= 0 && index < prevDecision.getOptions().size()) {
                Quest.Option option = prevDecision.getOptions().get(index);
                return new OptionResult(option.getTitle(), option.getStory(), option.getNext());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new OptionResult(null, null, currentKey);
    }

    private boolean isChoiceSubmitted(HttpServletRequest request) {
        return request.getParameter(OPTION_CHOICE_PARAMETER_NAME) != null;
    }

    private static record OptionResult(String title, String story, String next){}
}
