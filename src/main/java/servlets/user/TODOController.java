package servlets.user;

import repositories.TODORepository;
import services.TODOService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/todo")
public class TODOController extends HttpServlet {
    private TODORepository todoRepository = new TODORepository();
    private TODOService todoService = new TODOService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getSession().getAttribute("login");
        req.setAttribute("todoList", todoService.getUsersToDoList(login));
        req.getRequestDispatcher("/user/todo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delete = request.getParameter("delete");
        String login = (String) request.getSession().getAttribute("login");
        if (delete != null) { // Is the delete button pressed?
            todoRepository.deleteTODOItemFromList(todoRepository.getTodos(login), (Long.valueOf(delete)), login, "delete");
        }

        String changeStatus = request.getParameter("changeStatus");
        if (changeStatus != null) {
            todoRepository.updateStatusTODOItemFromList(todoRepository.getTodos(login), (Long.valueOf(changeStatus)), login, "changeStatus");
        }
        String addItem = request.getParameter("additem");
        String idtodo = request.getParameter("idtodo");
        String comment = request.getParameter("comment");
        boolean isdone = Boolean.valueOf(request.getParameter("isdone"));
        if (addItem != null) {
            todoRepository.addTODOItemToList(todoRepository.getTodos(login), login, Long.valueOf(idtodo), comment, isdone, "additem");
        }

        response.sendRedirect(request.getContextPath() + "/user/todo"); // Refresh page
    }
}

