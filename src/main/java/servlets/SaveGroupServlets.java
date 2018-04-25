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


@WebServlet("/SaveGroup")
public class SaveGroupServlets extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupLogic groupLogic = new GroupLogic();
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();

        String groupNumber = (String) req.getParameter("groupNumber");
        String newGroupNumber = (String) req.getParameter("newGroupNumber");

        if(groupNumber.equals("")){
            Group group = new Group();
            group.setNumberOfGroup(newGroupNumber);

            try {
                dataBaseCore.create(group);
            } catch (DataBaseQueryException e) {
                e.printStackTrace();
            }
        } else {
            Group group = groupLogic.getByNumber(groupNumber);

            group.setNumberOfGroup(newGroupNumber);

            group.setSubGroupList(null);


            try {
                dataBaseCore.update(group);
            } catch (DataBaseQueryException e) {
                e.printStackTrace();
            }
        }
    }
}
