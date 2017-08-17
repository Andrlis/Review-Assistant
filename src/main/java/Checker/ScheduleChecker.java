package Checker;

import Data.Group.Group;
import Data.Group.GroupsKeeper;
import bsuirAPI.BsuirParser;
import bsuirAPI.BsuirRequests;
import bsuirAPI.bsuirTimetable.DayTimetable;
import bsuirAPI.bsuirTimetable.Subject;
import bsuirAPI.bsuirTimetable.Timetable;

/**
 * Created by Andrey.
 * Search "ТРиТПО" laboratory work for all groups on current day.
 */
public class ScheduleChecker{

      public static void groupScheduleCheck(GroupsKeeper groups) throws Exception{
          DayTimetable currentDaySchedule;
          Timetable timetable;

          for(Group group: groups.getGroupList()){

              timetable = BsuirParser.parseTimetable(BsuirRequests.getTimetable(group.getScheduleApiGroupNumber()));
              currentDaySchedule = timetable.getCurrentDaySchedule();

              for(Subject lesson: currentDaySchedule.getCurrentDayLessons(BsuirRequests.getCurrentWeek())){
                  if(lesson.getLessonName().equals("ТРиТПО")&&lesson.getLessonType().equals("ЛР")){
                       //TODO save lesson`s date and time at database.
                       break;
                  }
              }
          }

      }
}
