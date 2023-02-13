package e.hospital.lims.service;

import java.io.Serial;

public class SharedException extends RuntimeException {

    public static final String BUNDLE_BASE_PATH = "com.payeshgaran.SharedBundle";

    @Serial
    private static final long serialVersionUID = -6285909385623767218L;

    public static final String UNAUTHORIZED = "unauthorized";
    public static final String BadRequest = "BadRequest";

    public SharedException(String badRequest) {
    }
}