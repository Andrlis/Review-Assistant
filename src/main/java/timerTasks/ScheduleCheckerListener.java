package timerTasks;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

/*Обновляет расписание каждый день в 00:15*/
public class ScheduleCheckerListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        try {
            Timer scheduleTimer = new Timer();
            ScheduleCheckerTask task = new ScheduleCheckerTask();
            Date startTime = new GregorianCalendar(2017, 9, 1, 0, 15, 0).getTime();

            // startTime is in past, so task will be runned during deployment. Period = ms*sec*min*h
            scheduleTimer.scheduleAtFixedRate(task, startTime, 1000 * 60 * 60 * 24);
            servletContext.setAttribute("scheduletimer", scheduleTimer);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        // get our timer from the Context
        Timer scheduleTimer = (Timer) servletContext.getAttribute("scheduletimer");
        if (scheduleTimer != null) {
            scheduleTimer.cancel();
        }
        servletContext.removeAttribute("scheduletimer");
    }
}
