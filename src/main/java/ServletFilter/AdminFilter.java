package ServletFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("admin") != null);

        String loginURI =  request.getContextPath() + "/admin/login";

        boolean isLoginRequest = request.getRequestURI().equals(loginURI);

        boolean isLoginPage = request.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            request.getRequestDispatcher("/admin/").forward(request, response);
        } else if (isLoggedIn || isLoginRequest) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
