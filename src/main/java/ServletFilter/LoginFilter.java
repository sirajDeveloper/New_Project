package ServletFilter;

import service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    HttpServletRequest request;

    private static final String[] securityURLs = {
            "/new", "/insert", "/update", "edit", "/delete"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (path.startsWith("/admin/")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/login";
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isLoginRequest = request.getRequestURI().equals(loginURI);
        boolean isLoginPage = request.getRequestURI().endsWith("login.jsp");
        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            request.getRequestDispatcher("/").forward(request, response);
        } else if (adminAccessURLs()) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean adminAccessURLs() {
        String requestURL = request.getRequestURI();
        for (String loginRequiredURL : securityURLs) {
            if (loginRequiredURL.contains(requestURL)) {
                return true;
            }
        }
        return false;
    }
}
