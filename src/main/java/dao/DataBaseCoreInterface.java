package dao;

import exceptions.DataBaseCriteriaCountException;
import exceptions.DataBaseQueryException;

import java.util.List;

public interface DataBaseCoreInterface {

    Object getById(Class c, Integer id) throws DataBaseQueryException;

    Object create(Object object) throws DataBaseQueryException ;
    Object update(Object object) throws DataBaseQueryException ;
    void delete(Object object) throws DataBaseQueryException ;

    Integer getCount(Class c) throws DataBaseQueryException ;

    Object getByCriteria(Class c, Object ... criteria) throws DataBaseQueryException, DataBaseCriteriaCountException;
    Integer getNumberCriteria(Class c, Object ... criteria) throws DataBaseQueryException, DataBaseCriteriaCountException;
    List<Object> getAll(Class c) throws DataBaseQueryException ;
}
