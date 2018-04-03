package servlets;

import data.Student;
import data.UniversityClass;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.Test;
import resources.Controllers.DefaultController;
import resources.Hibernate.Exceptions.DataBaseQueryException;
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
        DefaultController defaultController = new DefaultController();

        String commentType = (String) req.getParameter("type");
        String comment = "";
        Integer commentEntityId = Integer.parseInt((String) req.getParameter("commentId"));
        String secondCommentIdParam = ((String) req.getParameter("secondCommentId"));

        Integer secondCommentId = secondCommentIdParam.equals("") ? -1 : Integer.parseInt(secondCommentIdParam);

        try {
            switch (commentType) {
                case "lab": // mark
                    LabMark labMark = (LabMark) defaultController.getById(LabMark.class,
                            commentEntityId);
                    comment = JsonMaker.getJsonLabMarkComment(labMark);

                    break;
                case "class": //class
                    Student student = (Student) defaultController.getById(Student.class,
                            commentEntityId);
                    UniversityClass universityClass = (UniversityClass) defaultController.getById(UniversityClass.class,
                            secondCommentId);
                    comment = JsonMaker.getJsonClassComment(
                            student, universityClass);
                    break;
                case "test": //test
                    TestMark testMark = (TestMark) defaultController.getById(TestMark.class,
                            commentEntityId);
                    comment = JsonMaker.getJsonTestMarkComment(testMark);

                    break;
                case "bonus"://bonus
                    Student student1 = (Student) defaultController.getById(Student.class,
                            commentEntityId);
                    comment = JsonMaker.getJsonBonusMarkComment(student1);

                    break;
            }
        } catch (DataBaseQueryException e) {
            e.printStackTrace();
        }

        resp.getWriter().append(comment);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
