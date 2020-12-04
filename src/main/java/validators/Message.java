package validators;

public class Message {
    private String message;
    private ValidationStatus status;

    public String getMessage() {
        return message;
    }

    public ValidationStatus getStatus() {
        return status;
    }

    public Message(String message, ValidationStatus status) {
        this.message = message;
        this.status = status;
    }

    public Message(ValidationStatus status) {
        this.status = status;
    }



}
