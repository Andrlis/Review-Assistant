package timerTasks;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;

public class ScheduleCheckerTask implements Job {

    public ScheduleCheckerTask() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("ScheduleCheckerTask"+ new Date());
    }
}