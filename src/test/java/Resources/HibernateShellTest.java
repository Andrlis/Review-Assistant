package Resources;

import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.Lab.LabsKeeper;
import Data.Lecturer;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HibernateShellTest {
    private Group group;

    @Before
    public void setUp() throws Exception {
        Lecturer lecturer = new Lecturer();
        lecturer.setFullName("Иванов Иван Иваноыич");

        group = new Group();
        group.setNumberOfGroup("550503");
        group.setScheduleApiGroupNumber("550503");
        group.setAmountOfTest(0);
        SubGroup subGroup1 = new SubGroup();
        subGroup1.setLecturer(lecturer);
        subGroup1.setGroup(group);
        subGroup1.setUniversityClassesList(null);
        subGroup1.setSubGroupNumber("1");
        subGroup1.setIssuedLabsList(null);
        subGroup1.setStudentsList(null);
        SubGroup subGroup2 = new SubGroup();
        subGroup2.setLecturer(null);
        subGroup2.setGroup(group);
        subGroup2.setUniversityClassesList(null);
        subGroup2.setSubGroupNumber("2");
        subGroup2.setIssuedLabsList(null);
        subGroup2.setStudentsList(null);
        
        lecturer.setSubGroupList(Arrays.asList(subGroup1,subGroup2));
        group.getSubGroupList().add(subGroup1);
        group.getSubGroupList().add(subGroup2);
    }


    @Test
    public void testSave() throws Exception {
        System.out.println("Test HibernateShell.save().");
        try{
            HibernateShell.save(group);
            GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();
            assertEquals(1, groupsKeeper.getGroupList().size());
        } finally {
            HibernateShell.delete(group);
        }
        System.out.println("Test success.");
    }

    @Test
    public void testGetGroupKeeper() throws Exception {
        System.out.println("Test HibernateShell.getGroupKeeper().");
        GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();
        assertEquals(0, groupsKeeper.getGroupList().size());
        System.out.println("Test success.");
    }

    @Test
    public void getLabsKeeper() throws Exception {
        System.out.println("Test HibernateShell.getLabsKeeper().");
        LabsKeeper labsKeeper = HibernateShell.getLabsKeeper();
        assertEquals(0, labsKeeper.getLabList().size());
        System.out.println("Test success.");
    }

    @Test
    public void testGetNumberOfTests() throws Exception {
        System.out.println("Test HibernateShell.getNumberOfTests().");

        System.out.println("Test success.");
    }

    @Test
    public void teatGetNumberOfLab() throws Exception {
        System.out.println("Test HibernateShell.getNumberOfLab().");

        System.out.println("Test success.");
    }

    @Test
    public void testUpdate() throws Exception {
        System.out.println("Test HibernateShell.update().");

        System.out.println("Test success.");
    }
}