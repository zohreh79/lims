package e.hospital.lims.controller;

import e.hospital.lims.service.Errors.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<?> badRequest(BadRequest badRequest) {
        return new ResponseEntity<>(badRequest.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<?> notFound(NotFound notFound) {
        return new ResponseEntity<>(notFound.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity<?> unauthorized(Unauthorized unauthorized) {
        return new ResponseEntity<>(unauthorized.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Conflict.class)
    public ResponseEntity<?> conflict(Conflict conflict) {
        return new ResponseEntity<>(conflict.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotAcceptable.class)
    public ResponseEntity<?> notAcceptable(NotAcceptable notAcceptable) {
        return new ResponseEntity<>(notAcceptable.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Forbidden.class)
    public ResponseEntity<?> forbidden(Forbidden forbidden) {
        return new ResponseEntity<>(forbidden.getMessage(), HttpStatus.NOT_FOUND);
    }
}
