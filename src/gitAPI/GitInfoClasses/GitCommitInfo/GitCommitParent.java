package gitAPI.GitInfoClasses.GitCommitInfo;

/**
 * Created by Andrey on 19.07.2017.
 */
public class GitCommitParent {
    private String sha;
    private String url;
    private String html_url;

    public GitCommitParent() {
        this.sha = null;
        this.url = null;
        this.html_url = null;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
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

    @Override
    public String toString() {
        return "{\n" +
                "sha='" + sha + '\n' +
                ", url='" + url + '\n' +
                ", html_url='" + html_url + '\'' +
                "\n}";
    }
}
