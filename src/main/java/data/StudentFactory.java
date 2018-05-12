package data;

import dao.DataBaseCore;
import dao.DataBaseCoreInterface;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.mark.LabMark;
import data.mark.TestMark;
import data.test.Test;
import exceptions.DataBaseQueryException;
import logics.GroupLogic;

public class StudentFactory {
    DataBaseCoreInterface dataBaseCore;
    GroupLogic groupLogic;

    public StudentFactory() {
        dataBaseCore = DataBaseCore.getInstance();
        groupLogic = new GroupLogic();
    }

    public StudentFactory(DataBaseCoreInterface core) {
        dataBaseCore = core;
        groupLogic = new GroupLogic(core);
    }

    public Student createInDataBaseStudent(String studentName, String eMail, String gitRepo,
                                           String groupNumber, String subGroupNumber) throws DataBaseQueryException {

        Student student = new Student();

        student.setFulName(studentName);
        student.seteMail(eMail);
        student.setGitURL(gitRepo);

        Group group = groupLogic.getByNumber(groupNumber);
        SubGroup subGroup = group.getSubGroup(subGroupNumber);

        student.setSubGroup(subGroup);

        addMarksToStudent(student, subGroup);

        return student;
    }

    public void addMarksToStudent(Student student, SubGroup subGroup) throws DataBaseQueryException {
        for (IssuedLab issuedLab : subGroup.getIssuedLabsList()) {
            LabMark labMark = new LabMark();
            labMark.setIssuedLab(issuedLab);

            issuedLab.getStudentControlList().add(student);

            labMark.setStudent(student);

            student.addLabMark(labMark);
        }

        for (Object o : dataBaseCore.getAll(Test.class)) {
            Test test = (Test) o;
            TestMark testMark = new TestMark();
            testMark.setTest(test);
            testMark.setStudent(student);

            student.addTestMark(testMark);
        }
    }
}
