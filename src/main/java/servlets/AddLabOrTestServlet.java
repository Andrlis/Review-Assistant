package servlets;

import data.test.Test;
import resources.Controllers.LabController;
import resources.Controllers.TestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddLabOrTestServlet")
public class AddLabOrTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String newColumnType = (String) req.getParameter("new-column-type");
        String newLabDate = (String) req.getParameter("new-lab-date");*/
        String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        String type = (String) req.getParameter("type");
        String date = (String) req.getParameter("date");
        String newColumnType = (String) req.getParameter("new-column-type");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /*String newColumnType = (String) req.getParameter("new-column-type");
        String newLabDate = (String) req.getParameter("new-lab-date");*/
        LabController labController = new LabController();
        TestController testController = new TestController();

        String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        String type = (String) req.getParameter("type");
        String date = (String) req.getParameter("date");
        String comment = (String) req.getParameter("comment");

        try {
            if (type.equals("lab")) {
                labController.issue(groupNumber, subGroupNumber, date);
            } else {
                testController.addNewTest();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
