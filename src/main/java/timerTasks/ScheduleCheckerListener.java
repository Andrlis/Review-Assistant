package timerTasks;

import org.quartz.*;
import static org.quartz.TriggerBuilder.newTrigger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.impl.StdSchedulerFactory;

public class ScheduleCheckerListener implements ServletContextListener {
    private Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        try {
            // Setup the Job class and the Job group
            JobDetail job = JobBuilder.newJob(ScheduleCheckerTask.class).withIdentity(
                    "ScheduleQuartzJob", "Group2").build();

            // Create a Trigger that fires every 5 minutes.
            Trigger trigger = newTrigger()
                    .withIdentity("ScheduleTrigger", "Group2")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(15)
                            .repeatForever()).build();    //Setup interval

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
