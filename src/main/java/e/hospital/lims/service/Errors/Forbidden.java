package e.hospital.lims.service.Errors;

public class Forbidden extends RuntimeException{

    public Forbidden(String message) {
        super(message);
    }
}
