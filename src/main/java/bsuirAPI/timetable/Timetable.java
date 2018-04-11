package bsuirAPI.timetable;

import java.util.ArrayList;
import java.util.Calendar;

public class Timetable {
    private ArrayList<Schedule> schedules;

    public Timetable() {
        this.schedules = new ArrayList<Schedule>();
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Lesson> getCurrentDaySchedule(Integer currentWeek){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new java.util.Date());

        //Calendar.DAY_OF_WEEK of week counts with 1 And the first day is Sunday
        //But we count days of week with 0 and the first day is Monday
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) == 1 ? 6 : calendar.get(Calendar.DAY_OF_WEEK) - 2;

        return schedules.get(dayOfWeek).getCurrentDayLessons(currentWeek);
    }
}