package servlets;

import data.User;
import logics.GroupLogic;

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
        GroupLogic groupLogic = new GroupLogic();

        try {
            request.setAttribute("groups", groupLogic.getAll());
            User user = (User)request.getSession().getAttribute("user");

            String redirection;
            if (user != null && user.isAdmin())
                redirection = "WEB-INF/pages/adminPage.jsp";
            else
                redirection = "WEB-INF/pages/index.jsp";

            request
                    .getRequestDispatcher(redirection)
                    .forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
