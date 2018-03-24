package servlets;

import statistic.StatisticCollector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/CreateStatisticFile")
public class CreateStatisticFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String groupNumber = (String) req.getParameter("group");
        String fromDateString = (String) req.getParameter("from");
        String toDateString = (String) req.getParameter("till");

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date fromDate = dateFormat.parse(fromDateString);
            Date toDate = dateFormat.parse(toDateString);
            String filePath = StatisticCollector.createStatisticFile(groupNumber, fromDate, toDate);
            req.setAttribute("path", filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/DownloadFileServlet").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
