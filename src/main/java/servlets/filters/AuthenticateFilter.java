package servlets.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();

        HttpSession session = httpRequest.getSession();

        if ( session.getAttribute("user") != null || uri.matches(".*\\..*")
                || uri.endsWith("DoLoginServlet")  || uri.endsWith("GetTableServlet")
                || uri.endsWith("/") || uri.endsWith("Welcome") || uri.endsWith("AccessDeniedServlet")) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("AccessDeniedServlet");
        }
    }


    @Override
    public void destroy() { }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }
}