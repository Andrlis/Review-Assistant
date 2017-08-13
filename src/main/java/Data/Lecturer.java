package Data;

import Data.Group.SubGroup;

import java.util.List;

/**
 * lecturer
 */
public class Lecturer {
    private Long id;
    private String fullName;
    private List<SubGroup> subGroupList;

    public Lecturer(Long id, String fullName, List<SubGroup> subGroupList) {
        this.id = id;
        this.fullName = fullName;
        this.subGroupList = subGroupList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
