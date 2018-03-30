package resources.Hibernate.Shells;

import resources.Hibernate.HibernateShellQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;
import resources.Hibernate.Interfaces.DefaultDataBaseInterface;

public class DefaultDataBaseShell<T extends Object> implements DefaultDataBaseInterface<T> {
    private DataBaseCoreInterface dataBaseCore;
    private Class<T> genericType;

    public DefaultDataBaseShell(Class<T> type, DataBaseCoreInterface core){
        dataBaseCore = core;
        genericType = type;
    }

    @Override
    public T getById(Integer id) throws HibernateShellQueryException {
        return (T) dataBaseCore.getById(genericType, id);
    }

    @Override
    public T create(T object) {
        return (T) dataBaseCore.create(object);
    }

    @Override
    public T update(T object) {
        return (T) dataBaseCore.update(object);
    }

    @Override
    public void delete(T object) {
        dataBaseCore.delete(object);
    }
}
