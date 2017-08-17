package timerTasks;

import java.util.Date;

import Checker.Checker;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Task for checking students` repositories.
 */
public class RepositoryCheckerTask implements Job {

    public RepositoryCheckerTask() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        //Checker.checkForCommitsInGroups();
    }
}