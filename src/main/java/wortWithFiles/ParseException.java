package wortWithFiles;

public class ParseException extends Exception {
    /**
     * @param message - error message
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * @param exception - thrown exception
     */
    public ParseException(Exception exception) {
        super(exception);
    }
}
