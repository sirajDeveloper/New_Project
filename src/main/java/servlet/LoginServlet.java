package servlet;

import model.User;
import service.UserServiceImpl;
import sun.security.krb5.EncryptionKey;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = UserServiceImpl.getUserService().getRoleUser(login, password);
        if (user != null) {
            if (user.getRole().equals("user")) {
                session.setAttribute("user", user.getRole());
                req.getRequestDispatcher("/user.jsp").forward(req, resp);
            }
            if (user.getRole().equals("admin")) {
                session.setAttribute("admin", user.getRole());
                req.getRequestDispatcher("admin/user-list.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
