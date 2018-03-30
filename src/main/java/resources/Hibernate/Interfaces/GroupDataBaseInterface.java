package resources.Hibernate.Interfaces;

public interface GroupDataBaseInterface<T> extends DefaultDataBaseInterface<T> {
    public T getByNumber();
}
