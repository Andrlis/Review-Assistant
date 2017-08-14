package gitAPI;

import Data.Lab.Lab;
import Data.Student;
import gitAPI.GitInfoClasses.GitCommitInfo.GitCommitHistory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by kesso on 14.08.17.
 */
public class GitShell {
    /**
     * @param message - сообщене для проверка
     * @return GirCommitHistory cоответствующий коммиту с заданным сообщением
     */
    static private GitCommitHistory messageCheck(String gitUserName, String gitRepoName, String message) {
        GitRequests git = new GitRequests();
        GitParser parser = new GitParser();

        ArrayList<GitCommitHistory> commitHistories = null;

        try {
            commitHistories = parser.readCommitHistory(git.getCommitList(gitUserName, gitRepoName));
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
     * @param lab
     * @return - дату и время коммита
     */
    static public Date getDateOfTheCommit(Student student, Lab lab) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GTM"));

        GitCommitHistory answer = null;

        answer = GitShell.messageCheck(student.getGitUserName(), student.getGitRepoName(),
                lab.getKeyWord());

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
}
