package data.lecturer;

import data.group.SubGroup;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * lecturer
 */
@Entity
@Table(name = "lecturers")
public class Lecturer {
    @Id
    @Column(name = "id_lecturer")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "full_name", length = 100)
    private String fullName;
    @OneToMany(mappedBy = "lecturer", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SubGroup> subGroupList;


    public Lecturer() {
        this.subGroupList = new ArrayList<SubGroup>();
    }

    public Lecturer(Integer id, String fullName, List<SubGroup> subGroupList) {
        this.id = id;
        this.fullName = fullName;
        this.subGroupList = subGroupList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<SubGroup> getSubGroupList() {
        return subGroupList;
    }

    public void setSubGroupList(List<SubGroup> subGroupList) {
        this.subGroupList = subGroupList;
    }
}
