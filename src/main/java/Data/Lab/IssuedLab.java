package Data.Lab;

import Data.Student;
import Data.UniversityClass;

import java.util.Date;
import java.util.List;

/**
 * class containing information about issued laboratory work and list of students
 * who did not pass the laboratory work
 */
public class IssuedLab {
    private Long id;
    private Lab labDescription;
    private UniversityClass universityClassOfIssue;
    private Double coefficientOfCurrentDeadline;
    private UniversityClass currentDeadline;
    private Date dateOfLastRepoCheck;
    private List<Student> studentControlList;

    public IssuedLab(Long id, Lab labDescription, UniversityClass universityClassOfIssue,
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
