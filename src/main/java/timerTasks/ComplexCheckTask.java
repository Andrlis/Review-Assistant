package timerTasks;

/*
Check repositories and timetable at 00.00
 */

import checker.RepositoryChecker;
import checker.ScheduleChecker;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import resources.Hibernate.HibernateShell;

public class ComplexCheckTask implements Job {

    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Minsk"));

    /*
        Temporary container for holidays.
     */
    private HashMap<Integer, List<Integer>> holidays = new HashMap<Integer, List<Integer>>(){{
        put(11, Arrays.asList(7));
        put(12, Arrays.asList(25));
        put(1, Arrays.asList(1, 7));
        put(3, Arrays.asList(8));
        put(4, Arrays.asList(8,17));
        put(5, Arrays.asList(1, 9));
        put(7, Arrays.asList(3));
    }};

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            RepositoryChecker.checkForCommitsInGroups(HibernateShell.getGroupKeeper());

            if(isMidnight() && !isSunday() && !isHoliday()) {
                ScheduleChecker.groupScheduleCheck();
            }

        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

    private boolean isSunday(){
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return (day == 7);
    }

    private boolean isHoliday(){
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if(holidays.containsKey(month)){
            if(holidays.get(month).contains(day))
                return true;
        }
        return false;
    }

    private boolean isMidnight(){
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return (hour == 0 && minute == 0 && second == 0);
    }
}