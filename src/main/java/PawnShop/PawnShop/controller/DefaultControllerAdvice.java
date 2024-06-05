package PawnShop.PawnShop.controller;

import PawnShop.PawnShop.exception.UserAlreadyExistsException;
import PawnShop.PawnShop.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@Order
@Slf4j
@ResponseBody
@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse usernameNotFoundException(HttpServletRequest request, UsernameNotFoundException exception) {
        log.warn("Exception in controller", exception);
        return ErrorResponse.builder()
                .status(NOT_FOUND.value())
                .error(exception.getMessage())
                .build();
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ErrorResponse userAlreadyExistsException(HttpServletRequest request, UserAlreadyExistsException exception) {
        log.warn("Exception in controller", exception);
        return ErrorResponse.builder()
                .status(CONFLICT.value())
                .error(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse generalThrowable(HttpServletRequest request, Throwable throwable) {
        log.warn("Exception in controller", throwable);
        return ErrorResponse.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .error(throwable.getMessage())
                .build();
    }
}
