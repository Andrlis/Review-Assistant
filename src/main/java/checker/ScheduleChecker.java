package checker;

import bsuirAPI.BsuirParserFactory;
import bsuirAPI.BsuirRequestFactory;
import bsuirAPI.BsuirRequestInterface;
import dao.DataBaseCoreInterface;
import data.group.Group;
import data.group.SubGroup;
import data.UniversityClass;
import data.lab.IssuedLab;
import dao.DataBaseCore;
import exceptions.DataBaseQueryException;
import resources.TimeLogic;
import bsuirAPI.timetable.*;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by Andrey.
 * Search "ТРиТПО" laboratory work for all groups on current day.
 */
public class ScheduleChecker {
    private static final Logger logger = Logger.getLogger(ScheduleChecker.class);

    private DataBaseCoreInterface dataBaseCore;
    private BsuirRequestInterface bsuirRequestInterface;

    public ScheduleChecker() {
        dataBaseCore = DataBaseCore.getInstance();
        bsuirRequestInterface = BsuirRequestFactory.getRequest();
    }

    public ScheduleChecker(DataBaseCoreInterface core, BsuirRequestInterface request) {
        dataBaseCore = core;
        bsuirRequestInterface = request;
    }


    public void groupScheduleCheck(List<Group> groups) throws Exception {
        logger.info("Start schedule check.");
        Timetable timetable;

        for (Group group : groups) {
            logger.info("Current group number : " + group.getNumberOfGroup() + ".");

            timetable = BsuirParserFactory.getParser().parseTimetable(
                    bsuirRequestInterface.getTimetable(group.getNumberOfGroup()));

            boolean[] hasCoefficientOfLabsBeenChanged = new boolean[group.getSubGroupList().size()];
            for (Lesson lesson : timetable.getCurrentDaySchedule(
                    new Integer(bsuirRequestInterface.getCurrentWeek()))) {

                if (lesson.getSubject().equals("ТРиТПО") && lesson.getLessonType().equals("ЛР")) {
                    String subgroupNumber = String.valueOf(lesson.getNumSubgroup());

                    logger.info("Current lesson for subgroup number " + subgroupNumber + ".");
                    if (subgroupNumber.equals("0")) {
                        for (SubGroup subGroup : group.getSubGroupList())
                            addNewClassDate(subGroup,
                                    TimeLogic.getTodayDatePlusTime(lesson.getLessonTime()),
                                    hasCoefficientOfLabsBeenChanged);
                    } else {
                        addNewClassDate(group.getSubGroup(subgroupNumber),
                                TimeLogic.getTodayDatePlusTime(lesson.getLessonTime()),
                                hasCoefficientOfLabsBeenChanged);
                    }
                    break;
                }
            }
        }
        logger.info("End schedule check.");
    }

    private void addNewClassDate(SubGroup subGroup, Date date,
                                 boolean[] hasCoefficientOfLabsBeenChanged) {
        UniversityClass universityClass = new UniversityClass();

        universityClass.setDate(date);
        universityClass.setSubGroup(subGroup);
        subGroup.addUniversityClass(universityClass);

        int subGroupNumber = Integer.parseInt(subGroup.getSubGroupNumber()) - 1;
        if (!hasCoefficientOfLabsBeenChanged[subGroupNumber]) {
            hasCoefficientOfLabsBeenChanged[subGroupNumber] = true;

            for (IssuedLab issuedLab : subGroup.getIssuedLabsList()) {
                issuedLab.decreaseCoefficient();

                try {
                    dataBaseCore.update(issuedLab);
                } catch (DataBaseQueryException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            dataBaseCore.create(universityClass);
        } catch (DataBaseQueryException e) {
            logger.debug(e.getMessage());
        }
    }
}
