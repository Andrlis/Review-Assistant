package resources.TableMaker;

import data.Student;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.Test;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//        Group group;
//        Lecturer lecturer;
//        SubGroup subGroup1;
//        UniversityClass universityClass;
//        IssuedLab issuedLab;
//        Lab lab;
//        Student student;
//        TestMark testMark;
//        LabMark labMark;
//        data.test.Test test;
//
//
//        group = new Group();
//        lecturer = new Lecturer();
//        subGroup1 = new SubGroup();
//        universityClass = new UniversityClass();
//        issuedLab = new IssuedLab();
//        lab = new Lab();
//        student = new Student();
//        testMark = new TestMark();
//        labMark = new LabMark();
//        test = new data.test.Test();
//
//        lecturer.setFullName("Иванов Иван Иваноыич");
//        lecturer.setSubGroupList(Arrays.asList(subGroup1));
//
//        group.setNumberOfGroup("550503");
//        group.setScheduleApiGroupNumber("550503");
//        group.setAmountOfTest(0);
//        group.getSubGroupList().add(subGroup1);
//
//        subGroup1.setLecturer(lecturer);
//        subGroup1.setGroup(group);
//        subGroup1.setUniversityClassesList(Arrays.asList(universityClass));
//        subGroup1.setSubGroupNumber("1");
//        subGroup1.setStudentsList(Arrays.asList(student));
//        subGroup1.setIssuedLabsList(Arrays.asList(issuedLab));
//
//        universityClass.setDate(new Date());
//        universityClass.setSubGroup(subGroup1);
//
//        issuedLab.setCoefficientOfCurrentDeadline(0.8);
//        issuedLab.setCurrentDeadline(universityClass);
//        issuedLab.setDateOfLastRepoCheck(new Date());
//        issuedLab.setLabDescription(lab);
//        issuedLab.setStudentControlList(Arrays.asList(student));
//        issuedLab.setUniversityClassOfIssue(universityClass);
//
//        lab.setKeyWord("lab");
//        lab.setNumberOfLab(1);
//
//        student.seteMail("student@gmail.com");
//        student.setFulName("Петров Петр Петрович");
//        student.setGitRepoName("studentRepo");
//        student.setGitUserName("student");
//        student.setBonusMark(9);
//        student.setLabMarkList(Arrays.asList(labMark));
//        student.setMissedUniversityClassesList(Arrays.asList(universityClass));
//        student.setSubGroup(subGroup1);
//        student.setTestMarkList(Arrays.asList(testMark));
//
//        labMark.setCoefficient(0.2);
//        labMark.setIssuedLab(issuedLab);
//        labMark.setMark(9);
//        labMark.setStudent(student);
//
//        testMark.setMark(8);
//        testMark.setStudent(student);
//        testMark.setTest(test);
//
//        test.setTestDate(new Date());
//        test.setTestNumber(7);
//        test.setTestMarkList(Arrays.asList(testMark));
//
//
//
//        HibernateShell.save(group);
//
//        HibernateShell.updateLabMark(labMark.getId(), 9);
//
//        System.out.println("END");
//
//        ArrayList<Map<String, Object>> a = new ArrayList<Map<String, Object>>();




        Student student = new Student();
        student.setFulName("Pavel Kesso");
        student.setId(1);
        //student.setBonusMark(3);

        Lab lab1 = new Lab();
        lab1.setNumberOfLab(new Integer(1));
        Lab lab2 = new Lab();
        lab2.setNumberOfLab(new Integer(2));
        Lab lab3 = new Lab();
        lab3.setNumberOfLab(new Integer(3));

        IssuedLab il1 = new IssuedLab();
        il1.setLabDescription(lab1);
        IssuedLab il2 = new IssuedLab();
        il2.setLabDescription(lab2);
        IssuedLab il3 = new IssuedLab();
        il3.setLabDescription(lab3);


        LabMark l1 = new LabMark();
        l1.setId(1);
        //l1.setCoefficient(new Double(0.1));
        l1.setMark(new Integer(-1));
        l1.setIssuedLab(il1);

        LabMark l2 = new LabMark();
        l2.setId(2);
        l2.setCoefficient(new Double(-1));
        //l2.setMark(new Integer(10));
        l2.setIssuedLab(il2);

        LabMark l3 = new LabMark();
        l3.setId(3);
        l3.setCoefficient(new Double(-1));
        l3.setMark(new Integer(-1));
        l3.setIssuedLab(il3);


        ArrayList<LabMark> labMarks = new ArrayList<LabMark>();
        labMarks.add(l1);
        labMarks.add(l2);
        labMarks.add(l3);

        Test test = new Test();
        test.setTestNumber(7);

        TestMark testMark = new TestMark();
        testMark.setId(1);
        testMark.setMark(8);
        testMark.setStudent(student);
        testMark.setTest(test);

        ArrayList<TestMark> testMarks = new ArrayList<TestMark>();
        testMarks.add(testMark);
        student.setTestMarkList(testMarks);


        student.setLabMarkList(labMarks);

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(student);
        students.add(student);
        students.add(student);



        SubGroup subGroup = new SubGroup();
        subGroup.setStudentsList(students);


        subGroup.setStudentsList(new ArrayList<Student>());


        System.out.println(JsonMaker.getJsonSubGroupMarks(subGroup));


    }
}
