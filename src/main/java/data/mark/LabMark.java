package data.mark;

import data.lab.IssuedLab;
import data.Student;

import javax.persistence.*;
import java.util.Comparator;

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
    @Column(name = "comment", length = 280)
    private String comment;


    public LabMark() {
        coefficient = -1.0;
        mark = -1;
    }

    /**
     *
     * @return Mark with coefficient
     */
    public double getRealMark() {
       if (coefficient > 0 && mark > 0)
           return coefficient * mark;
       else return 0;
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

    public static final Comparator<LabMark> COMPARATOR_BY_NUMBER_OF_LAB = new Comparator<LabMark>() {
        @Override
        public int compare(LabMark labMark, LabMark t1) {
            return labMark.getIssuedLab().getLabDescription().getNumberOfLab() - t1.getIssuedLab().getLabDescription().getNumberOfLab();
        }
    };

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
