package resources.Controllers;

import resources.Hibernate.Controller.DataBaseCore;
import resources.Hibernate.Exceptions.DataBaseQueryException;
import resources.Hibernate.Interfaces.DataBaseCoreInterface;
import resources.Controllers.Interfaces.DefaultControllerInterface;

import java.util.ArrayList;
import java.util.List;

public class DefaultController implements DefaultControllerInterface {
    protected DataBaseCoreInterface dataBaseCore;

    public DefaultController() {
        dataBaseCore = DataBaseCore.getInstance();
    }

    public DefaultController(DataBaseCoreInterface core){
        dataBaseCore = core;
    }

    @Override
    public Object saveToDataBase(Object object) throws DataBaseQueryException {
        return dataBaseCore.create(object);
    }

    @Override
    public void updateInDataBase(Object object) throws DataBaseQueryException {
        dataBaseCore.update(object);
    }

    @Override
    public void removeFromDataBase(Object object) throws DataBaseQueryException {
        dataBaseCore.delete(object);
    }

    @Override
    public List<Object> getAll(Class c) throws DataBaseQueryException {

        List<Object> answer = new ArrayList<>();
        List<Object> a = dataBaseCore.getAll(c);

        for(Object o : a) {
            answer.add((Object)o);
        }

        return answer;
    }

    @Override
    public Object getById(Class c, Integer id) throws DataBaseQueryException {
        return (Object) dataBaseCore.getById(c, id);
    }
}
