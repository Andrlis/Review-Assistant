package Checker;


import Data.Lab;
import Data.Student;
import Data.SubGroup;
import gitAPI.GitInfoClasses.GitCommitInfo.GitCommit;
import gitAPI.GitInfoClasses.GitCommitInfo.GitCommitHistory;
import gitAPI.GitParser;
import gitAPI.GitRequests;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                    lab.getIssuedLad().getInformation().getKeyWord());
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
     * @param student - сткдент для проверки
     * @return - true - есть изменения, false - нет изменений
     * Изенения сохраняются в объекте student
     * если коэффициент = -1 - лабараторная не сдана, -2 - дата и время коммита были изменены(фальсификация)
     */
    static public boolean checkTheChangeInStudentCommits(Student student) {
        boolean flag = false;

        for (Lab currentLab : student.getLabs()) { //цикл по всем выданым лабараторным студента
            if (currentLab.getCoefficient() >= 0)
                continue;

            Date commitDate = Checker.getDateOfTheCommit(student, currentLab);

            if (commitDate == null)
                continue;

            if (commitDate.getTime() < currentLab.getIssuedLad().getLastCheckDateAndTime().getTime()) {
                currentLab.setCoefficient(-2);
            } else {
                flag = true;
                currentLab.setCoefficient(currentLab.getIssuedLad().getCoefficient());
                student.setNumberOfPasses(student.getNumberOfPasses() - 1);
                //ToDo сохранить в бд коэффициент сданной лабы
                if(student.getNumberOfPasses() == 0)
                    break;
            }
        }

        return flag;
    }

    /**
     * алгоритм проверки лабараторных
     * @param subGroups список групп
     */
    static public void checkForCommitsInTheSubGroups(ArrayList<SubGroup> subGroups){
        for(SubGroup currentSubGroup: subGroups) {

            ArrayList<Student> students = null;
            //ToDo выбрать из бд студентов из выбранной подгруппы с задолжностями

            for (Student currentStudent : students) {
                if (Checker.checkTheChangeInStudentCommits(currentStudent)) {
                    //ToDo сохранение в бд колличество долгов
                }
            }
        }
    }
}