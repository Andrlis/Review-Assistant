package resources.Hibernate.Interfaces;

import data.сomment.Comment;

public interface DataBaseCoreInterface {

    public Object getById(Class c, Integer id);

    public Object create(Object object);
    public Object update(Object object);
    public void delete(Object object);

    public Integer getCount(Class c);

    public Object getByCriteria(Class c, Object ... criteria);

}
