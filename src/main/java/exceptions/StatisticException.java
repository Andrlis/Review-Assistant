package exceptions;

public class StatisticException extends Exception {

    public StatisticException() {}

    public StatisticException(String message) {
        super(message);
    }

    public StatisticException(Throwable cause) {
        super(cause);
    }

    public StatisticException(String message, Throwable cause) {
        super(message, cause);
    }
}