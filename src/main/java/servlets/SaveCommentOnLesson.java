package servlets;

import resources.Hibernate.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveCommentOnLesson")
public class SaveCommentOnLesson extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentsHibernateShell commentsHibernateShell = new CommentsHibernateShell();
        String idStudent = (String) req.getParameter("id_student");
        String idUniversityClass = (String) req.getParameter("id_university_class");
        String comment = (String) req.getParameter("comment");
        try {
            commentsHibernateShell.saveComment(idStudent, idUniversityClass, comment);
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
