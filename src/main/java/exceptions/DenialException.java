package exceptions;

public class DenialException extends RuntimeException {
    public DenialException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
