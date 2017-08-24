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

    @Before
    public void setUp() throws Exception {
        group = new Group();
        Lecturer lecturer = new Lecturer();
        SubGroup subGroup1 = new SubGroup();
        UniversityClass universityClass = new UniversityClass();
        IssuedLab issuedLab = new IssuedLab();
        Lab lab = new Lab();
        Student student = new Student();
        TestMark testMark = new TestMark();
        LabMark labMark = new LabMark();
        Data.Test.Test test = new Data.Test.Test();

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


    @Ignore
    @Test
    public void testSave() throws Exception {
        System.out.println("Test HibernateShell.save().");
        HibernateShell.save(group);
        GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();
        assertEquals(1, groupsKeeper.getGroupList().size());
        HibernateShell.delete(group);
        System.out.println("Test success.");
    }

    @Test
    public void testAllHibernateShell() throws Exception {
        System.out.println("****************/Test all HibernateShell/****************");
        HibernateShell.save(group);
        GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();
        assertEquals(1, groupsKeeper.getGroupList().size());
        System.out.println("Test HibernateShell.save() success.");
        HibernateShell.delete(group);
        groupsKeeper = HibernateShell.getGroupKeeper();
        assertEquals(0, groupsKeeper.getGroupList().size());
        System.out.println("Test HibernateShell.delete() success.");
    }
}