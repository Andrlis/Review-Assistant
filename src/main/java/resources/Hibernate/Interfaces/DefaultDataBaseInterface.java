package resources.Hibernate.Interfaces;

import resources.Hibernate.HibernateShellQueryException;

public interface DefaultDataBaseInterface<T> {
    public T getById(Integer id) throws HibernateShellQueryException;
    public T create(T object);
    public T update(T object);
    public void delete(T object);
}
