package Data;

import Data.Group.SubGroup;

import javax.persistence.*;
import java.util.Date;

/**
 * University class
 */
@Entity
@Table(name="classes")
public class UniversityClass {
    @Id
    @Column(name ="id_class")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="class_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public UniversityClass(){}

    public UniversityClass(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String toString() {
        return "University class: id = " + id + " date & time = " + date;
    }
}
