package data;

import data.group.SubGroup;
import data.lab.Lab;
import data.mark.LabMark;
import data.mark.TestMark;
import data.сomment.Comment;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class containing information about the student and list of marks for the lab,
 * list of bonuses, list of missed classes
 */
@Entity
@Table(name = "students")
@SecondaryTable(name = "bonuses", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id_student"))
@FilterDef(name = "coefficientFilter")
public class Student implements Serializable {
    @Id
    @Column(name = "id_student")
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
    @ManyToMany()
    @JoinTable(name = "absentees",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_class"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UniversityClass> missedUniversityClassesList;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "id_student")
    private List<LabMark> labMarkList;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "id_student")
    private List<TestMark> testMarkList;
    @Column(name = "bonus", table = "bonuses")
    private Integer bonusMark;
    @Column(name = "comment", table = "bonuses")
    private String bonusComment;
    @ManyToOne()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "id_subgroup")
    private SubGroup subGroup;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "id_student")
    private List<Comment> commentList;


    public Student() {
        this.missedUniversityClassesList = new ArrayList<UniversityClass>();
        this.labMarkList = new ArrayList<LabMark>();
        this.testMarkList = new ArrayList<TestMark>();
        this.commentList = new ArrayList<Comment>();
        this.bonusMark = -1;
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

    public String getCommentForBonusMark() {
        return "Temp comment for bonus mark";
    }

    public void setCommentForBonusMark(String comment) throws NoSuchMethodException {
        throw new NoSuchMethodException("Realise method");
    }

    public List<LabMark> getLabMarkList() {
        return labMarkList;
    }

    public void setLabMarkList(List<LabMark> labMarksMap) {
        this.labMarkList = labMarksMap;
    }

    public Integer getBonusMark() {
        return bonusMark;
    }

    public void setBonusMark(Integer bonusMark) {
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

    public void addLabMark(LabMark labMark) {
        this.labMarkList.add(labMark);
    }

    public void addTestMark(TestMark testMark) {
        this.testMarkList.add(testMark);
    }

    public LabMark getLabMark(Lab lab) {

        for (LabMark currentLabMark : this.labMarkList) {
            if (currentLabMark.getIssuedLab().getLabDescription().equals(lab))
                return currentLabMark;
        }
        return null;
    }

    public TestMark getTestMark(Integer number) {
        for (TestMark currentTestMark : this.testMarkList) {
            if (currentTestMark.getTest().getTestNumber().equals(number))
                return currentTestMark;
        }
        return null;
    }

    public SubGroup getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(SubGroup subGroup) {
        this.subGroup = subGroup;
    }

    public String getGitURL(){
        if(this.gitUserName == null || this.gitRepoName == null)
            return "";
        if(this.gitUserName.equals("") || this.gitRepoName.equals(""))
            return "";
        return String.format("https://github.com/%s/%s", this.gitUserName, this.gitRepoName);
    }

    public void setGitURL(@NotNull String url) {
        if(url.split("/").length != 5){
            this.gitRepoName = "";
            this.gitUserName = "";
            return;
        }
        this.gitRepoName = url.split("/")[4];
        this.gitUserName = url.split("/")[3];
    }

    public void setInfoFromURL(String url){
        String[] parseURL = url.split("/");
        this.gitUserName = parseURL[parseURL.length-2];
        this.gitRepoName = parseURL[parseURL.length-1];
    }

    public List<LabMark> sortLabMarkList() {
        Collections.sort(this.labMarkList,LabMark.COMPARATOR_BY_NUMBER_OF_LAB);
        return this.labMarkList;
    }

    public List<TestMark> sortTestMarkList() {
        Collections.sort(this.testMarkList, TestMark.COMPARATOR_BY_NUMBER_OF_TEST);
        return this.testMarkList;
    }

    public List<Comment> getCommentList(){
        return this.commentList;
    }

    public void setCommentList(List<Comment> commentList){
        this.commentList = commentList;
    }

    public void addComment(Comment comment){
        this.commentList.add(comment);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fulName='" + fulName + '\'' +
                ", gitRepoName='" + gitRepoName + '\'' +
                ", gitUserName='" + gitUserName + '\'' +
                '}';
    }

    public void addMissedClass(UniversityClass universityClass) {
        if(!this.missedUniversityClassesList.contains(universityClass)) {
            this.missedUniversityClassesList.add(universityClass);
        }
    }

    public void removeMissedClass(UniversityClass universityClass){
        if(this.missedUniversityClassesList.contains(universityClass)){
            this.missedUniversityClassesList.remove(universityClass);
        }
    }

    public String getComment() {
        return bonusComment;
    }

    public void setComment(String comment) {
        this.bonusComment = comment;
    }
}
