package e.hospital.lims.service.Errors;


public class Unauthorized extends RuntimeException {

    public Unauthorized(String message) {
        super(message);
    }

}
