package servlets;

import dao.DataBaseCore;
import data.group.Group;
import exceptions.DataBaseQueryException;
import logics.GroupLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteGroup")
public class DeleteGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupLogic groupLogic = new GroupLogic();
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();

        String groupNumber = (String) req.getParameter("groupNumber");

        Group group = groupLogic.getByNumber(groupNumber);

        try {
            dataBaseCore.delete(group);
        } catch (DataBaseQueryException e) {
            e.printStackTrace();
        }
    }
}