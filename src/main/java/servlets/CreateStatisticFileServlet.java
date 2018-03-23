package servlets;

import statistic.StatisticCollector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

            FileInputStream inputStream = new FileInputStream(filePath);

            try (OutputStream outputStream = resp.getOutputStream()) {
                resp.setHeader("Content-Disposition", String.format("attachment; filename=550501.xls"));
                int buffer;
                while ((buffer = inputStream.read()) != -1) {
                    outputStream.write(buffer);
                }
                outputStream.flush();
            } finally {
                inputStream.close();
            }

//            resp.getWriter().append(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
