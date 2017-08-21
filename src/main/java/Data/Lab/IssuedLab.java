package Data.Lab;

import Data.Student;
import Data.UniversityClass;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@FilterDef(name ="coefficientFilter",
        parameters = @ParamDef(name ="coefficientFilterParam", type = "int"))
public class IssuedLab {
    @Id
    @Column(name ="id_issued_lab")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_lab")
    private Lab labDescription;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_class_of_issue")
    private UniversityClass universityClassOfIssue;
    @Column(name = "coefficient", columnDefinition="Decimal(10,2)")
    private Double coefficientOfCurrentDeadline;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_class_deadline")
    private UniversityClass currentDeadline;
    @Column(name ="last_check_date_time", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfLastRepoCheck;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "labs_marks",
            joinColumns = @JoinColumn(name = "id_issued_lab"),
            inverseJoinColumns = @JoinColumn(name = "id_student"))
    @Filter(name ="coefficientFilterParam",condition = "coefficient == :coefficientFilterParam")
    private List<Student> studentControlList;


    public IssuedLab(){
        this.studentControlList = new ArrayList<Student>();
    }

    public IssuedLab(Integer id, Lab labDescription, UniversityClass universityClassOfIssue,
                     Double coefficientOfCurrentDeadline, UniversityClass currentDeadline,
                     Date dateOfLastRepoCheck, List<Student> studentControlList) {
        this.id = id;
        this.labDescription = labDescription;
        this.universityClassOfIssue = universityClassOfIssue;
        this.coefficientOfCurrentDeadline = coefficientOfCurrentDeadline;
        this.currentDeadline = currentDeadline;
        this.dateOfLastRepoCheck = dateOfLastRepoCheck;
        this.studentControlList = studentControlList;
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
        this.studentControlList = studentControlList;
    }

    public void deleteStudentFromControlList(Student student) {
        this.studentControlList.remove(student);
    }
}
