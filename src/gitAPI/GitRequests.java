package gitAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


/**
 * Created by Andrey on 12.07.2017.
 */
public class GitRequests {
    private URL gitURL;
    private URLConnection connection;
    private BufferedReader in;

    /**
     * Get repository info.
     * @param userName
     * @param repoName
     * @return
     * @throws IOException
     */
    public String getRepoInfo(String userName, String repoName) throws IOException {
        String response = null;

        gitURL = new URL(String.format("https://api.github.com/repos/%s/%s", userName, repoName));
        connection = gitURL.openConnection();
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        response = in.readLine();                                                                  //Read response.
        return response;
    }

    /**
     * Get commits history in repository.
     * @param userName
     * @param repoName
     * @return
     * @throws IOException
     */
    public String getCommitList(String userName, String repoName) throws IOException {
        String response = null;

        gitURL = new URL(String.format("https://api.github.com/repos/%s/%s/commits", userName, repoName));
        connection = gitURL.openConnection();
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        response = in.readLine();
        return response;
    }
}
