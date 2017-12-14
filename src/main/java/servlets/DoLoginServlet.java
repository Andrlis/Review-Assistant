package servlets;

import data.User;
import resources.Hibernate.HibernateShell;
import resources.MD5Hash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DoLoginServlet")
public class DoLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        User user = null;
        boolean hasError = false;
        String errorMessage = "";


        if (userName == null || password == null ||
                password.isEmpty() || userName.isEmpty()) {
            hasError = true;
            errorMessage = "Введите необходимые данные.";
        }
        user = HibernateShell.getUserByUserName(userName);
        if(user == null || !MD5Hash.getHash(password).equals(user.getPassword())){
            hasError = true;
            errorMessage = "Неверный логин или пароль";
        }

        if (hasError) {
            request.setAttribute("message", errorMessage);
            request.getRequestDispatcher("WEB-INF/pages/loginPage.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/Welcome").forward(request, response);
        }
    }
}
