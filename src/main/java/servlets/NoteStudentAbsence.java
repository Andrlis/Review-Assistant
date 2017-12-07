package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/NoteStudentAbsence")
public class NoteStudentAbsence extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        String studentId = (String) req.getParameter("studentId");
        String classId = (String) req.getParameter("classId");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
