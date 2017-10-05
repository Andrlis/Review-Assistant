package bsuirAPI;

import data.Group.Group;
import bsuirAPI.bsuirTimetable.DayTimetable;
import bsuirAPI.bsuirTimetable.Subject;
import bsuirAPI.bsuirTimetable.Timetable;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BsuirParserTest extends TestCase {

    @Test
    public void testParseGroups() throws Exception{
        String xmlToParase = "<studentGroupXmlModels><studentGroup><id>21366</id><name>550502</name><calendarId>i6teqtgc4vunhp1q8at6du26qg@group.calendar.google.com" +
                "</calendarId><course>3</course><facultyId>20026</facultyId><specialityDepartmentEducationFormId>20019</specialityDepartmentEducationFormId>" +
                "</studentGroup></studentGroupXmlModels>";
        Group group = new Group();
        group.setScheduleApiGroupNumber("21366");
        group.setNumberOfGroup("550502");

        System.out.println("Test BsuirParser.parseGroups()");
        ArrayList<Group>groupList = BsuirParser.parseGroups(xmlToParase);
        assertNotNull(groupList);
        assertEquals(group.getNumberOfGroup(), groupList.get(0).getNumberOfGroup());
        assertEquals(group.getScheduleApiGroupNumber(),groupList.get(0).getScheduleApiGroupNumber());
        System.out.println("Test success.");
    }

    @Test
    public void testParseTimetable() throws Exception {
        String xmlToParase = "<scheduleXmlModels><scheduleModel><schedule><auditory>211-3</auditory><employee><academicDepartment>ИСиТ</academicDepartment><calendarId>" +
                "ar3pmmo0porclbpmnbd9ae11cs@group.calendar.google.com</calendarId><firstName>Ирина</firstName><id>500307</id><lastName>Коренская</lastName>" +
                "<middleName>Николаевна</middleName><rank>без звания</rank></employee><lessonTime>18:45-22:00</lessonTime><lessonType>ЛК</lessonType>" +
                "<note/><numSubgroup>0</numSubgroup><studentGroup>481061</studentGroup><subject>ОerpС</subject><weekNumber>1</weekNumber>" +
                "<weekNumber>3</weekNumber><zaoch>false</zaoch></schedule><weekDay>Понедельник</weekDay></scheduleModel></scheduleXmlModels>";

        Timetable assertTimetable = new Timetable();
        DayTimetable dayTimetable = new DayTimetable();
        dayTimetable.getLessons().add(new Subject("ОerpС", "18:45-22:00", "211-3", "ЛК", "0",  new ArrayList<String>(Arrays.asList("1", "3"))));
        assertTimetable.getDays().add(dayTimetable);

        System.out.println("Test BsuirParser.parseTimetable()");
        Timetable timetable = BsuirParser.parseTimetable(xmlToParase);
        assertNotNull(timetable);
        assertEquals(assertTimetable.getDays().get(0).getLessons().get(0).getLessonName(), timetable.getDays().get(0).getLessons().get(0).getLessonName());
        assertEquals(assertTimetable.getDays().get(0).getLessons().get(0).getLessonType(),timetable.getDays().get(0).getLessons().get(0).getLessonType());
        assertEquals(assertTimetable.getDays().get(0).getLessons().get(0).getClassroom(),timetable.getDays().get(0).getLessons().get(0).getClassroom());
        assertEquals(assertTimetable.getDays().get(0).getLessons().get(0).getTime(),timetable.getDays().get(0).getLessons().get(0).getTime());
        assertEquals(assertTimetable.getDays().get(0).getLessons().get(0).getSubGroup(),timetable.getDays().get(0).getLessons().get(0).getSubGroup());
        assertEquals(assertTimetable.getDays().get(0).getLessons().get(0).getWeeks(),timetable.getDays().get(0).getLessons().get(0).getWeeks());
        System.out.println("Test success.");
    }
}