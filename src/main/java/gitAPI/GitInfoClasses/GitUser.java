package gitAPI.GitInfoClasses;

/**
 * Created by Andrey on 13.07.2017.
 */
public class GitUser {

    private String login;
    private String html_url;

    public GitUser(){
        login = null;
        html_url = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    @Override
    public String toString() {
        return "\n" +
                "   login=" + login + '\n' +
                "   html_url=" + html_url + '\n';
    }
}
