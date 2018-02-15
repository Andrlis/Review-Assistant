package checker;


import data.group.Group;
import data.group.GroupsKeeper;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.mark.LabMark;
import data.Student;
import exceptions.CheckException;
import exceptions.GitException;
import resources.Hibernate.HibernateCore;
import gitAPI.GitShell;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by kesso on 01.08.17.
 * Проверка репозиториев.
 */
public class Checker {
    private static final Logger logger = Logger.getLogger(Checker.class);

    /**
     * Check issued labs in all groups.
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
                    try {
                        Checker.checkIssuedLab(currentIssuedLab);
                    }catch (CheckException e){
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        logger.info("End check for commits is group.");
    }

    static private void checkStudent(Student student, IssuedLab issuedLab) throws CheckException {
        HibernateCore hibernateCore = HibernateCore.getInstance();
        LabMark labMark = student.getLabMark(issuedLab.getLabDescription());

        Date commitDate;

        try {
            commitDate = GitShell.getDateOfTheCommit(student, issuedLab.getLabDescription());
        }catch (GitException e){
            throw new CheckException(e);
        }

        if (commitDate == null) {
            logger.info("Student " + student.getFulName() + " did not commit a lab.");
        }
        else {
            if (commitDate.before(issuedLab.getDateOfLastRepoCheck())) {               //Дата коммита раньше даты последней проверки. Фальсификация сдачи
                logger.info("Student " + student.getFulName() + " cheated.");

                labMark.setCoefficient(new Double(-2));
                hibernateCore.update(labMark);
            } else if (commitDate.before(issuedLab.getCurrentDeadline().getDate())){
                logger.info("Student " + student.getFulName() + " commit a lab.");

                issuedLab.deleteStudentFromControlList(student);
                labMark.setCoefficient(issuedLab.getCoefficientOfCurrentDeadline());

                hibernateCore.update(labMark);
            }
        }
    }

    static private void checkIssuedLab(IssuedLab issuedLab) throws CheckException{
        logger.info("Check lab number " + issuedLab.getLabDescription().getNumberOfLab());
        Date newDateOfLastRepoCheck = new Date();
        HibernateCore hibernateCore = HibernateCore.getInstance();
        //loop by students who did not pass the lab
        for (Student currentStudent : issuedLab.getStudentControlList()) {
            try {
                Checker.checkStudent(currentStudent, issuedLab);
            }catch(CheckException e){
                throw new CheckException(e);
            }
        }

        //save new date of last lab checking
        logger.info("New date of last repo check : " + newDateOfLastRepoCheck.toString() + ".");
        issuedLab.setDateOfLastRepoCheck(newDateOfLastRepoCheck);
        hibernateCore.update(issuedLab);
    }
}