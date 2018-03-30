package resources.Hibernate.Shells;

import data.Student;
import data.UniversityClass;
import resources.Hibernate.HibernateShellQueryException;
import resources.Hibernate.Interfaces.AbsencesDataBaseInterface;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;

public class StudentDataBaseShell extends DefaultDataBaseShell<Student> implements AbsencesDataBaseInterface<Student> {
    public StudentDataBaseShell(DataBaseCoreInterface core){
        super(Student.class, core);
    }

    @Override
    public void notePresence(Student object, UniversityClass universityClass) {
        object.addMissedClass(universityClass);
        update(object);
    }

    @Override
    public void noteAbsent(Student object, UniversityClass universityClass) {
        object.removeMissedClass(universityClass);
        update(object);
    }
}
