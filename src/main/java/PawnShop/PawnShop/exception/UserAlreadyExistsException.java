package PawnShop.PawnShop.exception;

import org.aspectj.bridge.IMessage;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
