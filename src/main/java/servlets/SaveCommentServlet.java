package servlets;

import resources.Hibernate.CommentsHibernateShell;
import resources.Hibernate.HibernateShellQueryException;
import resources.Hibernate.LabsHibernateShell;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/SaveCommentServlet")
public class SaveCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentsHibernateShell commentsHibernateShell = new CommentsHibernateShell();
        LabsHibernateShell labsHibernateShell = new LabsHibernateShell();
        String commentType = (String) req.getParameter("type");
        String comment = (String) req.getParameter("comment");

        try {
            switch (commentType) {
                //mark
                case "m":
                    String idLabMark = (String) req.getParameter("id_lab_mark");
                    labsHibernateShell.updateLabComment(idLabMark, comment);
                    break;
                //class
                case "c":
                    String idStudent = (String) req.getParameter("id_student");
                    String idUniversityClass = (String) req.getParameter("id_university_class");
                    commentsHibernateShell.saveComment(idStudent, idUniversityClass, comment);
                    break;
            }
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
