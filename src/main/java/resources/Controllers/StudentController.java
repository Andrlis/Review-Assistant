package resources.Controllers;

import data.Student;
import data.UniversityClass;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

public class StudentController extends DefaultController {
    public StudentController() {
        super();
    }

    public StudentController(DataBaseCoreInterface core) {
        super(core);
    }

    public void notePresence(Student object, UniversityClass universityClass) throws DataBaseQueryException {
        object.addMissedClass(universityClass);
        updateInDataBase(object);
    }

    public void noteAbsent(Student object, UniversityClass universityClass) throws DataBaseQueryException {
        object.removeMissedClass(universityClass);
        updateInDataBase(object);
    }
}
