package timerTasks;

/*
Check repositories and timetable at 00.00
 */

import checker.RepositoryChecker;
import checker.ScheduleChecker;
import java.util.Calendar;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import resources.Hibernate.HibernateShell;

public class ComplexCheckTask implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            RepositoryChecker.checkForCommitsInGroups(HibernateShell.getGroupKeeper());

            if(!isSunday()) {
                ScheduleChecker.groupScheduleCheck();
            }

        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

    private boolean isSunday(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return (day == 7);
    }
}
