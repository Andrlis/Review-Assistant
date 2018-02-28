package resources.Hibernate;

import data.group.Group;
import data.group.SubGroup;

public class GroupHibernateShell {
    private HibernateCore hibernateCore;

    public GroupHibernateShell() {
        hibernateCore = HibernateCore.getInstance();
    }

    public void deleteGroup(Group group) {
        hibernateCore.delete(group);
    }

    public void deleteGroup(String groupNumber) throws HibernateShellQueryException {
        Group group = hibernateCore.getGroupByGroupNumber(groupNumber);

        hibernateCore.delete(group);
    }

    public void deleteSubGroup(SubGroup subGroup) {
        hibernateCore.delete(subGroup);
    }

    public void deleteSubGroup(String groupNumber, String subGroupNumber) throws HibernateShellQueryException {
        Group group = hibernateCore.getGroupByGroupNumber(groupNumber);
        SubGroup subGroup = group.getSubGroup(subGroupNumber);

        hibernateCore.delete(subGroup);
    }
}
