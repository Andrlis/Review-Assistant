package Checker;

import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Resources.HibernateShell;
import bsuirAPI.BsuirParser;
import bsuirAPI.BsuirRequests;
import org.junit.Before;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.easymock.EasyMock.anyObject;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;
import static org.easymock.EasyMock.expect;

import org.junit.Test;

public class ScheduleCheckerTest {
    private GroupsKeeper groupsKeeper;
    private Group group;
    private String strXml;

    @Before
    public void setUp(){
        group.setNumberOfGroup("550503");
        group.setScheduleApiGroupNumber("550503");
        group.setAmountOfTest(0);
        groupsKeeper.addGroup(group);

        strXml = "<scheduleXmlModels>"+
                "<scheduleModel><schedule><auditory>512-5</auditory><employee><firstName>Алексей</firstName><id>504389</id>\n" +
                "<lastName>Яночкин</lastName><middleName>Леонидович</middleName></employee><lessonTime>18:45-20:20</lessonTime><lessonType>ЛР</lessonType>\n" +
                "<numSubgroup>0</numSubgroup><studentGroup>550502</studentGroup><subject>ТРиТПО</subject><weekNumber>1</weekNumber></schedule></scheduleModel>"+

                "<scheduleModel><schedule><auditory>512-5</auditory><employee><firstName>Алексей</firstName><id>504389</id>\n" +
                "<lastName>Яночкин</lastName><middleName>Леонидович</middleName></employee><lessonTime>18:45-20:20</lessonTime><lessonType>ЛР</lessonType>\n" +
                "<numSubgroup>0</numSubgroup><studentGroup>550502</studentGroup><subject>ТРиТПО</subject><weekNumber>1</weekNumber></schedule></scheduleModel>"+

                "<scheduleModel><schedule><auditory>512-5</auditory><employee><firstName>Алексей</firstName><id>504389</id>\n" +
                "<lastName>Яночкин</lastName><middleName>Леонидович</middleName></employee><lessonTime>18:45-20:20</lessonTime><lessonType>ЛР</lessonType>\n" +
                "<numSubgroup>0</numSubgroup><studentGroup>550502</studentGroup><subject>ТРиТПО</subject><weekNumber>1</weekNumber></schedule></scheduleModel>"+

                "<scheduleModel><schedule><auditory>512-5</auditory><employee><firstName>Алексей</firstName><id>504389</id>\n" +
                "<lastName>Яночкин</lastName><middleName>Леонидович</middleName></employee><lessonTime>18:45-20:20</lessonTime><lessonType>ЛР</lessonType>\n" +
                "<numSubgroup>0</numSubgroup><studentGroup>550502</studentGroup><subject>ТРиТПО</subject><weekNumber>1</weekNumber></schedule></scheduleModel>"+

                "<scheduleModel><schedule><auditory>512-5</auditory><employee><firstName>Алексей</firstName><id>504389</id>\n" +
                "<lastName>Яночкин</lastName><middleName>Леонидович</middleName></employee><lessonTime>18:45-20:20</lessonTime><lessonType>ЛР</lessonType>\n" +
                "<numSubgroup>0</numSubgroup><studentGroup>550502</studentGroup><subject>ТРиТПО</subject><weekNumber>1</weekNumber></schedule></scheduleModel>"+

                "<scheduleModel><schedule><auditory>512-5</auditory><employee><firstName>Алексей</firstName><id>504389</id>\n" +
                "<lastName>Яночкин</lastName><middleName>Леонидович</middleName></employee><lessonTime>18:45-20:20</lessonTime><lessonType>ЛР</lessonType>\n" +
                "<numSubgroup>0</numSubgroup><studentGroup>550502</studentGroup><subject>ТРиТПО</subject><weekNumber>1</weekNumber></schedule></scheduleModel>"+

                "<scheduleModel><schedule><auditory>512-5</auditory><employee><firstName>Алексей</firstName><id>504389</id>\n" +
                "<lastName>Яночкин</lastName><middleName>Леонидович</middleName></employee><lessonTime>18:45-20:20</lessonTime><lessonType>ЛР</lessonType>\n" +
                "<numSubgroup>0</numSubgroup><studentGroup>550502</studentGroup><subject>ТРиТПО</subject><weekNumber>1</weekNumber></schedule></scheduleModel>"+
                "</scheduleXmlModels>";
    }
    @Test
    public void testGroupScheduleCheck() throws Exception {
        System.out.println("Test ScheduleChecker.groupScheduleCheck");
        expect(HibernateShell.getGroupKeeper()).andReturn(groupsKeeper);
        expect(BsuirRequests.getTimetable((String)anyObject())).andReturn(strXml);
        expect(BsuirRequests.getCurrentWeek()).andReturn("1");
        ScheduleChecker.groupScheduleCheck();
        
        System.out.println("Test success.");
    }
}