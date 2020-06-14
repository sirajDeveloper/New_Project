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
        boolean loggedIn = session != null && session.getAttribute("userRole") != null;
        if (loggedIn) {
            String userRole = session.getAttribute("userRole").toString();
            if (userRole.equals("user")) {
                response.sendRedirect(request.getContextPath() + "/user.jsp");
            } else if (userRole.equals("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin/user-list.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/user.jsp");
        }
    }
}
