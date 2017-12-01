package gitAPI;

import data.Lab.Lab;
import data.Student;
import exceptions.GitException;
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
     * Проверка коммита на ключевое слово.
     * @param message - сообщене для проверка
     * @return GirCommitHistory cоответствующий коммиту с заданным сообщением
     */
    static private GitCommitHistory messageCheck(String gitUserName, String gitRepoName, String message) throws GitException {
        logger.info("Start message check.");
        GitRequests git = new GitRequests();
        GitParser parser = new GitParser();

        try {
            ArrayList<GitCommitHistory> commitHistories = null;

            commitHistories = parser.readCommitHistory(git.getCommitList(gitUserName, gitRepoName));

            for (GitCommitHistory selected : commitHistories) {
                if (selected.getCommit().getMessage().equals(message)) {
                    logger.info("End message check. Messages are equals.");
                    return selected;
                }
            }
        }catch(NullPointerException e){
            throw new GitException(e);
        } catch (IOException e) {
            throw new GitException(e);
        }

        logger.info("End message check.");
        return null;
    }

    /**
     * @param lab
     * @return - дату и время коммита
     */
    static public Date getDateOfTheCommit(Student student, Lab lab) throws GitException {
        logger.info("Start get date of the commit.");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GTM"));

        GitCommitHistory answer = null;

        try {
            answer = GitShell.messageCheck(student.getGitUserName(), student.getGitRepoName(),
                    lab.getKeyWord());
        }catch (GitException e){
            throw new GitException(e);
        }

        if (answer != null) {
            Date commitDate = null;
            try {
                commitDate = format.parse(answer.getCommit().getCommitter().getDate().replace("T", ""));
            } catch (ParseException e) {
                throw new GitException(e);
            }
            logger.info("End get date of the commit.");
            return commitDate;
        }
        logger.info("End get date of the commit.");
        return null;
    }
}
