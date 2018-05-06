package servlets;

import data.Student;
import data.mark.LabMark;
import data.mark.TestMark;
import data.сomment.Comment;
import logics.CommentLogic;
import dao.DataBaseCore;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;

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
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();
        CommentLogic commentLogic = new CommentLogic();

        String commentType = (String) req.getParameter("type");
        String comment = (String) req.getParameter("comment");
        Integer commentEntityId = Integer.parseInt((String) req.getParameter("commentId"));
        String secondCommentIdParam = ((String) req.getParameter("secondCommentId"));

        Integer secondCommentId = secondCommentIdParam.equals("") ? -1 : Integer.parseInt(secondCommentIdParam);

        try {
            switch (commentType) {
                //mark
                case "lab":
                    LabMark labMark = (LabMark) dataBaseCore.getById(LabMark.class, commentEntityId);
                    labMark.setComment(comment);
                    dataBaseCore.update(labMark);
                    break;
                //class
                case "class":
                    Comment classComment = commentLogic.get(commentEntityId, secondCommentId);
                    classComment.setComment(comment);
                    dataBaseCore.update(classComment);
                    break;
                case "test" : //test
                    TestMark testMark = (TestMark) dataBaseCore.getById(TestMark.class, commentEntityId);
                    testMark.setComment(comment);
                    dataBaseCore.update(testMark);
                case "bonus" ://bonus
                    Student student = (Student) dataBaseCore.getById(Student.class, commentEntityId);
                    student.setComment(comment);
                    dataBaseCore.update(student);
                    break;
            }
        } catch (DataBaseQueryException | DataBaseCriteriaCountException e) {
            e.printStackTrace();
        }
    }
}
