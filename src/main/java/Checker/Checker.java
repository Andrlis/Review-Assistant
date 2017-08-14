package Checker;


import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.Lab.IssuedLab;
import Data.Mark.LabMark;
import Data.Student;
import gitAPI.GitShell;

import java.util.Date;

/**
 * Created by kesso on 01.08.17.
 */
public class Checker {

    /**
     *
     * @param groupsKeeper
     */
    static public void checkForCommitsInGroups(GroupsKeeper groupsKeeper) {
        //loop by group
        for(Group currentGroup : groupsKeeper.getGroupList()) {
            //loop by subgroup
            for(SubGroup currentSubGroup : currentGroup.getSubGroupList()) {
                //loop by issued lab for subgroup
                for(IssuedLab currentIssuedLab : currentSubGroup.getIssuedLabsList()) {
                    Date newDateOfLastRepoCheck = new Date();
                    //loop by students who did not pass the lab
                    for(Student currentStudent : currentIssuedLab.getStudentControlList()) {
                        LabMark labMark = currentStudent.getLabMark(currentIssuedLab.getLabDescription());

                        if(labMark.getCoefficient() >= 0)
                            continue;

                        Date commitDate = GitShell.getDateOfTheCommit(currentStudent, currentIssuedLab.getLabDescription());
                        if(commitDate == null)
                            continue;

                        if(commitDate.getTime() < currentIssuedLab.getDateOfLastRepoCheck().getTime()) {
                            labMark.setCoefficient(new Double(-2));
                        } else {
                            currentIssuedLab.deleteStudentFromControlList(currentStudent);
                            labMark.setCoefficient(currentIssuedLab.getCoefficientOfCurrentDeadline());
                            //TODO save change for database
                        }
                    }
                    //save new date of last lab checking
                    currentIssuedLab.setDateOfLastRepoCheck(newDateOfLastRepoCheck);
                }
            }
        }
    }
}