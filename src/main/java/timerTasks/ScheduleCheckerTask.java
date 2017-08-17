package timerTasks;

import Checker.ScheduleChecker;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;

/**
 * Task for checking groups` schedule.
 */
public class ScheduleCheckerTask implements Job {

    public ScheduleCheckerTask() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            //ScheduleChecker.groupScheduleCheck();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}