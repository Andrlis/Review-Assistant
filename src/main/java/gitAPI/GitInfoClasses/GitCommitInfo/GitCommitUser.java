package gitAPI.GitInfoClasses.GitCommitInfo;

/**
 * Created by Andrey on 19.07.2017.
 */
public class GitCommitUser {
    private String name;
    private String email;
    private String date;

    GitCommitUser(){
        name = null;
        email = null;
        date = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  "       name=" + name + '\n' +
                "       email=" + email + '\n' +
                "       date=" + date + '\n' +
                "\n";
    }
}
