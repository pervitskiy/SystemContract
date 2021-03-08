package exception;

public class MyInjectException extends Exception {
    public MyInjectException() {
    }

    public MyInjectException(String message) {
        super(message);
    }

    public MyInjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyInjectException(Throwable cause) {
        super(cause);
    }
}
