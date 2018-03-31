package resources.Hibernate.Interfaces;

import resources.Hibernate.HibernateShellQueryException;

import java.util.List;

public interface DefaultControllerInterface<T> {
    public T saveToDataBase(T object);
    public T updateInDataBase(T object);
    public void removeFromDataBase(T object);

    public List<T> getAll();
    public T getById(Integer id) throws HibernateShellQueryException;
}
