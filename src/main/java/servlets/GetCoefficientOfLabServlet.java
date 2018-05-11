package servlets;

import com.sun.net.httpserver.HttpServer;
import dao.DataBaseCore;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import jsonMaker.JsonMaker;
import logics.GroupLogic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;

@WebServlet("/GetCoefficientOfLab")
public class GetCoefficientOfLabServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String groupNumber = (String) req.getParameter("group");
        String subgroupNumber = (String) req.getParameter("subgroup");
        String labDescription = ((String) req.getParameter("lab"));
        String labNumber = labDescription.split(" ")[1];


        GroupLogic groupLogic = new GroupLogic();
        Group group = groupLogic.getByNumber(groupNumber);
        SubGroup subGroup = group.getSubGroup(subgroupNumber);
        IssuedLab issuedLab = subGroup.getIssuedLabByNumber(labNumber);
        String json = JsonMaker.getIssuedLabJson(issuedLab);
        resp.getWriter().append(json);
    }
}
