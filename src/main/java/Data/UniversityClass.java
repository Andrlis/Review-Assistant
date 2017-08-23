package Data;

import Data.Group.SubGroup;

import javax.persistence.*;
import java.util.Date;

/**
 * University class
 */
@Entity
@Table(name = "classes")
public class UniversityClass {
    @Id
    @Column(name = "id_class")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "class_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_group_subgroup")
    private SubGroup subGroup;

    public UniversityClass() {
    }

    public UniversityClass(Integer id, Date date, SubGroup subGroup) {
        this.id = id;
        this.date = date;
        this.subGroup = subGroup;
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

    public SubGroup getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(SubGroup subGroup) {
        this.subGroup = subGroup;
    }
}
