package servlets;

import data.Student;
import resources.Controllers.DefaultController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/SaveStudent")
public class SaveStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DefaultController defaultController = new DefaultController();

        String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        String studentName = (String) req.getParameter("surname");
        studentName += " " + (String) req.getParameter("name");
        String studentId = (String) req.getParameter("studentId");
        String gitRepo = (String) req.getParameter("git");
        String eMail = (String) req.getParameter("email");

        try {
            if (studentId.equals("")) {
                //studentHibernateShell.insertStudent(groupNumber, subGroupNumber, studentName, eMail, gitRepo);
            } else {
                //studentHibernateShell.updateStudent(studentId, studentName, eMail, gitRepo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
