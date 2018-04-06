package logics;

import data.Student;
import data.UniversityClass;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.mark.LabMark;
import dao.DataBaseCore;
import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;
import dao.DataBaseCoreInterface;

import java.util.Date;

public class LabLogic {
    private DataBaseCoreInterface dataBaseCore;

    public LabLogic() {
        dataBaseCore = DataBaseCore.getInstance();
    }

    public LabLogic(DataBaseCoreInterface core) {
        dataBaseCore = core;
    }

    public Integer getNextNumber() throws DataBaseQueryException {
        return dataBaseCore.getCount(Lab.class) + 1;
    }

    public Lab getByNumber(String number) throws DataBaseQueryException, DataBaseCriteriaCountException {
        return (Lab) dataBaseCore.getByCriteria(Lab.class, "numberOfLab", number);
    }

    public Lab addNewLab() throws DataBaseQueryException {
        Lab lab = new Lab();
        lab.setKeyWord("lab" + getNextNumber());
        lab.setNumberOfLab(getNextNumber());

        dataBaseCore.create(lab);

        return lab;
    }

    public void issue(SubGroup subGroup, UniversityClass universityClass) throws DataBaseQueryException, DataBaseCriteriaCountException {
        Lab lab;
        if (getNextNumber() == subGroup.getIssuedLabsList().size() + 1) {
            lab = this.addNewLab();
        } else {
            Integer labNumber = subGroup.getIssuedLabsList().size();
            lab = this.getByNumber(labNumber.toString());
        }

        IssuedLab issuedLab = new IssuedLab();

        issuedLab.setLabDescription(lab);
        issuedLab.setUniversityClassOfIssue(universityClass);
        issuedLab.setCoefficientOfCurrentDeadline(1.0);
        //TODO getNextLab
        issuedLab.setCurrentDeadline(universityClass);
        issuedLab.setDateOfLastRepoCheck(new Date());
        issuedLab.setStudentControlList(subGroup.getStudentsList());
        issuedLab.setSubGroup(subGroup);


        dataBaseCore.create(issuedLab);

        subGroup.addIssuedLab(issuedLab);

        for (Student student : subGroup.getStudentsList()) {
            LabMark labMark = new LabMark();
            labMark.setIssuedLab(issuedLab);
            labMark.setStudent(student);
            dataBaseCore.create(labMark);
        }

    }

    public void issue(String groupNumber, String subGroupNumber, String data) throws DataBaseQueryException, DataBaseCriteriaCountException {
        GroupLogic groupLogic = new GroupLogic(dataBaseCore);

        Group group = null;

        group = groupLogic.getByNumber(groupNumber);

        SubGroup subGroup = group.getSubGroup(subGroupNumber);

        for(Object object : dataBaseCore.getAll(UniversityClass.class)) {
            UniversityClass universityClass = (UniversityClass) object;

            if(universityClass.getDataTime().equals(data)){
                if(universityClass.getSubGroup().equals(subGroup)){
                    issue(subGroup, universityClass);
                    return;
                }
            }
        }

    }
}