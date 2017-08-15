package gitAPI.GitInfoClasses.GitCommitInfo;

/**
 * Created by Andrey on 19.07.2017.
 */
public class GitCommit {
    private GitCommitUser author;
    private GitCommitUser committer;
    private String message;
    private String url;
    private int comment_count;

    public GitCommit() {
        author = new GitCommitUser();
        committer = new GitCommitUser();
        message = null;
        url = null;
        comment_count = 0;
    }

    public GitCommitUser getAuthor() {
        return author;
    }

    public void setAuthor(GitCommitUser author) {
        this.author = author;
    }

    public GitCommitUser getCommitter() {
        return committer;
    }

    public void setCommitter(GitCommitUser committer) {
        this.committer = committer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    @Override
    public String toString() {
        return "\n" +
                "   author:\n" + author.toString() +
                "   committer:\n" + committer.toString() +
                "   message='" + message + '\n' +
                "   url='" + url + '\n' +
                "   comment_count=" + comment_count +
                "\n";
    }
}
