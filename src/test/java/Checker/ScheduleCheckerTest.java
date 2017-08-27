package Checker;

import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Resources.HibernateShell;
import bsuirAPI.BsuirRequests;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.expect;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HibernateShell.class)
public class ScheduleCheckerTest {
    private GroupsKeeper groupsKeeper;
    private Group group;
    private String strXml;

    @Before
    public void setUp(){
        group = new Group();

        group.setNumberOfGroup("550503");
        group.setScheduleApiGroupNumber("550503");
        group.setAmountOfTest(0);
        groupsKeeper = new GroupsKeeper(Arrays.asList(group));
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
        SessionFactory mockSessionFactory = Mockito.mock(org.hibernate.SessionFactory.class);
        Configuration mockConfig = Mockito.mock(Configuration.class);
        Mockito.when(mockConfig.buildSessionFactory()).thenReturn(mockSessionFactory);
        PowerMockito.mockStatic(HibernateShell.class);

        expect(HibernateShell.getGroupKeeper()).andReturn(groupsKeeper);
        expect(BsuirRequests.getTimetable((String)anyObject())).andReturn(strXml);
        expect(BsuirRequests.getCurrentWeek()).andReturn("1");
        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        ScheduleChecker.groupScheduleCheck();
        System.out.println("Test success.");
    }
}