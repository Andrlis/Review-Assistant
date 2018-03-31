package resources.Controllers;

import data.UniversityClass;
import data.group.SubGroup;
import data.lab.Lab;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

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

    public void issue(SubGroup subGroup, UniversityClass universityClass) {
        //TODO
    }
}
