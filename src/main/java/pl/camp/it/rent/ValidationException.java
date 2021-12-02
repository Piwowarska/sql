package pl.camp.it.rent;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
