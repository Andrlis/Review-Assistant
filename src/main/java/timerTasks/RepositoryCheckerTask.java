package timerTasks;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RepositoryCheckerTask implements Job {

    public RepositoryCheckerTask() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("RepositoryCheckerTask"+ new Date());
    }
}