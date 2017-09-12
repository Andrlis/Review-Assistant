package servlets;

import Data.Group.Group;
import Data.Group.SubGroup;
import Resources.HibernateShell;
import intercalationJavaHTML.TableGenerator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/selectgroup")
public class SelectGroupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupName = (String) request.getParameter("selectGroup");

        Group group = HibernateShell.getGroupByGroupNumber(groupName);
        TableGenerator tableGenerator = new TableGenerator();

        if(group != null) {
            for (SubGroup subGroup : group.getSubGroupList()) {
                tableGenerator.createMarksTable(subGroup);
            }
        }
    }
}
