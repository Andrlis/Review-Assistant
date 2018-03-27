package servlets;

import resources.Hibernate.CommentsHibernateShell;
import resources.Hibernate.HibernateShellQueryException;
import resources.Hibernate.LabsHibernateShell;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentsHibernateShell commentsHibernateShell = new CommentsHibernateShell();
        LabsHibernateShell labsHibernateShell = new LabsHibernateShell();
        String id = (String) req.getParameter("id");
        String commentType = (String) req.getParameter("type");
        String comment = "";

        try {
            switch (commentType) {
                //mark
                case "m":
                    comment = labsHibernateShell.getLabComment(id);
                    //class
                case "c":
                    comment = commentsHibernateShell.getCommet(id);
            }
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }

        resp.getWriter().append(comment);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
