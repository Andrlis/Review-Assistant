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
     * @param groupsKeeper
     */
    static public void checkForCommitsInGroups(GroupsKeeper groupsKeeper) throws CheckException{
        logger.info("Start check for commits of all the groups.");

        //loop by group
        for (Group currentGroup : groupsKeeper.getGroupList()) {
            logger.info("Check commits of " + currentGroup.toString());
            RepositoryChecker.checkForCommitsInGroup(currentGroup);
        }
        logger.info("End check for commits in all the groups.");
    }

    static public void checkForCommitsInGroup(Group group) throws CheckException{
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
            //Почему нет updateIssuedLab? Ведь список редактируется. в предыдущем методе

        }

        logger.info("End check for commits is group.");
    }

    static private void checkIssuedLab(IssuedLab issuedLab) throws CheckException{
        logger.info("Check lab number " + issuedLab.toString());
        Date newDateOfLastRepoCheck = new Date();
        boolean changeDeadlineFlag = false;

        //loop by students who did not pass the lab
        for (Student currentStudent : issuedLab.getStudentControlList()) {
            try {
                if (RepositoryChecker.checkStudent(currentStudent, issuedLab))
                    changeDeadlineFlag = true;
            }catch(CheckException e){
                throw new CheckException(e);
            }
        }

        //Где-то здесь надо поменять дедлайн и новый коэффициент

        //save new date of last lab checking
        logger.info("New date of last repo check : " + newDateOfLastRepoCheck.toString() + ".");
        issuedLab.setDateOfLastRepoCheck(newDateOfLastRepoCheck);
        HibernateShell.update(issuedLab);
    }

    /**
     *
     * @param student
     * @param issuedLab
     * @return true if deadline shoul be changed or false if not
     * @throws CheckException
     */

    static private boolean checkStudent(Student student, IssuedLab issuedLab) throws CheckException {
        LabMark labMark = student.getLabMark(issuedLab.getLabDescription());

        Date commitDate;

        //Check if there link to github repository
        if (student.getGitURL().equals(""))
            return false;

        try {
            commitDate = GitShell.getDateOfTheCommit(student, issuedLab.getLabDescription());
        } catch (GitException e) {
            throw new CheckException(e);
        }

        if (commitDate == null) {
            logger.info("Student " + student.toString() + " did not commit a lab.");
        }

        double coefficient = issuedLab.getCoefficientOfCommit(commitDate);

        if (coefficient == IssuedLab.CHANGE_DEADLINE) {
            return true;
        } else {

            if (coefficient != IssuedLab.STUDENT_CHEAT)
                issuedLab.deleteStudentFromControlList(student);

            labMark.setCoefficient(coefficient);
            HibernateShell.update(labMark);
        }
        return false;
    }
}