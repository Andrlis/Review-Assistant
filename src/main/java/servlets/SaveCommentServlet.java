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
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentsHibernateShell commentsHibernateShell = new CommentsHibernateShell();
        LabsHibernateShell labsHibernateShell = new LabsHibernateShell();
        TestHibernateShell testHibernateShell = new TestHibernateShell();
        StudentHibernateShell studentHibernateShell = new StudentHibernateShell();

        String commentType = (String) req.getParameter("type");
        String comment = (String) req.getParameter("comment");
        String commentEntityId = (String) req.getParameter("commentId");

        try {
            switch (commentType) {
                //mark
                case "lab":
                    labsHibernateShell.updateLabComment(commentEntityId, comment);
                    break;
                //class
                case "class":
                    String idUniversityClass = (String) req.getParameter("secondCommentId"); //universityClassId
                    commentsHibernateShell.saveComment(commentEntityId, idUniversityClass, comment);
                    break;
                case "test" : //test
                    //String idTestMark = (String) req.getParameter("comment-id"); //testMarkId
                    //TODO testHibernateShell.saveComment(idTestMark, comment);
                    break;
                case "bonus" ://bonus

                    //TODO studentHibernateShell.saveComment(idBonus, comment);
                    break;
            }
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }
    }
}
