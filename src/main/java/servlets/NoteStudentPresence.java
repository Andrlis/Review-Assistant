package servlets;

import data.Student;
import data.UniversityClass;
import resources.Hibernate.Controller.DataBaseCore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/NoteStudentPresence")
public class NoteStudentPresence extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();

        String studentId = (String) req.getParameter("studentId");
        String classId = (String) req.getParameter("classId");
        try {
            Student student = (Student) dataBaseCore.getById(Student.class,
                    Integer.parseInt(studentId));
            UniversityClass universityClass = (UniversityClass) dataBaseCore.getById(UniversityClass.class,
                    Integer.parseInt(classId));

            student.removeMissedClass(universityClass);

            dataBaseCore.update(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
