package resources.Controllers;

import resources.Hibernate.Controller.DataBaseCore;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;
import resources.Controllers.Interfaces.DefaultControllerInterface;

import java.util.ArrayList;
import java.util.List;

public class DefaultController<T> implements DefaultControllerInterface<T> {
    protected DataBaseCoreInterface dataBaseCore;
    private Class<T> genericType;

    public DefaultController(Class<T> type) {
        dataBaseCore = DataBaseCore.getInstance();
        genericType = type;
    }

    public DefaultController(Class<T> type, DataBaseCoreInterface core){
        dataBaseCore = core;
        genericType = type;
    }

    @Override
    public T saveToDataBase(T object) throws DataBaseQueryException {
        return (T) dataBaseCore.create(object);
    }

    @Override
    public void updateInDataBase(T object) throws DataBaseQueryException {
        dataBaseCore.update(object);
    }

    @Override
    public void removeFromDataBase(T object) throws DataBaseQueryException {
        dataBaseCore.delete(object);
    }

    @Override
    public List<T> getAll() throws DataBaseQueryException {

        List<T> answer = new ArrayList<>();
        List<Object> a = dataBaseCore.getAll(genericType);

        for(Object o : a) {
            answer.add((T)o);
        }

        return answer;
    }

    @Override
    public T getById(Integer id) throws DataBaseQueryException {
        return (T) dataBaseCore.getById(genericType, id);
    }
}
