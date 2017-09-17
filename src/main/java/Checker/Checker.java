package Checker;


import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.Lab.IssuedLab;
import Data.Mark.LabMark;
import Data.Student;
import Resources.HibernateShell;
import gitAPI.GitShell;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by kesso on 01.08.17.
 */
public class Checker {
    private static final Logger logger = Logger.getLogger(Checker.class);
    /**
     * @param groupsKeeper
     */
    static public void checkForCommitsInGroups(GroupsKeeper groupsKeeper) {
        logger.info("Start check for commits is group.");
        //loop by group
        for (Group currentGroup : groupsKeeper.getGroupList()) {
            logger.info("Check commits of " + currentGroup.getNumberOfGroup() + " group.");
            //loop by subgroup
            for (SubGroup currentSubGroup : currentGroup.getSubGroupList()) {
                logger.info("Check commits of " + currentSubGroup.getSubGroupNumber() + " subgroup.");
                //loop by issued lab for subgroup
                for (IssuedLab currentIssuedLab : currentSubGroup.getIssuedLabsList()) {
                    logger.info("Check lab number " + currentIssuedLab.getLabDescription().getNumberOfLab());
                    Date newDateOfLastRepoCheck = new Date();
                    //loop by students who did not pass the lab
                    for (Student currentStudent : currentIssuedLab.getStudentControlList()) {
                        LabMark labMark = currentStudent.getLabMark(currentIssuedLab.getLabDescription());

                        Date commitDate = GitShell.getDateOfTheCommit(currentStudent, currentIssuedLab.getLabDescription());
                        if (commitDate == null) {
                            logger.info("Student " + currentStudent.getFulName() + " did not commit a lab.");
                            continue;
                        }

                        if (commitDate.getTime() < currentIssuedLab.getDateOfLastRepoCheck().getTime()) {
                            logger.info("Student " + currentStudent.getFulName() + " cheated.");
                            labMark.setCoefficient(new Double(-2));
                            HibernateShell.update(labMark);
                        } else {
                            if (commitDate.getTime() < currentIssuedLab.getCurrentDeadline().getDate().getTime()) {
                                logger.info("Student " + currentStudent.getFulName() + " commit a lab.");
                                currentIssuedLab.deleteStudentFromControlList(currentStudent);
                                labMark.setCoefficient(currentIssuedLab.getCoefficientOfCurrentDeadline());
                                HibernateShell.update(labMark);
                            }
                        }
                    }
                    //save new date of last lab checking
                    logger.info("New date of last repo check : " + newDateOfLastRepoCheck.toString() + ".");
                    currentIssuedLab.setDateOfLastRepoCheck(newDateOfLastRepoCheck);
                    HibernateShell.update(currentIssuedLab);
                }
            }
        }
        logger.info("Ebd check for commits is group.");
    }
}