package timerTasks;

import org.quartz.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Start task which check groups` schedule.
 */
public class ScheduleCheckerListener implements ServletContextListener {
    private Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        try {
            // Setup the Job class and the Job group
            JobDetail job = JobBuilder.newJob(ScheduleCheckerTask.class).withIdentity(
                    "ScheduleQuartzJob", "Group2").build();

            // Create a Trigger that fires every 5 minutes.
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("ScheduleTrigger", "Group2")
                    .startNow()
                    .withSchedule(
                            CronScheduleBuilder.dailyAtHourAndMinute(0, 30))
                    .forJob(job)
                    .build();

            // Setup the Job and Trigger with Scheduler & schedule jobs
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
        try {
            scheduler.shutdown();
        }   catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
