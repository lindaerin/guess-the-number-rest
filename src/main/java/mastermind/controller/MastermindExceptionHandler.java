package mastermind.controller;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mastermind.service.InvalidGuessException;

@ControllerAdvice
@RestController
public class MastermindExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String CONSTRAINT_MESSAGE = "Could not save your item. "
            + "Please ensure it is valid and try again.";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidGuessException.class)

    public final ResponseEntity<Error> handleInvalidGuessException(
            InvalidGuessException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
