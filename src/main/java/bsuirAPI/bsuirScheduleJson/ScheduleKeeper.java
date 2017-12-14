package bsuirAPI.bsuirScheduleJson;

import java.util.ArrayList;

public class ScheduleKeeper {
    private ArrayList<Schedule> schedules;

    public ScheduleKeeper() {
        this.schedules = new ArrayList<Schedule>();
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }
}
