package gitAPI.GitInfoClasses.GitCommitInfo;

import gitAPI.GitInfoClasses.GitUser;

/**
 * Created by Andrey on 19.07.2017.
 */
public class GitCommitHistory {
    private String sha;
    private GitCommit commit;
    private String url;
    private String html_url;
    private String comments_url;
    private GitUser author;
    private GitUser committer;

    public GitCommitHistory() {
        this.sha = null;
        this.commit = new GitCommit();
        this.url = null;
        this.html_url = null;
        this.comments_url = null;
        this.author = new GitUser();
        this.committer = new GitUser();
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public GitCommit getCommit() {
        return commit;
    }

    public void setCommit(GitCommit commit) {
        this.commit = commit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public GitUser getAuthor() {
        return author;
    }

    public void setAuthor(GitUser author) {
        this.author = author;
    }

    public GitUser getCommitter() {
        return committer;
    }

    public void setCommitter(GitUser committer) {
        this.committer = committer;
    }

    @Override
    public String toString() {
        return "{\n" +
                " sha=" + sha + '\n' +
                " commit:" + commit +
                " url=" + url + '\n' +
                " html_url=" + html_url + '\n' +
                " comments_url=" + comments_url + '\n' +
                " author:" + author.toString() +
                " committer:" + committer.toString() +
                "\n}\n";
    }
}
