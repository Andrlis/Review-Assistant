package Data.Group;

import java.util.List;

/**
 * class containing information abput group and list of subgroup
 */
public class Group {
    private Long id;
    private String numberOfGroup;
    private String scheduleApiGroupNumber;
    private List<SubGroup> subGroupList;
    private Integer amountOfTest;

    public Group(){
        this.id = null;
        this.numberOfGroup = null;
        this.scheduleApiGroupNumber = null;
        this.subGroupList = null;
        this.amountOfTest = null;
    }

    public Group(Long id, String numberOfGroup, String scheduleApiGroupNumber, List<SubGroup> subGroupList, Integer amountOfTest) {
        this.id = id;
        this.numberOfGroup = numberOfGroup;
        this.scheduleApiGroupNumber = scheduleApiGroupNumber;
        this.subGroupList = subGroupList;
        this.amountOfTest = amountOfTest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
