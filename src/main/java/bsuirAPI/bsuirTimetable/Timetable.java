package bsuirAPI.bsuirTimetable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Andrey on 17.07.2017.
 * Contains all timetable for group.
 */
public class Timetable {

    private HashMap<Integer, DayTimetable> days;

    public Timetable() {
        this.days = new HashMap<Integer, DayTimetable>();
    }

    public Timetable(HashMap<Integer, DayTimetable> days) {this.days = days;}

    public HashMap<Integer, DayTimetable> getDays() {
        return days;
    }

    public void setDays(HashMap<Integer, DayTimetable> days) {
        this.days = days;
    }

    public void setDay(Integer dayNumber, DayTimetable day) {
        this.days.put(dayNumber, day);
    }

    /**
     * Return timetable for current day of week.
     * @return
     */
    public ArrayList<Subject> getCurrentDaySchedule(String currentWeek){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());

        //Calendar.DAY_OF_WEEK of week counts with 1 And the first day is Sunday
        //But we count days of week with 0 and the first day is Monday
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? 6 : calendar.get(Calendar.DAY_OF_WEEK) - 2;

        if (days.containsKey(dayOfWeek)) {
            return days.get(dayOfWeek)
                    .getCurrentDayLessons(currentWeek);
        } else
            return new ArrayList<Subject>();
    }
}
