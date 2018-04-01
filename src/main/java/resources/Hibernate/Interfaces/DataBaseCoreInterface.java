package resources.Hibernate.Interfaces;

import data.сomment.Comment;
import resources.Hibernate.Exceptions.DataBaseCriteriaCountException;
import resources.Hibernate.Exceptions.DataBaseQueryException;

import java.util.List;

public interface DataBaseCoreInterface {

    public Object getById(Class c, Integer id) throws DataBaseQueryException;

    public Object create(Object object) throws DataBaseQueryException ;
    public Object update(Object object) throws DataBaseQueryException ;
    public void delete(Object object) throws DataBaseQueryException ;

    public Integer getCount(Class c) throws DataBaseQueryException ;

    public Object getByCriteria(Class c, Object ... criteria) throws DataBaseQueryException, DataBaseCriteriaCountException;
    public Integer getNumberCriteria(Class c, Object ... criteria) throws DataBaseQueryException, DataBaseCriteriaCountException;
    public List<Object> getAll(Class c) throws DataBaseQueryException ;
}
