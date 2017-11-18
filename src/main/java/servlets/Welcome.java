package servlets;

import data.group.SubGroup;
import resources.HibernateShell;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Welcome
 */
@WebServlet("/Welcome")
public class Welcome extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubGroup sg = HibernateShell
                .getGroupKeeper()
                .getGroupList()
                .get(0)
                .getSubGroup("1");
        request.setAttribute("groups", HibernateShell.getGroupKeeper().getGroupList());
        request
                .getRequestDispatcher("WEB-INF/pages/Welcome.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
