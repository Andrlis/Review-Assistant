package servlets;

import resources.Hibernate.*;
import resources.TableMaker.JsonMaker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetComment")
public class GetComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        CommentsHibernateShell commentsHibernateShell = new CommentsHibernateShell();
        LabsHibernateShell labsHibernateShell = new LabsHibernateShell();
        TestHibernateShell testHibernateShell = new TestHibernateShell();
        StudentHibernateShell studentHibernateShell = new StudentHibernateShell();


        String commentEntityId = (String) req.getParameter("commentId");
        String commentType = (String) req.getParameter("type");
        String secondCommentId = (String) req.getParameter("secondCommentId");
        String comment = "";

        try {
            switch (commentType) {
                case "lab": // mark
                    comment = JsonMaker.getJsonLabMarkComment(labsHibernateShell.getLabMarkById(commentEntityId));
                    break;
                /*case "class": //class
                    comment = JsonMaker.getJsonClassComment(

                    );
                    break;
                case "test" : //test
                    comment = JsonMaker.getJsonTestMarkComment(

                    );
                    break;
                case "bonus" ://bonus
                    comment = JsonMaker.getJsonBonusMarkComment();
                    break;*/
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
