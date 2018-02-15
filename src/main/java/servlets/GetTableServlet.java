package servlets;

import data.User;
import data.group.Group;
import data.group.SubGroup;
import resources.Hibernate.HibernateCore;
import resources.TableMaker.JsonMaker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetTableServlet")
public class GetTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HibernateCore hibernateCore = HibernateCore.getInstance();
        String groupNumber = (String) req.getParameter("group");
        String subGroupNumber = (String) req.getParameter("subgroup");
        String tableType = (String) req.getParameter("type");
        resp.setCharacterEncoding("UTF-8");
        User user = (User)req.getSession().getAttribute("user");
        boolean editable = user == null ? false : true;
        try {
            String table = "";
            Group group = hibernateCore.getGroupByGroupNumber(groupNumber);
            SubGroup subGroup = group.getSubGroup(subGroupNumber);
            switch (tableType.getBytes()[0]) {
                case 'm':
                    table = JsonMaker.getJsonSubGroupMarks(subGroup, editable); //Пашка добавил труе
                    break;
                case 'p':
                    table = JsonMaker.getJsonSubGroupVisits(subGroup, editable);
                    break;
                case 'e':
                    table = JsonMaker.getJsonSubGroupStudentRedact(subGroup, editable);
                    break;
                default:
                    req
                            .getRequestDispatcher("WEB-INF/pages/NotFound.jsp")
                            .forward(req, resp);
                    break;
            }

            resp.getWriter().append(table);
        } catch (Exception e) {
            req
                    .getRequestDispatcher("WEB-INF/pages/NotFound.jsp")
                    .forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
