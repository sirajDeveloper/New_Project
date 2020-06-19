package filterServlet;

import model.User;

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
        User user = null;
        HttpSession session = request.getSession();

        boolean hasAdmin = false;
        if (session.getAttribute("userObject") != null) {
            user = (User) session.getAttribute("userObject");
            hasAdmin = user.getRole().equals("admin");
        }


        if (hasAdmin) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/user");
        }
    }
}
