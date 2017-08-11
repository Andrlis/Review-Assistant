package bsuirAPI.bsuirTimetable;

import java.util.ArrayList;

/**
 * Created by Andrey on 17.07.2017.
 */
public class Subject {

    private String lessonName;
    private String time;
    private String classroom;
    private String lessonType;
    private String subGroup;
    private ArrayList<String> weeks;

    public Subject(){
        this.lessonName = "null";
        this.time = "null";
        this.classroom = "null";
        this.lessonType = "null";
        this.subGroup = "0";
        this.weeks = new ArrayList<String>();
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public ArrayList<String> getWeeks() {
        return weeks;
    }

    public void setWeeks(ArrayList<String> weeks) {
        this.weeks = weeks;
    }

    public void setWeek(String week){
        this.weeks.add(week);
    }
}
