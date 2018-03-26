package servlets;

import resources.Hibernate.CommentsHibernateShell;
import resources.Hibernate.HibernateShellQueryException;
import resources.Hibernate.LabsHibernateShell;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveCommentOnMark")
public class SaveCommentOnMark extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LabsHibernateShell labsHibernateShell = new LabsHibernateShell();
        String idLabMark = (String) req.getParameter("id_lab_mark");
        String comment = (String) req.getParameter("comment");

        try {
            labsHibernateShell.updateLabComment(idLabMark,comment);
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
