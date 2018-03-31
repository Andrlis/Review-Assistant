package resources.Hibernate.Controller;

import data.Student;
import data.UniversityClass;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;
import resources.Hibernate.Interfaces.StudentControllerInterface;

public class StudentController extends DefaultController<Student> implements StudentControllerInterface {
    public StudentController(Class<Student> type) {
        super(type);
    }

    public StudentController(Class<Student> type, DataBaseCoreInterface core) {
        super(type, core);
    }

    @Override
    public void notePresence(Student object, UniversityClass universityClass) {
        object.addMissedClass(universityClass);
        updateInDataBase(object);
    }

    @Override
    public void noteAbsent(Student object, UniversityClass universityClass) {
        object.removeMissedClass(universityClass);
        updateInDataBase(object);
    }
}
