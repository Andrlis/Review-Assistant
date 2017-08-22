package Data.Group;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class containing information abput group and list of subgroup
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name ="id_group")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="group_number", length = 10)
    private String numberOfGroup;
    @Column(name ="bsuir_api_group_id", length = 10)
    private String scheduleApiGroupNumber;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<SubGroup> subGroupList;
    @Column(name = "amount_of_test")
    private Integer amountOfTest;

    public Group(){
        this.subGroupList = new ArrayList<SubGroup>();
    }

    public Group(Integer id, String numberOfGroup, String scheduleApiGroupNumber, List<SubGroup> subGroupList, Integer amountOfTest) {
        this.id = id;
        this.numberOfGroup = numberOfGroup;
        this.scheduleApiGroupNumber = scheduleApiGroupNumber;
        this.subGroupList = subGroupList;
        this.amountOfTest = amountOfTest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public String getScheduleApiGroupNumber() {
        return scheduleApiGroupNumber;
    }

    public void setScheduleApiGroupNumber(String scheduleApiGroupNumber) {
        this.scheduleApiGroupNumber = scheduleApiGroupNumber;
    }

    public List<SubGroup> getSubGroupList() {
        return subGroupList;
    }

    public void setSubGroupList(List<SubGroup> subGroupList) {
        this.subGroupList = subGroupList;
    }

    public Integer getAmountOfTest() {
        return amountOfTest;
    }

    public void setAmountOfTest(Integer amountOfTest) {
        this.amountOfTest = amountOfTest;
    }

    public void addSubGroup(SubGroup subGroup) {
        if (!this.subGroupList.contains(subGroup))
            this.subGroupList.add(subGroup);
    }

    public void addTest() {
        this.amountOfTest++;
    }
}
