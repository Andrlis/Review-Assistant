package Data;

import java.util.ArrayList;

/**
 * Created by kesso on 08.08.17.
 */
public class Student {
    private long id;
    private String name;
    private String gitUserName;
    private String gitRepoName;
    private String eMail;
    private SubGroup subGroup;
    private int numberOfPasses;
    private ArrayList<Lab> labs;

    public Student(long id, String name, String gitUserName, String gitRepoName, String eMail, SubGroup subGroup, int numberOfPasses, ArrayList<Lab> labs) {
        this.id = id;
        this.name = name;
        this.gitUserName = gitUserName;
        this.gitRepoName = gitRepoName;
        this.eMail = eMail;
        this.subGroup = subGroup;
        this.numberOfPasses = numberOfPasses;
        this.labs = labs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGitUserName() {
        return gitUserName;
    }

    public void setGitUserName(String gitUserName) {
        this.gitUserName = gitUserName;
    }

    public String getGitRepoName() {
        return gitRepoName;
    }

    public void setGitRepoName(String gitRepoName) {
        this.gitRepoName = gitRepoName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public SubGroup getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(SubGroup subGroup) {
        this.subGroup = subGroup;
    }

    public ArrayList<Lab> getLabs() {
        return labs;
    }

    public void setLabs(ArrayList<Lab> labs) {
        this.labs = labs;
    }

    public int getNumberOfPasses() {
        return numberOfPasses;
    }

    public void setNumberOfPasses(int numberOfPasses) {
        this.numberOfPasses = numberOfPasses;
    }
}
