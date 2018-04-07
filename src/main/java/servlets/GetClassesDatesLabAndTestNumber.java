package servlets;

import data.group.Group;
import data.group.SubGroup;
import logics.GroupLogic;
import jsonMaker.JsonMaker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetClassesDatesLabAndTestNumber")
public class GetClassesDatesLabAndTestNumber extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GroupLogic groupLogic = new GroupLogic();

        String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        try {
            Group    group    = groupLogic.getByNumber(groupNumber);
            SubGroup subGroup = group.getSubGroup(subGroupNumber);
            String str = JsonMaker.getJsonSubGroupClasses(subGroup);
            resp.getWriter().append(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //resp.getWriter().append("[\"12.02.17 12:40\", \"13.02.17 12:40\", \"14.02.17 12:40\"]");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
