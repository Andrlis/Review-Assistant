package resources.Hibernate.Interfaces;

import data.UniversityClass;

public interface LabTestDataBaseInterface<T> extends DefaultDataBaseInterface<T> {
    public Integer getNextNumber();
    public Integer getNumberOfIssued();
    public void issued(Object object, UniversityClass universityClass);
}
