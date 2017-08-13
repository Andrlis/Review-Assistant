package Data.Group;

import java.util.List;

/**
 * groups keeper
 */
public class GroupsKeeper {
    private List<Group> groupList;

    public GroupsKeeper(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public void addGroup(Group group) {
        if(!this.groupList.contains(group)) {
            this.groupList.add(group);
        }
    }
}
