package e.hospital.lims.service.Errors;


public class BadRequest extends RuntimeException {

    public BadRequest(String message) {
        super(message);
    }

}
