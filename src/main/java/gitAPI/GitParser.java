package gitAPI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gitAPI.GitInfoClasses.GitCommitInfo.GitCommitHistory;
import gitAPI.GitInfoClasses.GitRepository;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Andrey on 13.07.2017.
 */
public class GitParser {
    private static final Logger logger = Logger.getLogger(GitParser.class);
    private Gson gson;

    public GitParser() {
        gson = new Gson();
    }

    public GitRepository readRepoInfo(String json) throws IOException {
        GitRepository repo = gson.fromJson(json, GitRepository.class);
        return repo;
    }

    /**
     * Парсинг истории коммитов.
     * @param json
     * @return
     * @throws IOException
     */
    public ArrayList<GitCommitHistory> readCommitHistory(String json) throws IOException {
        logger.info("Start read commit history.");

        Type type = new TypeToken<ArrayList<GitCommitHistory>>() {}.getType();

        ArrayList<GitCommitHistory> commitList = gson.fromJson(json, type);

        logger.info("End read commit history.");
        return commitList;
    }
}
