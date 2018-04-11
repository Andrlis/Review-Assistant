package servlets;

import data.Student;
import resources.Hibernate.HibernateShellQueryException;
import resources.Hibernate.StudentHibernateShell;

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
        StudentHibernateShell studentHibernateShell = new StudentHibernateShell();
        String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        String studentName = (String) req.getParameter("surname");
        studentName += " " + (String) req.getParameter("name");
        String studentId = (String) req.getParameter("studentId");
        String gitRepo = (String) req.getParameter("git");
        String eMail = (String) req.getParameter("email");


        try {
            if (studentId.equals("")) {
                studentHibernateShell.insertStudent(groupNumber, subGroupNumber, studentName, eMail, gitRepo);
            } else {
                try {
                    studentHibernateShell.updateStudent(studentId, studentName, eMail, gitRepo);
                } catch (HibernateShellQueryException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
