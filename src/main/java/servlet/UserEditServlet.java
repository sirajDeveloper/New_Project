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

@WebServlet("/edit")
public class UserEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        User existUser = UserServiceImpl.getUserService().getUserById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("user-form.jsp");
        req.setAttribute("user", existUser);
        dispatcher.forward(req, resp);
    }
}
