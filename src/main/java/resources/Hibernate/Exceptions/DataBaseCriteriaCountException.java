package resources.Hibernate.Exceptions;

public class DataBaseCriteriaCountException extends Exception{
    public DataBaseCriteriaCountException() {
        super();
    }

    public DataBaseCriteriaCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseCriteriaCountException(String message) {
        super(message);
    }

    public DataBaseCriteriaCountException(Throwable cause) {
        super(cause);
    }
}
