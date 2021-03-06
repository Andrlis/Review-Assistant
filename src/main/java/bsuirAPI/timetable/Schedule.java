package bsuirAPI.timetable;

import java.util.ArrayList;

public class Schedule {
    private String weekDay;
    private ArrayList<Lesson> lessons;

    public Schedule() {
        this.lessons = new ArrayList<Lesson>();
    }

    public String getWeekDay() {return weekDay;}

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    ArrayList<Lesson>getCurrentDayLessons(Integer currentWeek){
        ArrayList<Lesson> currentDayLessons = new ArrayList<Lesson>();
        for(Lesson lesson: lessons){
            if(lesson.getWeekNumber().contains(currentWeek))
                currentDayLessons.add(lesson);
        }
        return currentDayLessons;
    }
}
