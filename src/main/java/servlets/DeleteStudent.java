package servlets;

import resources.Hibernate.LabsHibernateShell;
import resources.Hibernate.StudentHibernateShell;
import resources.Hibernate.TestHibernateShell;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteStudent")
public class DeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentHibernateShell studentHibernateShell = new StudentHibernateShell();
        String studentId = (String) req.getParameter("studentId");
        try {
            studentHibernateShell.deleteStudent(studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
