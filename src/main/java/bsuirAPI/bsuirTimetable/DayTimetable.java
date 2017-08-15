package bsuirAPI.bsuirTimetable;

import java.util.ArrayList;

/**
 * Created by Andrey on 17.07.2017.
 */
public class DayTimetable {

    private String day;
    private ArrayList<Subject> lessons;

    public DayTimetable() {
        this.day = "0";
        this.lessons = new ArrayList<Subject>();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<Subject> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Subject> lessons) {
        this.lessons = lessons;
    }

    public void setLesson(Subject subject) {
        this.lessons.add(subject);
    }

    public ArrayList<Subject>getCurrentDayLessons(String curentWeek){
        ArrayList<Subject> currentDayLessons = new ArrayList<Subject>();
        for(Subject subject: lessons){
            if(subject.getWeeks().contains(curentWeek))
                currentDayLessons.add(subject);
        }
        return currentDayLessons;
    }
}
