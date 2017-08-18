package Data.Mark;

import Data.Lab.Lab;

import javax.persistence.*;

/**
 * Lab mark
 */
@Entity
@Table(name = "Labs_marks")
public class LabMark extends Mark {
    @Id
    @Column(name ="id_lab_mark")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "coefficient")
    private Double coefficient;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_issued_lab")
    private Lab lab;

    public LabMark(){
        super();
    }

    public LabMark(Integer mark, Integer id, Double coefficient, Lab lab) {
        super(mark);
        this.id = id;
        this.coefficient = coefficient;
        this.lab = lab;
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
}
