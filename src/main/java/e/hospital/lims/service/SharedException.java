package e.hospital.lims.service;

import java.io.Serial;

public class SharedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6285909385623767218L;
    private final String errorMessage;

    public static final String UNAUTHORIZED = "unauthorized";
    public static final String BadRequest = "BadRequest";

    public SharedException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}