package filterServlet;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        boolean isAuth = session.getAttribute("userObject") != null;
        boolean isLogin = request.getRequestURI().equals("/login");
        User user = null;
        boolean isUserRole = false;
        boolean isAdminRole = false;
        if (isAuth) {
            user = (User) session.getAttribute("userObject");
            isUserRole = user.getRole().equals("user");
            isAdminRole = user.getRole().equals("admin");
        }

        if (isAdminRole) {
            response.sendRedirect("/admin");
        }
        else if (isUserRole) {
            response.sendRedirect("/user");
        }
        else {
        response.sendRedirect("/login");
        }
    }
}
