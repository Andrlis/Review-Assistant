package timerTasks;

import checker.Checker;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import resources.HibernateShell;

/**
 * Task for checking students` repositories.
 */
public class RepositoryCheckerTask implements Job {

    public RepositoryCheckerTask() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        //Checker.checkForCommitsInGroups(HibernateShell.getGroupKeeper());             //Раскомментировать при запуске
    }
}