package servlets;

import data.test.Test;
import resources.Hibernate.*;

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
       LabsHibernateShell labsHibernateShell = new LabsHibernateShell();
       TestHibernateShell testHibernateShell = new TestHibernateShell();
       String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        String type = (String) req.getParameter("type");
        String date = (String) req.getParameter("date");
        String comment = (String) req.getParameter("comment");

        try {
            if (type.equals("lab")) {
                labsHibernateShell.issueLab(groupNumber, subGroupNumber, date);
            } else {
                testHibernateShell.addNextTest();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
