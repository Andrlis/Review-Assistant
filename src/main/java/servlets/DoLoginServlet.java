package servlets;

import data.User;
import logics.UserLogic;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;
import resources.TableMaker.JsonMaker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DoLoginServlet")
public class  DoLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserLogic userLogic = new UserLogic();

        response.setCharacterEncoding("UTF-8");

        String userName = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        User user = null;
        boolean hasError = false;
        String errorMessage = "";
        String message = "/Welcome";
        int code = 0;


        if (userName == null || password == null ||
                password.isEmpty() || userName.isEmpty()) {
            hasError = true;
            message = "Введите необходимые данные.";
            code = 1;
        }

        try {
            user = userLogic.getByUserName(userName);
        } catch (DataBaseQueryException | DataBaseCriteriaCountException e) {
            e.printStackTrace();
        }

        if(!hasError && (user == null || !password.equals(user.getPassword())))//!MD5Hash.getHash(password).equals(user.getPassword())){
        {
            hasError = true;
            message = "Неверный логин или пароль";
            code = 2;
        }

        String result;

        result = JsonMaker.getAuthorisationResult(code, message);
        if (!hasError) {
            request.getSession().setAttribute("user", user);
        }
        response.getWriter().append(result);


        /*if (hasError) {
            //request.setAttribute("message", errorMessage);
            //request.getRequestDispatcher("WEB-INF/pages/loginPage.jsp").forward(request, response);

        } else {
            //request.getSession().setAttribute("user", user);
           // request.getRequestDispatcher("/Welcome").forward(request, response);
        }*/
    }

}
