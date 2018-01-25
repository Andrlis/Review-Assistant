package checker;

import com.sun.javafx.scene.SceneHelper;
import data.group.Group;
import data.group.GroupsKeeper;
import data.group.SubGroup;
import data.UniversityClass;
import resources.Hibernate.HibernateShell;
import resources.TimeLogic;
import bsuirAPI.BsuirParser;
import bsuirAPI.BsuirRequests;
import bsuirAPI.bsuirTimetable.Subject;
import bsuirAPI.bsuirTimetable.Timetable;
import org.apache.log4j.Logger;

import java.util.Date;

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

            boolean[] hasCoefficientOfLabsBeenChanged = new boolean[group.getSubGroupList().size()];
            for (Subject lesson : timetable.getCurrentDaySchedule(BsuirRequests.getCurrentWeek())) {

                if (lesson.getLessonName().equals("ТРиТПО") && lesson.getLessonType().equals("ЛР")) {
                    String subgroupNumber = lesson.getSubGroup();

                    logger.info("Current lesson for subgroup number " + subgroupNumber + ".");
                    if (subgroupNumber.equals("0")) {
                        for (SubGroup subGroup: group.getSubGroupList())
                            ScheduleChecker.addNewClassDate(subGroup, TimeLogic.getTodayDatePlusTime(lesson.getTime()), hasCoefficientOfLabsBeenChanged);
                    } else {
                        ScheduleChecker.addNewClassDate(group.getSubGroup(subgroupNumber),
                                TimeLogic.getTodayDatePlusTime(lesson.getTime()), hasCoefficientOfLabsBeenChanged);
                    }
                    break;
                }
            }
        }
        logger.info("End schedule check.");
    }

    private static void addNewClassDate(SubGroup subGroup, Date date, boolean[] hasCoefficientOfLabsBeenChanged) {
        UniversityClass universityClass = new UniversityClass();

        universityClass.setDate(date);
        universityClass.setSubGroup(subGroup);
        subGroup.addUniversityClass(universityClass);

        int subGroupNumber = Integer.parseInt(subGroup.getSubGroupNumber()) - 1;
        if (!hasCoefficientOfLabsBeenChanged[subGroupNumber]) {
            hasCoefficientOfLabsBeenChanged[subGroupNumber] = true;
            subGroup.decreaseCoefficientOfLabs();
        }

        HibernateShell.save(universityClass);
    }
}
