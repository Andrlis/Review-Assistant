package servlets;


import data.Student;
import data.mark.LabMark;
import data.mark.TestMark;
import resources.Hibernate.Controller.DataBaseCore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveMarkServlet")
public class SaveMarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();

        Integer id = Integer.parseInt((String) req.getParameter("id"));
        String type = (String) req.getParameter("type");
        String value = (String) req.getParameter("value");

        if (id == null || type == null || value == null) {
            req.getRequestDispatcher("WEB-INF/pages/NotFound.jsp")
                    .forward(req, resp);
        }

        try {
            Integer mark = value.equals("") ? -1: Integer.parseInt(value);

            switch (type) {
                case("lab"):
                    LabMark labMark = (LabMark) dataBaseCore.getById(LabMark.class, id);
                    labMark.setMark(mark);
                    dataBaseCore.update(labMark);

                    break;
                case ("test"):
                    TestMark testMark = (TestMark) dataBaseCore.getById(TestMark.class, id);
                    testMark.setMark(mark);
                    dataBaseCore.update(testMark);

                    break;
                case("bonus"):
                    Student student = (Student) dataBaseCore.getById(Student.class, id);
                    student.setBonusMark(mark);
                    dataBaseCore.update(student);

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
