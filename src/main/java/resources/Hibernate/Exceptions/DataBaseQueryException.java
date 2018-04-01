package resources.Hibernate.Exceptions;

public class DataBaseQueryException extends Exception {
    public DataBaseQueryException() {
        super();
    }

    public DataBaseQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseQueryException(String message) {
        super(message);
    }

    public DataBaseQueryException(Throwable cause) {
        super(cause);
    }
}
