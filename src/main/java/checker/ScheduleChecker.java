package checker;

import data.Group.Group;
import data.Group.GroupsKeeper;
import data.Group.SubGroup;
import data.UniversityClass;
import resources.HibernateShell;
import resources.TimeLogic;
import bsuirAPI.BsuirParser;
import bsuirAPI.BsuirRequests;
import bsuirAPI.bsuirTimetable.Subject;
import bsuirAPI.bsuirTimetable.Timetable;
import org.apache.log4j.Logger;

/**
 * Created by Andrey.
 * Search "ТРиТПО" laboratory work for all groups on current day.
 */
public class ScheduleChecker {
    private static final Logger logger = Logger.getLogger(ScheduleChecker.class);

    public static void groupScheduleCheck() throws Exception {
        logger.info("Start schedule check.");
        Timetable timetable;
        GroupsKeeper groups = HibernateShell.getGroupKeeper();

        for (Group group : groups.getGroupList()) {
            logger.info("Current group number : " + group.getNumberOfGroup() + ".");
            timetable = BsuirParser.parseTimetable(BsuirRequests.getTimetable(group.getNumberOfGroup()));

            for (Subject lesson : timetable.getCurrentDaySchedule(BsuirRequests.getCurrentWeek())) {
                if (lesson.getLessonName().equals("ТРиТПО") && lesson.getLessonType().equals("ЛР")) {
                    logger.info("Current lesson for subgroup number " + lesson.getSubGroup() + ".");
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
        logger.info("End schedule check.");
    }
}
