package Checker;



import Data.Group.Group;
import Data.Group.GroupsKeeper;
import Data.Group.SubGroup;
import Data.Lab.IssuedLab;
import Data.Lab.Lab;
import Data.Mark.LabMark;
import Data.Student;
import gitAPI.GitInfoClasses.GitCommitInfo.GitCommit;
import gitAPI.GitInfoClasses.GitCommitInfo.GitCommitHistory;
import gitAPI.GitParser;
import gitAPI.GitRequests;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by kesso on 01.08.17.
 */
public class Checker {
    /**
     * @param json
     * @param message - сообщене для проверка
     * @return GirCommitHistory cоответствующий коммиту с заданным сообщением
     */
    static private GitCommitHistory messageCheck(String json, String message) {
        GitParser parser = new GitParser();

        ArrayList<GitCommitHistory> commitHistories = null;

        try {
            commitHistories = parser.readCommitHistory(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (GitCommitHistory selected : commitHistories) {
            if (selected.getCommit().getMessage().equals(message))
                return selected;
        }

        return null;
    }

    /**
     * @param commitHistory GitCommitHistory соответсвтвующий коммиту для проверки
     * @param data          дата сдачи в формате гггг-мм-дд
     * @return разница между датой коммита и датой сдачи в днях(может быть отрицательной)
     */
    static private int dataCheck(GitCommitHistory commitHistory, String data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date commitDate = null;
        Date requiredDate = null;
        try {
            requiredDate = format.parse(data);
            commitDate = format.parse(commitHistory.getCommit().getCommitter().getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) (commitDate.getTime() - requiredDate.getTime()) / 1000 / 60 / 60 / 24;
    }

    /**
     * @param json
     * @param message - сообщение для проверки
     * @param data    - дата сдачи
     * @return - на сколько просрочена лабораторная ( -1 если нет нужного коммита)
     */
    static public int getNumberOfDaysPenalty(String json, String message, String data) {
        GitCommitHistory commitHistory = Checker.messageCheck(json, message);

        if (commitHistory == null)
            return -1;

        int difference = Checker.dataCheck(commitHistory, data);
        if (difference < 0)
            return 0;
        return difference;
    }

    /**
     * @param lab
     * @return - дату и время коммита
     */
    static public Date getDateOfTheCommit(Student student, Lab lab) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GTM"));


        GitRequests git = new GitRequests();

        GitCommitHistory answer = null;
        try {
            answer = Checker.messageCheck(
                    git.getCommitList(student.getGitUserName(), student.getGitRepoName()),
                    lab.getKeyWord());
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (answer != null) {
            Date commitDate = null;
            try {
                commitDate = format.parse(answer.getCommit().getCommitter().getDate().replace("T", ""));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return commitDate;
        }
        return null;
    }

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

                        Date commitDate = Checker.getDateOfTheCommit(currentStudent, currentIssuedLab.getLabDescription());
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