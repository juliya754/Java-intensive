package servlets;

import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class loginController extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean success = loginService.doLogin(login, password);
        if (success)
        {
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("logged", true);
            resp.sendRedirect( req.getContextPath() + "/user/todo"); //без слеша надо было. ты тут? да, спасибо!
        }
        else
        {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}

