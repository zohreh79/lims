package e.hospital.lims.service.Errors;


public class NotFound extends RuntimeException {

    public NotFound(String message) {
        super(message);
    }

}
