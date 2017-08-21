package Data.Mark;

import Data.Lab.Lab;
import Data.Student;

import javax.persistence.*;

/**
 * Lab mark
 */
@Entity
@Table(name = "labs_marks")
public class LabMark {
    @Id
    @Column(name ="id_lab_mark")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "coefficient", columnDefinition="Decimal(10,2)")
    private Double coefficient;
    @Column(name = "mark")
    private Integer mark;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_issued_lab")
    private Lab lab;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_student")
    private Student student;

    public LabMark(){}

    public LabMark(Integer id, Double coefficient, Integer mark, Lab lab, Student student) {
        this.id = id;
        this.coefficient = coefficient;
        this.mark = mark;
        this.lab = lab;
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

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
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
