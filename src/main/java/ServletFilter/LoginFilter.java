package ServletFilter;

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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        boolean isAuth = session.getAttribute("userRole") != null && session.getAttribute("userId") != null;
        boolean isLogin = request.getRequestURI().equals("/login");

        if (isAuth || isLogin) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}
