
import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.Lab.LabsKeeper;
import Data.Lecturer.LecturerKeeper;
import Data.Mark.LabMark;
import Data.Student;
import Data.Test.TestKeeper;
import Resources.HibernateShell;

import java.util.ArrayList;

/**
 * Created by kesso on 22.08.17.
 */
public class Test {
    private /*static*/ void main(String[] args) {
        GroupsKeeper groupsKeeper = HibernateShell.getGroupKeeper();


        //saveOrUpdate
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

        /*LabsKeeper labsKeeper = HibernateShell.getLabsKeeper();
        labsKeeper.getLabList();*/

        /*LabMark labMark = groupsKeeper.getGroupList().get(0).getSubGroupList().get(0).getStudentsList().get(0).getLabMarkList().get(0);
        labMark.setCoefficient(new Double(0.5));
        labMark.setMark(2);
        HibernateShell.update(labMark);*/

       /* Student student = groupsKeeper.getGroupList().get(0).getSubGroupList().get(0).getStudentsList().get(0);

        LabMark labMark = student.getLabMarkList().get(0);*/


        //Data.Test.Test test = groupsKeeper.getGroupList().get(0).getSubGroupList().get(0).getStudentsList().get(0).getTestMarkList().get(0).getTest();


        //HibernateShell.delete(groupsKeeper.getGroupList().get(0));

        //TestKeeper testKeeper = HibernateShell.getTestKeeper();

        //LecturerKeeper lecturerKeeper = HibernateShell.getLecturerKeeper();

        return;
    }
}
