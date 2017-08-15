package Checker;

import Data.Group.Group;
import Data.Group.GroupsKeeper;
import bsuirAPI.BsuirParser;
import bsuirAPI.BsuirRequests;
import bsuirAPI.bsuirTimetable.Timetable;


public class ScheduleChecker {
    private BsuirParser parser;
    private BsuirRequests requests;
    private Timetable timetable;
    private String currentWeek;


    public  void groupScheduleCheck(GroupsKeeper groups){
        for(Group group: groups.getGroupList()){
            try {
                timetable = parser.parseTimetable(requests.getTimetable(group.getScheduleApiGroupNumber()));

            }catch (Exception exc){
                exc.printStackTrace();
            }
        }

    }
}
