package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UserUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        new UserServiceImpl().updateUser(new User(id, name, email));
        resp.sendRedirect("list");
    }
}
