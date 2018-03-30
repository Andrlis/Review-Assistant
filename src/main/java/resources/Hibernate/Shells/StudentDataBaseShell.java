package resources.Hibernate.Shells;

import data.Student;
import data.UniversityClass;
import resources.Hibernate.HibernateCore;
import resources.Hibernate.Interfaces.AbsencesDataBaseInterface;

public class StudentDataBaseShell implements AbsencesDataBaseInterface<Student> {
    private HibernateCore hibernateCore;

    public StudentDataBaseShell(){

    }

    @Override
    public void notePresence(Student object, UniversityClass universityClass) {

    }

    @Override
    public void noteAbsent(Student object, UniversityClass universityClass) {

    }

    @Override
    public Student getById(Integer id) {

        return null;
    }

    @Override
    public Student create(Student object) {
        return null;
    }

    @Override
    public Student update(Student object) {
        return null;
    }

    @Override
    public Student delete(Student object) {
        return null;
    }
}
