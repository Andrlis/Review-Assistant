package resources.Hibernate.Interfaces;

import data.UniversityClass;

public interface AbsencesDataBaseInterface<T> {
    public void notePresence(T object, UniversityClass universityClass);
    public void noteAbsent(T object, UniversityClass universityClass);
}
