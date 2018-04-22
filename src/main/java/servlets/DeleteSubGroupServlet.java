package servlets;

import dao.DataBaseCore;
import data.group.SubGroup;
import exceptions.DataBaseQueryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteSubGroup")
public class DeleteSubGroupServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataBaseCore dataBaseCore = DataBaseCore.getInstance();

        String idSubGroup = (String) req.getParameter("subgroupId");

        try {
            SubGroup subGroup = (SubGroup) dataBaseCore.getById(SubGroup.class, Integer.parseInt(idSubGroup));
            dataBaseCore.delete(subGroup);
        } catch (DataBaseQueryException e) {
            e.printStackTrace();
        }
    }
}