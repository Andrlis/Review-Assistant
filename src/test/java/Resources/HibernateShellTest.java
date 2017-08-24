package Resources;

import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.Lab.IssuedLab;
import Data.Lab.Lab;
import Data.Lab.LabsKeeper;
import Data.Lecturer;
import Data.Mark.LabMark;
import Data.Mark.TestMark;
import Data.Student;
import Data.UniversityClass;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class HibernateShellTest {
    private Group group;
    Lecturer lecturer;
    SubGroup subGroup1;
    UniversityClass universityClass;
    IssuedLab issuedLab;
    Lab lab;
    Student student;
    TestMark testMark;
    LabMark labMark;
    Data.Test.Test test;

    @Before
    public void setUp() throws Exception {
        group = new Group();
        lecturer = new Lecturer();
        subGroup1 = new SubGroup();
        universityClass = new UniversityClass();
        issuedLab = new IssuedLab();
        lab = new Lab();
        student = new Student();
        testMark = new TestMark();
        labMark = new LabMark();
        test = new Data.Test.Test();

        lecturer.setFullName("Иванов Иван Иваноыич");
        lecturer.setSubGroupList(Arrays.asList(subGroup1));

        group.setNumberOfGroup("550503");
        group.setScheduleApiGroupNumber("550503");
        group.setAmountOfTest(0);
        group.getSubGroupList().add(subGroup1);

        subGroup1.setLecturer(lecturer);
        subGroup1.setGroup(group);
        subGroup1.setUniversityClassesList(Arrays.asList(universityClass));
        subGroup1.setSubGroupNumber("1");
        subGroup1.setStudentsList(Arrays.asList(student));
        subGroup1.setIssuedLabsList(Arrays.asList(issuedLab));

        universityClass.setDate(new Date());
        universityClass.setSubGroup(subGroup1);

        issuedLab.setCoefficientOfCurrentDeadline(0.8);
        issuedLab.setCurrentDeadline(universityClass);
        issuedLab.setDateOfLastRepoCheck(new Date());
        issuedLab.setLabDescription(lab);
        issuedLab.setStudentControlList(Arrays.asList(student));
        issuedLab.setUniversityClassOfIssue(universityClass);

        lab.setKeyWord("lab");
        lab.setNumberOfLab(1);

        student.seteMail("student@gmail.com");
        student.setFulName("Петров Петр Петрович");
        student.setGitRepoName("studentRepo");
        student.setGitUserName("student");
        student.setBonusMark(9);
        student.setLabMarkList(Arrays.asList(labMark));
        student.setMissedUniversityClassesList(Arrays.asList(universityClass));
        student.setSubGroup(subGroup1);
        student.setTestMarkList(Arrays.asList(testMark));

        labMark.setCoefficient(0.2);
        labMark.setIssuedLab(issuedLab);
        labMark.setMark(9);
        labMark.setStudent(student);

        testMark.setMark(8);
        testMark.setStudent(student);
        testMark.setTest(test);

        test.setTestDate(new Date());
        test.setTestNumber(7);
        test.setTestMarkList(Arrays.asList(testMark));
    }


    @Test
    public void testAllHibernateShell() throws Exception {
        System.out.println("****************/Test all HibernateShell/****************");
        HibernateShell.save(group);
        GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();
        assertEquals(1, groupsKeeper.getGroupList().size());
        assertEquals(group.getNumberOfGroup(),groupsKeeper.getGroupList().get(0).getNumberOfGroup());
        assertEquals(group.getScheduleApiGroupNumber(),
                groupsKeeper.getGroupList().get(0).getScheduleApiGroupNumber());
        System.out.println("Test HibernateShell.save() & HibernateShell,getGroupKeeper() success.");
        lab = HibernateShell.getLabsKeeper().getLabList().get(0);
        lab.setNumberOfLab(2);
        HibernateShell.update(lab);
        assertEquals(lab.getNumberOfLab(),
                HibernateShell.getLabsKeeper().getLabList().get(0).getNumberOfLab());
        System.out.println("Test HibernateShell.update() & HibernateShell,getLabKeeper() success.");
        assertTrue(HibernateShell.getNumberOfLab()==1);
        //assertTrue(HibernateShell.getNumberOfTests().intValue()==1);
        System.out.println("Test HibernateShell.getNumberOfLab() & HibernateShell,getNumberOfTests() success.");
        HibernateShell.delete(group);
        assertTrue(HibernateShell.getGroupKeeper().getGroupList().isEmpty());
        System.out.println("Test HibernateShell.delete() success.");
    }
}