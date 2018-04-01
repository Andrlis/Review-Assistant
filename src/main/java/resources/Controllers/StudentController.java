package resources.Controllers;

import data.Student;
import data.UniversityClass;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

public class StudentController extends DefaultController<Student> {
    public StudentController() {
        super(Student.class);
    }

    public StudentController(DataBaseCoreInterface core) {
        super(Student.class, core);
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
