
import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.Lab.LabsKeeper;
import Data.Student;
import Resources.HibernateShell;

import java.util.ArrayList;

/**
 * Created by kesso on 22.08.17.
 */
public class Test {
    public static void main(String[] args) {
        GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();

        /*Group group = new Group();
        group.setNumberOfGroup("550503");
        group.setScheduleApiGroupNumber("550503");
        group.setAmountOfTest(0);
        SubGroup subGroup1 = new SubGroup();
        subGroup1.setLecturer(groupsKeeper.getGroupList().get(0).getSubGroupList().get(0).getLecturer());
        subGroup1.setGroup(group);
        subGroup1.setUniversityClassesList(null);
        subGroup1.setSubGroupNumber("1");
        subGroup1.setIssuedLabsList(null);
        subGroup1.setStudentsList(null);
        SubGroup subGroup2 = new SubGroup();
        subGroup2.setLecturer(groupsKeeper.getGroupList().get(0).getSubGroupList().get(0).getLecturer());
        subGroup2.setGroup(group);
        subGroup2.setUniversityClassesList(null);
        subGroup2.setSubGroupNumber("2");
        subGroup2.setIssuedLabsList(null);
        subGroup2.setStudentsList(null);
        group.getSubGroupList().add(subGroup1);
        group.getSubGroupList().add(subGroup2);

        HibernateShell.saveOrUpdate(group);*/

       /*Student student = new Student();
        student.setFulName("Runova Julia");
        student.setGitRepoName("123");
        student.setGitUserName("321");
        student.seteMail("234");

        HibernateShell.addStudent(student,groupsKeeper.getGroupList().get(0).getSubGroupList().get(0));
        */

        /*LabsKeeper labsKeeper = HibernateShell.getLabsKeeper();
        labsKeeper.getLabList();*/
        return;
    }
}
