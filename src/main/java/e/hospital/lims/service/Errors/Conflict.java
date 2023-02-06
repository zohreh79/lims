package e.hospital.lims.service.Errors;


public class Conflict extends RuntimeException {

    public Conflict(String message) {
        super(message);
    }

}
