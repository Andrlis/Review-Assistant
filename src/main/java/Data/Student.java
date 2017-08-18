package Data;

import Data.Lab.Lab;
import Data.Mark.LabMark;
import Data.Mark.Mark;
import Data.Mark.TestMark;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class containing information about the student and list of marks for the lab,
 * list of bonuses, list of missed classes
 */
@Entity
@Table(name ="students")
@SecondaryTable(name = "bonuses", pkJoinColumns =
    @PrimaryKeyJoinColumn(name = "id_student",referencedColumnName = "id_student"))
public class Student {
    @Id
    @Column(name ="id_student")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name", length = 100)
    private String fulName;
    @Column(name = "git_repo_name", length = 30)
    private String gitRepoName;
    @Column(name = "git_user_name", length = 30)
    private String gitUserName;
    @Column(name = "email", length = 30)
    private String eMail;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "absentees",
                joinColumns = @JoinColumn(name = "id_student"),
                inverseJoinColumns = @JoinColumn(name = "id_class"))
    private List<UniversityClass> missedUniversityClassesList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student")
    private List<LabMark> labMarkList;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student")
    private List<TestMark> testMarkList;
    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name ="mark", column = @Column(name = "bonus", table = "bonuses"))
    )
    private Mark bonusMark;

    public Student(){
        this.missedUniversityClassesList = new ArrayList<UniversityClass>();
        this.labMarkList = new ArrayList<LabMark>();
        this.testMarkList = new ArrayList<TestMark>();
    }

    public Student(Integer id, String fulName, String gitRepoName, String gitUserName, String eMail, List<LabMark> labMarkList, List<TestMark> testMarkList, Mark bonusMark, List<UniversityClass> missedUniversityClassesList) {
        this.id = id;
        this.fulName = fulName;
        this.gitRepoName = gitRepoName;
        this.gitUserName = gitUserName;
        this.eMail = eMail;
        this.labMarkList = labMarkList;
        this.testMarkList = testMarkList;
        this.bonusMark = bonusMark;
        this.missedUniversityClassesList = missedUniversityClassesList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFulName() {
        return fulName;
    }

    public void setFulName(String fulName) {
        this.fulName = fulName;
    }

    public String getGitRepoName() {
        return gitRepoName;
    }

    public void setGitRepoName(String gitRepoName) {
        this.gitRepoName = gitRepoName;
    }

    public String getGitUserName() {
        return gitUserName;
    }

    public void setGitUserName(String gitUserName) {
        this.gitUserName = gitUserName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public List<LabMark> getLabMarkList() {
        return labMarkList;
    }

    public void setLabMarkList(List<LabMark> labMarksMap) {
        this.labMarkList = labMarksMap;
    }

    public Mark getBonusMark() {
        return bonusMark;
    }

    public void setBonusMark(Mark bonusMark) {
        this.bonusMark = bonusMark;
    }

    public List<UniversityClass> getMissedUniversityClassesList() {
        return missedUniversityClassesList;
    }

    public void setMissedUniversityClassesList(List<UniversityClass> missedUniversityClassesList) {
        this.missedUniversityClassesList = missedUniversityClassesList;
    }

    public List<TestMark> getTestMarkList() {
        return testMarkList;
    }


    public void setTestMarkList(List<TestMark> testMarksMap) {
        this.testMarkList = testMarksMap;
    }

    public void addCoefficientToCommitedLab(Lab lab, Double coefficient) {
        LabMark labMark = this.getLabMark(lab);
        if (labMark != null) {
            labMark.setCoefficient(coefficient);
        }
    }

    public void addMarkToCommiteLab(Lab lab, int mark) {
        LabMark labMark = this.getLabMark(lab);
        if (labMark != null) {
            labMark.setMark(mark);
        }
    }

    private void addLabMark(LabMark labMark) {
        this.labMarkList.add(labMark);
    }

    private void addTestMark(TestMark testMark) {
        this.testMarkList.add(testMark);
    }

    public LabMark getLabMark(Lab lab) {

        for(LabMark currentLabMark: this.labMarkList){
            if(currentLabMark.getLab().equals(lab))
                return currentLabMark;
        }
        return null;
    }

    public Mark getTestMark(Integer number) {
        for(TestMark currentTestMark: this.testMarkList){
            if(currentTestMark.getTest().getTestNumber().equals(number))
                return currentTestMark;
        }
        return null;
    }
}
