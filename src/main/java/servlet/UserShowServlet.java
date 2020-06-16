package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class UserShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().contains("admin/new")) {
            resp.sendRedirect("/user-form.jsp");
        } else {
            List<User> listUser = UserServiceImpl.getUserService().getAllUsers();
            req.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
