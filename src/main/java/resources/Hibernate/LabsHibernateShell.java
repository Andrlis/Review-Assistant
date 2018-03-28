package resources.Hibernate;

import data.Student;
import data.UniversityClass;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.mark.LabMark;

import java.util.Date;


public class LabsHibernateShell {

    private HibernateCore hibernateCore;

    public LabsHibernateShell() {
        hibernateCore = HibernateCore.getInstance();
    }

    public boolean issueLab(String groupNumber, String subGroupNumber, String date)
            throws HibernateShellQueryException {
        Group group = hibernateCore.getGroupByGroupNumber(groupNumber);
        if (group == null)
            return false;

        SubGroup subGroup = group.getSubGroup(subGroupNumber);
        if (subGroup == null)
            return false;

        if (getNumberOfNextLab() == subGroup.getIssuedLabsList().size() + 1)
            addLab("lab" + getNumberOfNextLab());

        for (UniversityClass universityClass : subGroup.getUniversityClassesList()) {
            if (universityClass.getDataTime().equals(date)) {
                issueLab(subGroup, universityClass);
                return true;
            }
        }
        return false;
    }

    public LabMark getLabMarkById(String id) throws HibernateShellQueryException {
        return hibernateCore.getLabMarkById(Integer.parseInt(id));
    }

    public int getNumberIssuedLab(String groupNumber, String subGroupNumber) throws HibernateShellQueryException {
        Group group = hibernateCore.getGroupByGroupNumber(groupNumber);
        if (group == null)
            return -1;

        SubGroup subGroup = group.getSubGroup(subGroupNumber);
        if (subGroup == null)
            return -1;

        return subGroup.getIssuedLabsList().size();
    }

    private void issueLab(SubGroup subGroup,
                          UniversityClass universityClassOfIssue
    ) throws HibernateShellQueryException {
        Lab lab = hibernateCore.getLabsKeeper().getLabByNumber(subGroup.getIssuedLabsList().size() + 1);
        IssuedLab issuedLab = new IssuedLab();

        issuedLab.setLabDescription(lab);
        issuedLab.setUniversityClassOfIssue(universityClassOfIssue);
        issuedLab.setCoefficientOfCurrentDeadline(1.0);
        //TODO getNextLab
        issuedLab.setCurrentDeadline(universityClassOfIssue);
        issuedLab.setDateOfLastRepoCheck(new Date());
        issuedLab.setStudentControlList(subGroup.getStudentsList());
        issuedLab.setSubGroup(subGroup);

        hibernateCore.save(issuedLab);

        subGroup.addIssuedLab(issuedLab);

        for (Student student : subGroup.getStudentsList()) {
            LabMark labMark = new LabMark();
            labMark.setIssuedLab(issuedLab);
            labMark.setStudent(student);
            hibernateCore.save(labMark);
//
//            student.addLabMark(labMark);
        }
    }

    public void addLab(String keyWord) throws HibernateShellQueryException {
        Lab lab = new Lab();
        lab.setKeyWord(keyWord);
        lab.setNumberOfLab(getNumberOfNextLab());

        hibernateCore.save(lab);
    }

    public void updateLabMark(Integer id, Integer mark) throws HibernateShellQueryException {
        LabMark labMark = hibernateCore.getLabMarkById(id);

        if (labMark != null) {
            labMark.setMark(mark);
            hibernateCore.update(labMark);
        }
    }

    public void updateLabComment(Integer id, String comment) throws HibernateShellQueryException {
        LabMark labMark = hibernateCore.getLabMarkById(id);

        if (labMark != null) {
            labMark.setComment(comment);
            hibernateCore.update(labMark);
        }
    }

    public void updateLabComment(String id, String comment) throws HibernateShellQueryException {
        LabMark labMark = hibernateCore.getLabMarkById(Integer.getInteger(id));

        if (labMark != null) {
            labMark.setComment(comment);
            hibernateCore.update(labMark);
        }
    }

    public void updateLabCoefficient(Integer id, Double coeff) throws HibernateShellQueryException {
        LabMark labMark = hibernateCore.getLabMarkById(id);

        if (labMark != null) {
            labMark.setCoefficient(coeff);
            hibernateCore.update(labMark);
        }
    }

    public String getLabMarkComment(String id) throws HibernateShellQueryException {
        LabMark labMark = hibernateCore.getLabMarkById(Integer.getInteger(id));

        if(labMark != null){
            return labMark.getComment();
        }

        return "";
    }

    public Integer getNumberOfNextLab() throws HibernateShellQueryException {
        return (int) (hibernateCore.getNumberOfLab() + 1);
    }

    public void deleteLabDescription(Lab lab) {
        HibernateCore hibernateCore = HibernateCore.getInstance();
        hibernateCore.delete(lab);
    }

    public void deleteIssuedLab(IssuedLab issuedLab) {
        HibernateCore hibernateCore = HibernateCore.getInstance();
        hibernateCore.delete(issuedLab);
    }

    public void deleteLabDescription(Integer labNumber) {
        HibernateCore hibernateCore = HibernateCore.getInstance();
        Lab lab = null;
        try {
            lab = hibernateCore.getLabByNumber(labNumber);
        } catch (HibernateShellQueryException e) {
            e.printStackTrace();
        }

        deleteLabDescription(lab);
    }


}
