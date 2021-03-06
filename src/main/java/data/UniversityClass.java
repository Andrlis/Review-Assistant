package data;

import data.group.SubGroup;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * University class
 * Информация о занятии у подгруппы.
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

    @ManyToOne
    @JoinColumn(name = "id_subgroup")
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

    public String getDataTime() {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
        return dataFormat.format(this.date);
    }
}
