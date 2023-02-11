package e.hospital.lims.model;

import e.hospital.lims.domain.Gender;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PatientInfo {

    @NotEmpty
    private String patientName;
    @NotEmpty
    private Gender gender;
    @NotEmpty
    private int age;
    @NotEmpty
    private String initialSymptoms;
}
