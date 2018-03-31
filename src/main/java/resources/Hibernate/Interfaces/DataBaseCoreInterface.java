package resources.Hibernate.Interfaces;

import data.сomment.Comment;
import resources.Hibernate.HibernateShellQueryException;

import java.util.List;

public interface DataBaseCoreInterface {

    public Object getById(Class c, Integer id) throws HibernateShellQueryException;

    public Object create(Object object);
    public Object update(Object object);
    public void delete(Object object);

    public Integer getCount(Class c);

    public Object getByCriteria(Class c, Object ... criteria);
    public Integer getNumberCriteria(Class c, Object ... criteria);
    public List<Object> getAll(Class c);
}
