package filterServlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        boolean isLogin = req.getRequestURI().equals("/login");
        boolean isAuth = session.getAttribute("userObject") != null;
        boolean isAdminRole = false;
        boolean isUserRole = false;
        User user = null;
        if (!isAuth) {
            String login = req.getParameter("email");
            String password = req.getParameter("password");
            user = UserServiceImpl.getUserService().getRoleUser(login, password);
            session.setAttribute("userObject", user);
        }
        if (isAuth) {
            user = (User) session.getAttribute("userObject");
            isAdminRole = user.getRole().equals("admin");
            isUserRole = user.getRole().equals("user");
            filterChain.doFilter(req, resp);
        }
        if (user != null) {
            if (user.getRole().equals("user")) {
                resp.sendRedirect("/user");
            }
            if (user.getRole().equals("admin")) {
                resp.sendRedirect("/admin");
            }
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
