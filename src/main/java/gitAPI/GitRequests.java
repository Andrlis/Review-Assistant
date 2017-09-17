package gitAPI;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Andrey on 12.07.2017.
 */
public class GitRequests {
    private static final Logger logger = Logger.getLogger(GitRequests.class);
    private URL gitURL;
    private URLConnection connection;
    private BufferedReader in;

    /**
     * Get repository info.
     *
     * @param userName
     * @param repoName
     * @return
     * @throws IOException
     */
    public String getRepoInfo(String userName, String repoName) throws IOException {
        logger.info("Start get repo info.");
        String response = null;

        gitURL = new URL(String.format("https://api.github.com/repos/%s/%s", userName, repoName));
        connection = gitURL.openConnection();

        logger.info("Send request about group info(\"https://api.github.com/repos/" + userName + "/" + repoName +"\").");

        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        response = in.readLine();                                                                  //Read response.
        logger.info("End get repo info.");
        return response;
    }

    /**
     * Get commits history in repository.
     *
     * @param userName
     * @param repoName
     * @return
     * @throws IOException
     */
    public String getCommitList(String userName, String repoName) throws IOException {
        logger.info("Start get commit list");
        String response = null;

        gitURL = new URL(String.format("https://api.github.com/repos/%s/%s/commits", userName, repoName));
        connection = gitURL.openConnection();

        logger.info("Send request about commit list(\"https://api.github.com/repos/" + userName + "/" + repoName +"/commits\")");

        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        response = in.readLine();
        logger.info("End get commit list");
        return response;
    }
}
