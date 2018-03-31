package data.lab;

import data.group.SubGroup;
import data.Student;
import data.UniversityClass;
import org.apache.log4j.Logger;
import org.hibernate.annotations.*;
import resources.Hibernate.HibernateCore;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * class containing information about issue laboratory work and list of students
 * who did not pass the laboratory work
 */
@Entity
@Table(name = "issued_labs")
@FilterDef(name = "coefficientFilter", parameters = {
        @ParamDef(name = "border", type = "double")
})
public class IssuedLab implements Serializable {
    private static final double decreasePart  = 0.2;
    public static final  double STUDENT_CHEAT = -2;
    private static final Logger logger        = Logger.getLogger(IssuedLab.class);
    @Id
    @Column(name = "id_issued_lab")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer         id;
    @ManyToOne()
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "id_lab")
    private Lab             labDescription;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_class_of_issue")
    private UniversityClass universityClassOfIssue;
    @Column(name = "coefficient")
    private Double          coefficientOfCurrentDeadline;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_class_deadline")
    private UniversityClass currentDeadline;
    @Column(name = "last_check_date_time", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date            dateOfLastRepoCheck;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "labs_marks",
            joinColumns = @JoinColumn(name = "id_issued_lab"),
            inverseJoinColumns = @JoinColumn(name = "id_student"))
    @FilterJoinTable(name = "coefficientFilter", condition = ":border >= coefficient")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Student>   studentControlList;
    @ManyToOne()
    @Cascade({org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "id_group_subgroup")
    private SubGroup        subGroup;

    public IssuedLab() {
        this.studentControlList = new ArrayList<Student>();
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
        logger.info("Delete student(" + student.getFulName() + ") from student confrol list issue lab(" + labDescription.getNumberOfLab() + ")");
        this.studentControlList.remove(student);
    }

    public SubGroup getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(SubGroup subGroup) {
        this.subGroup = subGroup;
    }

    public void decreaseCoefficient() {
        if (this.coefficientOfCurrentDeadline > 0.4)
            this.coefficientOfCurrentDeadline -= IssuedLab.decreasePart;
        else
            this.coefficientOfCurrentDeadline = 0.0;

    }

    /**
     * Calculates coefficient of mark according to the date of the commit
     *
     * @param commitDate
     * @return coefficient
     * -2 - cheated
     * from 1 to 0 - coef
     */

    public double getCoefficientOfCommit(Date commitDate) {

        if (commitDate.before(this.getDateOfLastRepoCheck())) {               //Дата коммита раньше даты последней проверки. Фальсификация сдачи

            logger.info("Student cheated.");
            return STUDENT_CHEAT;
        } else {

            logger.info("Student committed a lab.");
            return this.getCoefficientOfCurrentDeadline();
        }
    }

    @Override
    public String toString() {
        return "IssuedLab{" +
                "id=" + id +
                ", labDescription=" + labDescription +
                ", universityClassOfIssue=" + universityClassOfIssue +
                ", coefficientOfCurrentDeadline=" + coefficientOfCurrentDeadline +
                ", currentDeadline=" + currentDeadline +
                ", dateOfLastRepoCheck=" + dateOfLastRepoCheck +
                '}';
    }
}
