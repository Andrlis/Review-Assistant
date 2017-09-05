package servlets;

import Data.User;
import Resources.HibernateShell;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class DoLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        User user = null;
        boolean hasError = false;
        String errorMessage= null;

            user = HibernateShell.getUserByUserName(userName);

        if(user == null || !password.equals(user.getPassword())){
            hasError = true;
            errorMessage = "Неверный логин или пароль";
        }


        if(hasError) {
            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/JSP/loginPage.jsp");
            dispatcher.forward(request, response);
        }
        else{
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
