package bsuirAPI.bsuirScheduleJson;

import java.util.ArrayList;

public class Lesson {
    private ArrayList<Integer> weekNumber;
    private int numSubgroup;
    private String lessonTime;
    private String subject;
    private String lessonType;

    public Lesson() {
        this.weekNumber = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(ArrayList<Integer> weekNumber) {
        this.weekNumber = weekNumber;
    }

    public int getNumSubgroup() {
        return numSubgroup;
    }

    public void setNumSubgroup(int numSubgroup) {
        this.numSubgroup = numSubgroup;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }
}
