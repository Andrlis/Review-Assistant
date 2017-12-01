package exceptions;

public class GitException extends Exception {
    private static final long serialVersionUID = -2818899801515373436L;

    public GitException() {}

    public GitException(String message) {
        super(message);
    }

    public GitException(Throwable cause) {
        super(cause);
    }

    public GitException(String message, Throwable cause) {
        super(message, cause);
    }
}