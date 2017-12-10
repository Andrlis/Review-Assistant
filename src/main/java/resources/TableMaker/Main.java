package resources.TableMaker;

import data.Student;
import data.group.GroupsKeeper;
import data.group.SubGroup;
import data.lab.LabsKeeper;
import resources.Hibernate.HibernateShell;
import resources.Hibernate.StudentHibernateShell;
import resources.Hibernate.TestHibernateShell;

import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {

//        SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
//        SubGroup subGroup = new SubGroup();
//
//        UniversityClass universityClass1 = new UniversityClass();
//        universityClass1.setDate(dataFormat.parse("01.01.17 11:40"));
//        universityClass1.setId(1);
//        universityClass1.setSubGroup(subGroup);
//
//        UniversityClass universityClass2 = new UniversityClass();
//        universityClass2.setDate(dataFormat.parse("01.01.17 13:25"));
//        universityClass2.setId(1);
//        universityClass2.setSubGroup(subGroup);
//
//        UniversityClass universityClass3 = new UniversityClass();
//        universityClass3.setDate(dataFormat.parse("02.01.17 08:00"));
//        universityClass3.setId(1);
//        universityClass3.setSubGroup(subGroup);
//
//        UniversityClass universityClass4 = new UniversityClass();
//        universityClass4.setDate(dataFormat.parse("03.01.17 13:25"));
//        universityClass4.setId(1);
//        universityClass4.setSubGroup(subGroup);
//
//        ArrayList<UniversityClass> universityClasses = new ArrayList<UniversityClass>();
//        universityClasses.add(universityClass1);
//        universityClasses.add(universityClass2);
//        universityClasses.add(universityClass3);
//        universityClasses.add(universityClass4);
//
//        subGroup.setUniversityClassesList(universityClasses);
//
//        Student student1 = new Student();
//        student1.setId(1);
//        student1.setFulName("Julia");
//        student1.setSubGroup(subGroup);
//        ArrayList<UniversityClass> universityClassesStudent1 = new ArrayList<UniversityClass>();
//        universityClassesStudent1.add(universityClass1);
//        universityClassesStudent1.add(universityClass4);
//        student1.setMissedUniversityClassesList(universityClassesStudent1);
//        student1.seteMail("123");
//        student1.setGitRepoName("repo");
//        student1.setGitUserName("user");
//
//        Student student2 = new Student();
//        student2.setId(2);
//        student2.setFulName("Julia2");
//        student2.setSubGroup(subGroup);
//        ArrayList<UniversityClass> universityClassesStudent2 = new ArrayList<UniversityClass>();
//        universityClassesStudent2.add(universityClass2);
//        universityClassesStudent2.add(universityClass3);
//        student2.setMissedUniversityClassesList(universityClassesStudent2);
//        student2.seteMail("1234");
//        student2.setGitRepoName("repo2");
//        student2.setGitUserName("user2");
//
//        Student student3 = new Student();
//        student3.setId(3);
//        student3.setFulName("Toje Julia");
//        student3.setSubGroup(subGroup);
//        ArrayList<UniversityClass> universityClassesStudent3 = new ArrayList<UniversityClass>();
//        universityClassesStudent3.add(universityClass1);
//        student3.setMissedUniversityClassesList(universityClassesStudent3);
//        student3.seteMail("12345");
//        student3.setGitRepoName("repo3");
//        student3.setGitUserName("user3");
//
//        ArrayList<Student> students = new ArrayList<Student>();
//        students.add(student1);
//        students.add(student2);
//        students.add(student3);
//
//        subGroup.setStudentsList(students);
//
//        System.out.println(JsonMaker.getJsonSubGroupStudentRedact(subGroup));


        // Added new lab



//       GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();
//
//        Student student = groupsKeeper.getGroupList().get(0).getSubGroup("1").getStudentsList().get(0);
//
//        System.out.println(JsonMaker.getJsonSubGroupClasses(groupsKeeper.getGroupList().get(0).getSubGroup("1")));


        TestHibernateShell.addNextTest("1", "1");

    }
}
