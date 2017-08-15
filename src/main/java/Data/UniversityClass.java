package Data;

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
    @Column(name ="id_group_subgroup")
    private Integer subGroupID;

    public UniversityClass(){}

    public UniversityClass(Integer id, Date date, Integer subGroupID) {
        this.id = id;
        this.date = date;
        this.subGroupID = subGroupID;
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

    public Integer getSubGroupID() {
        return subGroupID;
    }

    public void setSubGroupID(Integer subGroupID) {
        this.subGroupID = subGroupID;
    }
}
