package resources.Hibernate.Interfaces;

import data.Student;
import data.UniversityClass;

public interface StudentControllerInterface {
    public void notePresence(Student student, UniversityClass universityClass);
    public void noteAbsent(Student student, UniversityClass universityClass);
}
