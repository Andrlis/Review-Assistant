package exceptions;

public class CheckException extends Exception {
    private static final long serialVersionUID = -2818899801515373436L;

    public CheckException() {}

    public CheckException(String message) {
        super(message);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }
}