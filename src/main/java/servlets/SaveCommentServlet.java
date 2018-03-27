package servlets;

import resources.Hibernate.*;

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
        TestHibernateShell testHibernateShell = new TestHibernateShell();
        StudentHibernateShell studentHibernateShell = new StudentHibernateShell();

        String commentType = (String) req.getParameter("type");
        String comment = (String) req.getParameter("comment");

        try {
            switch (commentType) {
                //mark
                case "m":
                    String idLabMark = (String) req.getParameter("comment-id"); //labMarkId
                    labsHibernateShell.updateLabComment(idLabMark, comment);
                    break;
                //class
                case "c":
                    String idStudent = (String) req.getParameter("comment-id"); //studentId
                    String idUniversityClass = (String) req.getParameter("second-comment-id"); //universityClassId
                    commentsHibernateShell.saveComment(idStudent, idUniversityClass, comment);
                    break;
                case "t" : //test
                    String idTestMark = (String) req.getParameter("comment-id"); //testMarkId
                    //TODO testHibernateShell.saveComment(idTestMark, comment);
                    break;
                case "b" ://bonus
                    String idBonus = (String) req.getParameter("comment-id"); //studentId
                    //TODO studentHibernateShell.saveComment(idBonus, comment);
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
