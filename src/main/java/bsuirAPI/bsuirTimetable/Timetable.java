package bsuirAPI.bsuirTimetable;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Andrey on 17.07.2017.
 * Contains all timetable for group.
 */
public class Timetable {

    private ArrayList<DayTimetable> days;

    public Timetable() {
        this.days = new ArrayList<DayTimetable>();
    }

    public Timetable(ArrayList<DayTimetable> days){this.days = days;}

    public ArrayList<DayTimetable> getDays() {
        return days;
    }

    public void setDays(ArrayList<DayTimetable> days) {
        this.days = days;
    }

    public void setDay(DayTimetable day) {
        this.days.add(day);
    }

    /**
     * Return timetable for current day of week.
     * @return
     */
    public ArrayList<Subject> getCurrentDaySchedule(String currentWeek){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        //return days.get((day == 1) ? 6 : day-2)
                //.getCurrentDayLessons(currentWeek);
        return days.get(2)
            .getCurrentDayLessons(currentWeek);
    }
}
