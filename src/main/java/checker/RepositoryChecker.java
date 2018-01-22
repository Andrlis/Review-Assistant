package checker;


import data.group.Group;
import data.group.GroupsKeeper;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.mark.LabMark;
import data.Student;
import exceptions.CheckException;
import exceptions.GitException;
import resources.Hibernate.HibernateShell;
import gitAPI.GitShell;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by kesso on 01.08.17.
 * Проверка репозиториев.
 */
public class RepositoryChecker {
    private static final Logger logger = Logger.getLogger(RepositoryChecker.class);

    /**
     * Check issued labs in all groups.
     *
     * @param groupsKeeper
     */
    static public void checkForCommitsInGroups(GroupsKeeper groupsKeeper) throws CheckException {
        logger.info("Start check for commits of all the groups.");

        //loop by group
        for (Group currentGroup : groupsKeeper.getGroupList()) {
            logger.info("Check commits of " + currentGroup.toString());
            RepositoryChecker.checkForCommitsInGroup(currentGroup);
        }
        logger.info("End check for commits in all the groups.");
    }

    static public void checkForCommitsInGroup(Group group) throws CheckException {
        logger.info("Start check for commits of " + group.toString());
        //loop by subgroup
        for (SubGroup currentSubGroup : group.getSubGroupList()) {
            logger.info("Check commits of " + currentSubGroup.toString());
            RepositoryChecker.checkForCommitsInSubgroup(currentSubGroup);
        }
        logger.info("End check for commits in group.");
    }

    static public void checkForCommitsInSubgroup(SubGroup subGroup) throws CheckException {
        logger.info("Start check for commits of " + subGroup.toString());
        //loop by issued lab for subgroup

        for (IssuedLab currentIssuedLab : subGroup.getIssuedLabsList()) {

            RepositoryChecker.checkIssuedLab(currentIssuedLab);
        }

        logger.info("End check for commits is group.");
    }

    static private void checkIssuedLab(IssuedLab issuedLab) throws CheckException {
        logger.info("Check lab number " + issuedLab.toString());
        Date newDateOfLastRepoCheck = new Date();

        //loop by students who did not pass the lab
        for (Student currentStudent : issuedLab.getStudentControlList()) {
            try {
                RepositoryChecker.checkStudent(currentStudent, issuedLab);
            } catch (CheckException e) {
                throw new CheckException(e);
            }
        }

        //save new date of last lab checking
        logger.info("New date of last repo check : " + newDateOfLastRepoCheck.toString() + ".");
        issuedLab.setDateOfLastRepoCheck(newDateOfLastRepoCheck);
        HibernateShell.update(issuedLab);
    }

    /**
     * @param student
     * @param issuedLab
     * @return true if deadline shoul be changed or false if not
     * @throws CheckException
     */

    static private void checkStudent(Student student, IssuedLab issuedLab) throws CheckException {
        LabMark labMark = student.getLabMark(issuedLab.getLabDescription());
        Date    commitDate;

        //Check if there link to github repository
        if (student.getGitURL().equals(""))
            return;

        try {
            commitDate = GitShell.getDateOfTheCommit(student.getGitUserName(),
                    student.getGitRepoName(),
                    issuedLab.getLabDescription().getKeyWord());
        } catch (GitException e) {
            throw new CheckException(e);
        }

        if (commitDate != null) {
            double coefficient = issuedLab.getCoefficientOfCommit(commitDate);
            labMark.setCoefficient(coefficient);
            HibernateShell.update(labMark);
            logger.info("Student " + student + " committed a lab with coefficient " + coefficient);
        } else {
            logger.info("Student " + student + " did not commit a lab.");
        }
    }
}