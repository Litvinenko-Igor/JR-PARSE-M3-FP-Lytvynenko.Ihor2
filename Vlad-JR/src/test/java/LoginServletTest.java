
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import root.controller.LoginServlet;
import root.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import static org.mockito.Mockito.*;

class LoginServletTest {

    private LoginServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @BeforeEach
    void setUp() {
        servlet = new LoginServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoGet_forwardsToLoginPage() throws Exception {
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_validCredentials_redirectsToQuests() throws Exception {
        when(request.getParameter("username")).thenReturn("testuser");
        when(request.getParameter("password")).thenReturn("testpass");

        servlet.doPost(request, response);

        verify(session).setAttribute(eq("user"), any(User.class));
        verify(session).setAttribute("user_wins", 0);
        verify(response).sendRedirect(contains("/quests"));
    }

    @Test
    void testDoPost_emptyUsername_showsError() throws Exception {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("somepass");
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Введіть логін і пароль.");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testDoPost_emptyPassword_showsError() throws Exception {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("");
        when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Введіть логін і пароль.");
        verify(dispatcher).forward(request, response);
    }
}
