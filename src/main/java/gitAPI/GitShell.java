package gitAPI;

import data.Lab.Lab;
import data.Student;
import gitAPI.GitInfoClasses.GitCommitInfo.GitCommitHistory;
import org.apache.log4j.Logger;

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
    private static final Logger logger = Logger.getLogger(GitShell.class);
    /**
     * @param message - сообщене для проверка
     * @return GirCommitHistory cоответствующий коммиту с заданным сообщением
     */
    static private GitCommitHistory messageCheck(String gitUserName, String gitRepoName, String message) {
        logger.info("Start message check.");
        GitRequests git = new GitRequests();
        GitParser parser = new GitParser();

        ArrayList<GitCommitHistory> commitHistories = null;

        try {
            commitHistories = parser.readCommitHistory(git.getCommitList(gitUserName, gitRepoName));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        for (GitCommitHistory selected : commitHistories) {
            if (selected.getCommit().getMessage().equals(message))
                logger.info("End message check.");
                return selected;
        }

        logger.info("End message check.");
        return null;
    }

    /**
     * @param lab
     * @return - дату и время коммита
     */
    static public Date getDateOfTheCommit(Student student, Lab lab) {
        logger.info("Start get date of the commit.");
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
                logger.error(e.getMessage());
            }
            logger.info("End get date of the commit.");
            return commitDate;
        }
        logger.info("End get date of the commit.");
        return null;
    }
}
