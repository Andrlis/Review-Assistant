package resources.Controllers;

import data.Student;
import data.UniversityClass;
import data.group.SubGroup;
import data.lab.IssuedLab;
import data.lab.Lab;
import data.mark.LabMark;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

import java.util.Date;

public class LabController extends DefaultController<Lab>{
    public LabController() {
        super(Lab.class);
    }

    public LabController(DataBaseCoreInterface core) {
        super(Lab.class, core);
    }

    public Integer getNextNumber() {
        return dataBaseCore.getCount(Lab.class) + 1;
    }

    public Lab getByNumber(String number) {
        return (Lab) dataBaseCore.getByCriteria(Lab.class, "numberOfLab", number);
    }

    public Lab addNewLab(){
        Lab lab = new Lab();
        lab.setKeyWord("lab" + getNextNumber());
        lab.setNumberOfLab(getNextNumber());

        saveToDataBase(lab);

        return lab;
    }

    public void issue(SubGroup subGroup, UniversityClass universityClass) {
        DefaultController<IssuedLab> issuedLabController = new DefaultController<>(IssuedLab.class, dataBaseCore);
        DefaultController<LabMark> labMarkController = new DefaultController<>(LabMark.class, dataBaseCore);

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


        issuedLabController.saveToDataBase(issuedLab);

        subGroup.addIssuedLab(issuedLab);

        for (Student student : subGroup.getStudentsList()) {
            LabMark labMark = new LabMark();
            labMark.setIssuedLab(issuedLab);
            labMark.setStudent(student);
            labMarkController.saveToDataBase(labMark);
        }

    }
}
