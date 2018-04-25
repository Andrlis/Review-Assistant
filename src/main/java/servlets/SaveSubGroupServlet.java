package servlets;

import dao.DataBaseCore;
import data.group.Group;
import data.group.SubGroup;
import data.lecturer.Lecturer;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;
import logics.GroupLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveSubGroup")
public class SaveSubGroupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();
        GroupLogic groupLogic = new GroupLogic();

        String l = (String)req.getParameter("lecturer");
        String number = (String)req.getParameter("number");
        String s[] = number.split("_");

        Group group = groupLogic.getByNumber(s[0]);

        SubGroup subGroup = new SubGroup();
        subGroup.setGroup(group);

        Lecturer lecturer = null;

        try {
            lecturer = (Lecturer) dataBaseCore.getByCriteria(Lecturer.class, "fullName", l);
        } catch (DataBaseQueryException | DataBaseCriteriaCountException e) {
            e.printStackTrace();
        }

        subGroup.setLecturer(lecturer);

        if(s.length == 2){
            subGroup.setSubGroupNumber(s[1]);
        } else {
            subGroup.setSubGroupNumber(String.valueOf((group.getSubGroupList().size() + 1)));
        }

        try {
            dataBaseCore.create(subGroup);
        } catch (DataBaseQueryException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/Welcome");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}