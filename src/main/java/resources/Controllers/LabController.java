package resources.Controllers;

import data.Student;
import data.UniversityClass;
import data.group.Group;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.mark.LabMark;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

import java.util.Date;

public class LabController extends DefaultController{
    public LabController() {
        super();
    }

    public LabController(DataBaseCoreInterface core) {
        super(core);
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

        saveToDataBase(lab);

        return lab;
    }

    public void issue(SubGroup subGroup, UniversityClass universityClass) throws DataBaseQueryException, DataBaseCriteriaCountException {
        DefaultController defaultController = new DefaultController(dataBaseCore);

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


        defaultController.saveToDataBase(issuedLab);

        subGroup.addIssuedLab(issuedLab);

        for (Student student : subGroup.getStudentsList()) {
            LabMark labMark = new LabMark();
            labMark.setIssuedLab(issuedLab);
            labMark.setStudent(student);
            defaultController.saveToDataBase(labMark);
        }

    }

    public void issue(String groupNumber, String subGroupNumber, String data) throws DataBaseQueryException, DataBaseCriteriaCountException {
        GroupController groupController = new GroupController(dataBaseCore);
        DefaultController defaultController = new DefaultController(dataBaseCore);

        Group group = null;

        group = groupController.getByNumber(groupNumber);

        SubGroup subGroup = group.getSubGroup(subGroupNumber);

        for(Object object : defaultController.getAll(UniversityClass.class)) {
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
