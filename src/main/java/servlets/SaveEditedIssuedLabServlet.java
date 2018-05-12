package servlets;

import dao.DataBaseCore;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import exceptions.DataBaseQueryException;
import jsonMaker.JsonMaker;
import logics.GroupLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SaveEditedIssuedLab")
public class SaveEditedIssuedLabServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String coef = ((String) req.getParameter("coef"));
        String idOfEditedLab = (String) req.getParameter("id");

        try {
            IssuedLab issuedLab = (IssuedLab)DataBaseCore.getInstance().getById(IssuedLab.class, Integer.parseInt(idOfEditedLab));
            issuedLab.setCoefficientOfCurrentDeadline(Double.parseDouble(coef));

            DataBaseCore.getInstance().update(issuedLab);
        } catch (DataBaseQueryException e) {
            e.printStackTrace();
        }
    }
}
