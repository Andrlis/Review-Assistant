package resources.Hibernate.Interfaces;

public interface DefaultDataBaseInterface<T> {
    public T getById(Integer id);
    public T create(T object);
    public T update(T object);
    public T delete(T object);
}
