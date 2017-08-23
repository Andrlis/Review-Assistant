package Data.Mark;

import Data.Lab.IssuedLab;
import Data.Student;

import javax.persistence.*;

/**
 * IssuedLab mark
 */
@Entity
@Table(name = "labs_marks")
public class LabMark {
    @Id
    @Column(name = "id_lab_mark")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "coefficient")
    private Double coefficient;
    @Column(name = "mark")
    private Integer mark;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_issued_lab")
    private IssuedLab issuedLab;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_student")
    private Student student;

    public LabMark() {
    }

    public LabMark(Integer id, Double coefficient, Integer mark, IssuedLab issuedLab, Student student) {
        this.id = id;
        this.coefficient = coefficient;
        this.mark = mark;
        this.issuedLab = issuedLab;
        this.student = student;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IssuedLab getIssuedLab() {
        return issuedLab;
    }

    public void setIssuedLab(IssuedLab lab) {
        this.issuedLab = lab;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
