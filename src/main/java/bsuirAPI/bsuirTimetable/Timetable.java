package bsuirAPI.bsuirTimetable;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;

/**
 * Created by Andrey on 17.07.2017.
 */
public class Timetable {

    private String currentWeek;
    private ArrayList<DayTimetable> days;

    public Timetable(){
        this.currentWeek = "0";
        this.days = new ArrayList<DayTimetable>();
    }

    public String getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(String currentWeek) {
        this.currentWeek = currentWeek;
    }

    public ArrayList<DayTimetable> getDays() {
        return days;
    }

    public void setDays(ArrayList<DayTimetable> days) {
        this.days = days;
    }

    public void setDay(DayTimetable day){
        this.days.add(day);
    }
}
