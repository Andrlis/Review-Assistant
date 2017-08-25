package Checker;

import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.UniversityClass;
import Resources.HibernateShell;
import Resources.TimeLogic;
import bsuirAPI.BsuirParser;
import bsuirAPI.BsuirRequests;
import bsuirAPI.bsuirTimetable.DayTimetable;
import bsuirAPI.bsuirTimetable.Subject;
import bsuirAPI.bsuirTimetable.Timetable;

/**
 * Created by Andrey.
 * Search "ТРиТПО" laboratory work for all groups on current day.
 */
public class ScheduleChecker {

    public static void groupScheduleCheck() throws Exception {
        DayTimetable currentDaySchedule;
        Timetable timetable;
        GroupsKeeper groups = HibernateShell.getGroupKeeper();

        for (Group group : groups.getGroupList()) {

            timetable = BsuirParser.parseTimetable(BsuirRequests.getTimetable(group.getScheduleApiGroupNumber()));
            currentDaySchedule = timetable.getCurrentDaySchedule();

            for (Subject lesson : currentDaySchedule.getCurrentDayLessons(BsuirRequests.getCurrentWeek())) {
                if (lesson.getLessonName().equals("ТРиТПО") && lesson.getLessonType().equals("ЛР")) {
                    SubGroup subGroup = group.getSubGroup(lesson.getSubGroup());
                    UniversityClass universityClass = new UniversityClass();

                    universityClass.setDate(TimeLogic.getTodayDatePlusTime(lesson.getTime()));
                    universityClass.setSubGroup(subGroup);
                    subGroup.getUniversityClassesList().add(universityClass);

                    HibernateShell.update(universityClass);

                    break;
                }
            }
        }
    }
}
