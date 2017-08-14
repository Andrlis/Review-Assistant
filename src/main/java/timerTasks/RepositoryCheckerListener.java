package timerTasks;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

public class RepositoryCheckerListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        try {
            Timer repositoryTimer = new Timer();
            RepositoryCheckerTask task = new RepositoryCheckerTask();
            Date startTime = new GregorianCalendar(2017, 9, 1, 0, 05, 0).getTime();

            // startTime is in past, so task will be runned during deployment. Period = ms*sec*min
            repositoryTimer.scheduleAtFixedRate(task, startTime, 1000 * 60 * 30);
            servletContext.setAttribute("repotimer", repositoryTimer);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        // get our timer from the Context
        Timer repositoryTimer = (Timer) servletContext.getAttribute("repotimer");
        if (repositoryTimer != null) {
            repositoryTimer.cancel();
        }
        servletContext.removeAttribute("repotimer");
    }
}
