package Data.Lab;

import Data.Mark.LabMark;
import Data.Student;
import Data.UniversityClass;
import org.hibernate.annotations.FilterJoinTable;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * class containing information about issued laboratory work and list of students
 * who did not pass the laboratory work
 */
@Entity
@Table(name = "issued_labs")
public class IssuedLab implements Serializable {
    @Id
    @Column(name = "id_issued_lab")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_lab")
    private Lab labDescription;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_class_of_issue")
    private UniversityClass universityClassOfIssue;
    @Column(name = "coefficient")
    private Double coefficientOfCurrentDeadline;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_class_deadline")
    private UniversityClass currentDeadline;
    @Column(name = "last_check_date_time", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfLastRepoCheck;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "labs_marks",
            joinColumns = @JoinColumn(name = "id_issued_lab"),
            inverseJoinColumns = @JoinColumn(name = "id_student"))
    @FilterJoinTable(name = "coefficientFilter", condition = "coefficient <= -1")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Student> studentControlList;
  /*  @OneToMany(mappedBy = "issuedLab", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LabMark> labMarkList;*/


    public IssuedLab() {
        this.studentControlList = new ArrayList<Student>();
    }

    public IssuedLab(Integer id, Lab labDescription, UniversityClass universityClassOfIssue,
                     Double coefficientOfCurrentDeadline, UniversityClass currentDeadline,
                     Date dateOfLastRepoCheck, List<Student> studentControlList, List<LabMark> labMarkList) {
        this.id = id;
        this.labDescription = labDescription;
        this.universityClassOfIssue = universityClassOfIssue;
        this.coefficientOfCurrentDeadline = coefficientOfCurrentDeadline;
        this.currentDeadline = currentDeadline;
        this.dateOfLastRepoCheck = dateOfLastRepoCheck;
        this.studentControlList = studentControlList;
       // this.labMarkList = labMarkList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Lab getLabDescription() {
        return labDescription;
    }

    public void setLabDescription(Lab labDescription) {
        this.labDescription = labDescription;
    }

    public UniversityClass getUniversityClassOfIssue() {
        return universityClassOfIssue;
    }

    public void setUniversityClassOfIssue(UniversityClass universityClassOfIssue) {
        this.universityClassOfIssue = universityClassOfIssue;
    }

    public Double getCoefficientOfCurrentDeadline() {
        return coefficientOfCurrentDeadline;
    }

    public void setCoefficientOfCurrentDeadline(Double coefficientOfCurrentDeadline) {
        this.coefficientOfCurrentDeadline = coefficientOfCurrentDeadline;
    }

    public UniversityClass getCurrentDeadline() {
        return currentDeadline;
    }

    public void setCurrentDeadline(UniversityClass currentDeadline) {
        this.currentDeadline = currentDeadline;
    }

    public Date getDateOfLastRepoCheck() {
        return dateOfLastRepoCheck;
    }

    public void setDateOfLastRepoCheck(Date dateOfLastRepoCheck) {
        this.dateOfLastRepoCheck = dateOfLastRepoCheck;
    }

    public List<Student> getStudentControlList() {
        return studentControlList;
    }

    public void setStudentControlList(List<Student> studentControlList) {
        this.studentControlList.clear();
        this.studentControlList.addAll(studentControlList);
    }

    public void deleteStudentFromControlList(Student student) {
        this.studentControlList.remove(student);
    }

  /*  public List<LabMark> getLabMarkList() {
        return labMarkList;
    }

    public void setLabMarkList(List<LabMark> labMarkList) {
        this.labMarkList = labMarkList;
    }*/
}
