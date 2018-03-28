package resources.Hibernate;

import data.Student;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.Test;

import java.util.List;

public class StudentHibernateShell {

    private HibernateCore hibernateCore;

    public StudentHibernateShell() {
        hibernateCore = HibernateCore.getInstance();
    }

    public void deleteStudent(String id) throws HibernateShellQueryException {
        Student student = hibernateCore.getStudentById(id);
        hibernateCore.delete(student);
    }

    public void noteStudentPresence(String studentId,
                                    String classId) throws HibernateShellQueryException {
        hibernateCore.SQLQuery("DELETE FROM absentees WHERE id_class = " + classId + " and id_student = " + studentId);
    }

    public void noteStudentAbsent(String stringId,
                                  String classId) throws HibernateShellQueryException {
        hibernateCore.SQLQuery("INSERT INTO absentees (id_class, id_student) VALUES (" + classId + ", " + stringId + ");");
    }

    public boolean insertStudent(String groupNumber, String subGroupNumber, String name, String eMail, String gitURL) throws HibernateShellQueryException {
        Student student = new Student();
        student.setFulName(name);
        student.seteMail(eMail);
        student.setGitURL(gitURL);
        student.setBonusMark(-1);

        Group group = hibernateCore.getGroupByGroupNumber(groupNumber);
        if (group == null)
            return false;

        SubGroup subGroup = group.getSubGroup(subGroupNumber);
        if (subGroup == null)
            return false;


        subGroup.addStudent(student);

        for (IssuedLab issuedLab : subGroup.getIssuedLabsList()) {
            LabMark labMark = new LabMark();
            labMark.setStudent(student);
            labMark.setIssuedLab(issuedLab);
            student.addLabMark(labMark);
        }
        List<Test> tests = hibernateCore.getTestKeeper().getTestList();
        for (Test test : tests) {
            TestMark testMark = new TestMark();
            testMark.setStudent(student);
            testMark.setTest(test);

            student.addTestMark(testMark);
        }

        student.setSubGroup(subGroup);

        hibernateCore.save(student);
        return true;
    }

    public boolean updateStudent(String id, String name, String eMail, String gitURL) throws HibernateShellQueryException {
        Student student = hibernateCore.getStudentById(id);

        if (student == null)
            return false;

        student.setFulName(name);
        student.seteMail(eMail);
        student.setGitURL(gitURL);

        hibernateCore.update(student);

        return true;
    }

    public Student getStudetById(String id){
        Student student;

        try {
            student = hibernateCore.getStudentById(Integer.getInteger(id));
        } catch (HibernateShellQueryException e) {
            return null;
        }

        return student;
    }
}
