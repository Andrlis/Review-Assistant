package servlets;

import dao.DataBaseCore;
import data.Student;
import data.StudentFactory;
import data.group.Group;
import logics.GroupLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/SaveStudent")
public class SaveStudent extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();
        GroupLogic groupLogic = new GroupLogic();

        String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        String studentName = (String) req.getParameter("surname");
        studentName += " " + (String) req.getParameter("name");
        String studentId = (String) req.getParameter("studentId");
        String gitRepo = (String) req.getParameter("git");
        String eMail = (String) req.getParameter("email");

        try {
            if (studentId.equals("")) {
                StudentFactory studentFactory = new StudentFactory(dataBaseCore);

                Student student = studentFactory.createStudent(studentName, eMail, gitRepo,
                        groupNumber, subGroupNumber);

                dataBaseCore.create(student);
            } else {
                Student student = (Student) dataBaseCore.getById(Student.class, Integer.parseInt(studentId));

                student.setFulName(studentName);
                student.seteMail(eMail);
                student.setGitURL(gitRepo);

                Group group = groupLogic.getByNumber(groupNumber);

                student.setSubGroup(group.getSubGroup(subGroupNumber));

                dataBaseCore.update(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
