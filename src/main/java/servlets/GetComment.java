package servlets;

import resources.Hibernate.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentsHibernateShell commentsHibernateShell = new CommentsHibernateShell();
        LabsHibernateShell labsHibernateShell = new LabsHibernateShell();
        TestHibernateShell testHibernateShell = new TestHibernateShell();
        StudentHibernateShell studentHibernateShell = new StudentHibernateShell();

        String commentId = (String) req.getParameter("comment-id");
        String commentType = (String) req.getParameter("type");
        String secondCommentId = (String) req.getParameter("second-comment-id");
        String comment = "";

        try {
            switch (commentType) {
                case "m": // mark
                    comment = labsHibernateShell.getLabComment(commentId); //labMarkId
                    break;
                case "c": //class
                    comment = commentsHibernateShell.getComment(commentId, secondCommentId); //stidentId, classId
                    break;
                case "t" : //test
                    //TODO comment = testHibernateShell.getComment(commentId); // testMarkId
                    break;
                case "b" ://bonus
                    //TODO comment = studentHibernateShell.getComment(commentId); //studentId
                    break;
            }
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }

        resp.getWriter().append(comment);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
