package timerTasks;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComplexCheckListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(ComplexCheckListener.class);
    private Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        try {
            // Setup the Job class and the Job group
            JobDetail job = JobBuilder.newJob(ComplexCheckTask.class).withIdentity(
                    "ComplexQuartzJob", "Group1").build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("ComplexTrigger", "Group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * ? * * *"))          //At second :00 of minute :00 of every hour
                    .forJob(job)
                    .build();

            // Setup the Job and Trigger with Scheduler & schedule jobs
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        }
        catch (SchedulerException e) {
            logger.error(e.toString());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
        try {
            scheduler.shutdown();
        }   catch (SchedulerException e) {
            logger.error(e.toString());
        }
    }
}
