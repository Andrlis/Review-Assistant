package resources.Hibernate;

import data.UniversityClass;
import data.group.Group;
import data.group.SubGroup;

public class LabsHibernateShell {

    public static boolean issueLab(String groupNumber, String subGroupNumber, String date){
        Group group = HibernateShell.getGroupByGroupNumber(groupNumber);
        if(group == null)
            return false;

        SubGroup subGroup = group.getSubGroup(subGroupNumber);
        if(subGroup == null)
            return false;


        for (UniversityClass universityClass : subGroup.getUniversityClassesList()) {
            if(universityClass.getDataTime().equals(date)){
                HibernateShell.issueLab(subGroup,universityClass);
                return true;
            }

        }
        return false;
    }

    public static void addLab(String keyWorld){
        HibernateShell.addLab(keyWorld);
    }
}
