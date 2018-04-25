package servlets;

import dao.DataBaseCore;
import data.group.Group;
import data.group.SubGroup;
import exceptions.DataBaseQueryException;
import logics.GroupLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteGroupSubGroup")
public class DeleteGroupSubGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupLogic groupLogic = new GroupLogic();
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();

        String number = (String) req.getParameter("number");

        String s[] = number.split("_");

        if(s.length == 2){
            Group group = groupLogic.getByNumber(s[0]);

            SubGroup subGroup = group.getSubGroup(s[1]);

            group.setSubGroupList(null);
            subGroup.setGroup(null);
            try {
                dataBaseCore.delete(subGroup);
            } catch (DataBaseQueryException e) {
                e.printStackTrace();
            }

        } else {
            Group group = groupLogic.getByNumber(number);
            List<SubGroup> subGroupList = group.getSubGroupList();
            group.setSubGroupList(null);
            try {
                for (SubGroup subGroup : subGroupList){
                    dataBaseCore.delete(subGroup);
                }

                dataBaseCore.delete(group);
            } catch (DataBaseQueryException e) {
                e.printStackTrace();
            }
        }


    }
}