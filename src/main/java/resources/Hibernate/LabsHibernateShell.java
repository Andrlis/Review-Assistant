package resources.Hibernate;

import data.UniversityClass;
import data.group.Group;
import data.group.SubGroup;

public class LabsHibernateShell {

    public static boolean issueLab(String groupNumber,
                                   String subGroupNumber,
                                   String date,
                                   String comment)
            throws HibernateShellQueryException {
        Group group = HibernateShell.getGroupByGroupNumber(groupNumber);
        if(group == null)
            return false;

        SubGroup subGroup = group.getSubGroup(subGroupNumber);
        if(subGroup == null)
            return false;

        if(HibernateShell.getNumberOfNextLab() == subGroup.getIssuedLabsList().size() + 1)
            addLab("lab" + HibernateShell.getNumberOfNextLab());

        for (UniversityClass universityClass : subGroup.getUniversityClassesList()) {
            if(universityClass.getDataTime().equals(date)){
                HibernateShell.issueLab(subGroup,universityClass);
                return true;
            }

        }
        return false;
    }

    public static void addLab(String keyWorld) throws HibernateShellQueryException {
        HibernateShell.addLab(keyWorld);
    }

    public static int getNumberIssuedLab(String groupNumber, String subGroupNumber) throws HibernateShellQueryException {
        Group group = HibernateShell.getGroupByGroupNumber(groupNumber);
        if(group == null)
            return -1;

        SubGroup subGroup = group.getSubGroup(subGroupNumber);
        if(subGroup == null)
            return -1;

        return subGroup.getIssuedLabsList().size();
    }

    public static int getNumberIssuedLab(SubGroup subGroup) {
        if(subGroup == null)
            return -1;

        return subGroup.getIssuedLabsList().size();
    }
}
