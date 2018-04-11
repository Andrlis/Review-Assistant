package resources.Hibernate;

import data.group.Group;
import data.group.SubGroup;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GroupHibernateShellTest {

    @Test
    public void deleteGroup() {
        Integer p = null;
        try {
           p = HibernateCore.getInstance().getGroupKeeper().getGroupList().size();
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }


        SubGroup subGroup1 = new SubGroup();
        subGroup1.setSubGroupNumber("1");

        SubGroup subGroup2 = new SubGroup();
        subGroup2.setSubGroupNumber("2");

        Group group = new Group();
        group.setNumberOfGroup("322");
        subGroup1.setGroup(group);
        subGroup2.setGroup(group);
        ArrayList<SubGroup> subGroups = new ArrayList<>();
        subGroups.add(subGroup1);
        subGroups.add(subGroup2);

        group.setSubGroupList(subGroups);
        group.setAmountOfTest(0);
        group.setScheduleApiGroupNumber("322");

        HibernateCore.getInstance().save(group);

        GroupHibernateShell ghs = new GroupHibernateShell();
        ghs.deleteGroup(group);

        Integer a = null;
        try {
            a = HibernateCore.getInstance().getGroupKeeper().getGroupList().size();
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }

        assertEquals(p,a);
    }

    @Test
    public void deleteGroup1() throws HibernateShellQueryException {
        Integer p = null;
        try {
            p = HibernateCore.getInstance().getGroupKeeper().getGroupList().size();
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }


        SubGroup subGroup1 = new SubGroup();
        subGroup1.setSubGroupNumber("1");

        SubGroup subGroup2 = new SubGroup();
        subGroup2.setSubGroupNumber("2");

        Group group = new Group();
        group.setNumberOfGroup("322");
        subGroup1.setGroup(group);
        subGroup2.setGroup(group);
        ArrayList<SubGroup> subGroups = new ArrayList<>();
        subGroups.add(subGroup1);
        subGroups.add(subGroup2);

        group.setSubGroupList(subGroups);
        group.setAmountOfTest(0);
        group.setScheduleApiGroupNumber("322");

        HibernateCore.getInstance().save(group);

        GroupHibernateShell ghs = new GroupHibernateShell();
        ghs.deleteGroup(group.getNumberOfGroup());

        Integer a = null;
        try {
            a = HibernateCore.getInstance().getGroupKeeper().getGroupList().size();
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }

        assertEquals(p,a);
    }
}