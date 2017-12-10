package resources.Hibernate;

import data.Student;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.Test;

import java.util.ArrayList;
import java.util.List;

public class StudentHibernateShell {

    static public void deleteStudent(String id){
        Student student = HibernateShell.getStudentById(id);
        HibernateShell.delete(student);
    }

    static public void  NoteStudentPresence(String studentId, String classId){
        HibernateShell.SQLQuery("DELETE FROM absentees WHERE id_class = " + classId + " and id_student = " + studentId);
    }

    static public void NoteStudentAbsent(String stringId, String classId){
        HibernateShell.SQLQuery("INSERT INTO absentees (id_class, id_student) VALUES ("+ classId + ", " + stringId + ");");
    }

    static public boolean saveStudent(String groupNumber, String subGroupNumber, String name, String eMail, String gitURL) {
        Student student = new Student();
        student.setFulName(name);
        student.seteMail(eMail);
        student.setGitURL(gitURL);

        Group group = HibernateShell.getGroupByGroupNumber(groupNumber);
        if(group == null)
            return false;

        SubGroup subGroup = group.getSubGroup(subGroupNumber);
        if(subGroup == null)
            return false;


        subGroup.addStudent(student);

        for(IssuedLab issuedLab : subGroup.getIssuedLabsList()) {
            LabMark labMark = new LabMark();
            labMark.setStudent(student);
            labMark.setIssuedLab(issuedLab);
            student.addLabMark(labMark);
        }
        List<Test> tests = HibernateShell.getTestKeeper().getTestList();
        for(Test test : tests){
            TestMark testMark = new TestMark();
            testMark.setStudent(student);
            testMark.setTest(test);

            student.addTestMark(testMark);
        }

        student.setSubGroup(subGroup);

        HibernateShell.save(student);
        return true;
    }
}
