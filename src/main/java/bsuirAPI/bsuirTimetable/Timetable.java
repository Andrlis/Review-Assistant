package bsuirAPI.bsuirTimetable;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Andrey on 17.07.2017.
 */
public class Timetable {

     private ArrayList<DayTimetable> days;

    public Timetable() {
        this.days = new ArrayList<DayTimetable>();
    }

    public ArrayList<DayTimetable> getDays() {
        return days;
    }

    public void setDays(ArrayList<DayTimetable> days) {
        this.days = days;
    }

    public void setDay(DayTimetable day) {
        this.days.add(day);
    }

    public DayTimetable getCurrentDaySchedule(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return days.get((day == 1) ? 6 : day-2);
    }
}
