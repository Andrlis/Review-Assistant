package servlets;

import data.group.Group;
import data.group.SubGroup;
import resources.HibernateShell;
import resources.TableMaker.JsonMaker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveMarkServlet")
public class SaveMarkServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String) req.getParameter("id");
        String type = (String) req.getParameter("type");
        String value = (String) req.getParameter("value");
        if (type.equals("lab")) {
            HibernateShell.updateLabMark(Integer.parseInt(id), Integer.parseInt(value));
        } else if (type.equals("test")) {
            HibernateShell.updatTestMark(Integer.parseInt(id), Integer.parseInt(value));
        } else if (type.equals("bonus")) {
            //HibernateShell.
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
