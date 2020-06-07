package servlet;

import com.google.gson.Gson;
import dao.UserDAO;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();

        if (servletPath.contains("/new")) {

            RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
            dispatcher.forward(req, resp);

        } else if (req.getServletPath().contains("/insert")) {

            String name = req.getParameter("name");
            String email = req.getParameter("email");
            new UserDAO().addUserDAO(new User(name, email));
            resp.sendRedirect("list");

        } else if (servletPath.contains("/update")) {

            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            new UserService().updateUser(new User(id, name, email));
            resp.sendRedirect("list");

        } else if (servletPath.contains("/edit")) {

            int id = Integer.parseInt(req.getParameter("id"));
            User existUser = UserService.getUserService().getUserById(id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
            req.setAttribute("user", existUser);
            dispatcher.forward(req, resp);

        } else if (servletPath.contains("/delete")) {

            int id = Integer.parseInt(req.getParameter("id"));
            new UserDAO().deleteUserDAO(id);
            resp.sendRedirect("list");

        } else {

            List<User> listUser = UserService.getUserService().getAllUsers();
            req.setAttribute("listUser", listUser);
            RequestDispatcher dispatcher = req.getRequestDispatcher("user-list.jsp");
            dispatcher.forward(req, resp);

        }
    }
}
