package timerTasks;

/*
Check timetable and repositories at 00.00
 */

import checker.RepositoryChecker;
import checker.ScheduleChecker;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import resources.Hibernate.HibernateShell;

public class ComplexCheckTask implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            RepositoryChecker.checkForCommitsInGroups(HibernateShell.getGroupKeeper());
            ScheduleChecker.groupScheduleCheck();
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
