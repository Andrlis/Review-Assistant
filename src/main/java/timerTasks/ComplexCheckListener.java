package timerTasks;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComplexCheckListener implements ServletContextListener {
    private Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        try {
            // Setup the Job class and the Job group
            JobDetail job = JobBuilder.newJob(ComplexCheckTask.class).withIdentity(
                    "ComplexQuartzJob", "Group3").build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("ComplexTrigger", "Group3")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 ? * * *"))
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
