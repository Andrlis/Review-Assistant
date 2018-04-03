package servlets;

import data.Student;
import data.mark.LabMark;
import data.сomment.Comment;
import resources.Controllers.CommentController;
import resources.Controllers.DefaultController;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;

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
        DefaultController defaultController = new DefaultController();
        CommentController commentController = new CommentController();

        String commentType = (String) req.getParameter("type");
        String comment = (String) req.getParameter("comment");
        Integer commentEntityId = Integer.parseInt((String) req.getParameter("commentId"));
        String secondCommentIdParam = ((String) req.getParameter("secondCommentId"));

        Integer secondCommentId = secondCommentIdParam.equals("") ? -1 : Integer.parseInt(secondCommentIdParam);

        try {
            switch (commentType) {
                //mark
                case "lab":
                    LabMark labMark = (LabMark) defaultController.getById(LabMark.class, commentEntityId);
                    labMark.setComment(comment);
                    defaultController.updateInDataBase(labMark);
                    break;
                //class
                case "class":
                    Comment classComment = commentController.get(commentEntityId, secondCommentId);
                    classComment.setComment(comment);
                    defaultController.updateInDataBase(classComment);
                    break;
                case "test" : //test
                    break;
                case "bonus" ://bonus
                    break;
            }
        } catch (DataBaseQueryException e) {
            e.printStackTrace();
        } catch (DataBaseCriteriaCountException e) {
            e.printStackTrace();
        }
    }
}
