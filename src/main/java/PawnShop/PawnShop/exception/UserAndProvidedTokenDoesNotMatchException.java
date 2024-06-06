package PawnShop.PawnShop.exception;

public class UserAndProvidedTokenDoesNotMatchException extends RuntimeException {
    public UserAndProvidedTokenDoesNotMatchException(String message) {
        super(message);
    }
}
