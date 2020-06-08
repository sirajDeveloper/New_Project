package servlet;

import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        try{
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        UserServiceImpl.getUserService().deleteUser(id);
        resp.sendRedirect("list");
    }
}
