package Data;

import Data.Lab.Lab;
import Data.Mark.LabMark;
import Data.Mark.Mark;
import org.hibernate.annotations.Tables;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * class containing information about the student and list of marks for the lab,
 * list of bonuses, list of missed classes
 */
@Entity
@Table(name ="students")
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

    private Map<Lab, LabMark> labMarksMap;
    private Map<Integer, Mark> testMarksMap;
    private Mark bonusMark;

    public Student(){}

    public Student(Integer id, String fulName, String gitRepoName, String gitUserName, String eMail, Map<Lab, LabMark> labMarksMap, Map<Integer, Mark> testMarksMap, Mark bonusMark, List<UniversityClass> missedUniversityClassesList) {
        this.id = id;
        this.fulName = fulName;
        this.gitRepoName = gitRepoName;
        this.gitUserName = gitUserName;
        this.eMail = eMail;
        this.labMarksMap = labMarksMap;
        this.testMarksMap = testMarksMap;
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

    public Map<Lab, LabMark> getLabMarksMap() {
        return labMarksMap;
    }

    public void setLabMarksMap(Map<Lab, LabMark> labMarksMap) {
        this.labMarksMap = labMarksMap;
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

    public Map<Integer, Mark> getTestMarksMap() {
        return testMarksMap;
    }


    public void setTestMarksMap(Map<Integer, Mark> testMarksMap) {
        this.testMarksMap = testMarksMap;
    }

    public void addCoefficientToCommitedLab(Lab lab, Double coefficient) {
        LabMark labMark = this.labMarksMap.get(lab);
        if (labMark != null) {
            labMark.setCoefficient(coefficient);
        }
    }

    public void addMarkToCommiteLab(Lab lab, int mark) {
        LabMark labMark = this.labMarksMap.get(lab);
        if (labMark != null) {
            labMark.setMark(mark);
        }
    }

    private void addLabMark(Lab lab, LabMark labMark) {
        if (this.labMarksMap.containsKey(lab)) {
            this.labMarksMap.get(lab).setMark(labMark.getMark());
        } else {
            this.labMarksMap.put(lab, labMark);
        }
        this.labMarksMap.put(lab, labMark);
    }

    private void addTestMark(Integer number, Integer mark) {
        if (this.testMarksMap.containsKey(number)) {
            Mark m = this.testMarksMap.get(number);
            m.setMark(mark);
        } else {
            Mark m = new Mark(mark);
            this.testMarksMap.put(number, m);
        }
    }

    public LabMark getLabMark(Lab lab) {
        return this.labMarksMap.get(lab);
    }

    public Mark getTestMark(Integer number) {
        return this.testMarksMap.get(number);
    }
}
