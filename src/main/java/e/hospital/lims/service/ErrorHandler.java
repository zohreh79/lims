package e.hospital.lims.service;

import e.hospital.lims.service.Errors.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequest.class)
    public void badRequest(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "bad request");
    }

    @ExceptionHandler(NotFound.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "notFound");
    }

    @ExceptionHandler(Unauthorized.class)
    public void unauthorized(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
    }

    @ExceptionHandler(Conflict.class)
    public void conflict(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_CONFLICT, "conflict");
    }

    @ExceptionHandler(NotAcceptable.class)
    public void notAcceptable(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "notAcceptable");
    }

    @ExceptionHandler(Forbidden.class)
    public void forbidden(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "forbidden");
    }
}
