package e.hospital.lims.model;

import e.hospital.lims.domain.LabTests;
import e.hospital.lims.domain.TestStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LabTestModel extends PatientInfo{

    @NotEmpty
    private Long resultId;

    private List<Result> results;
    private ArrayList<String> selectedTestFields;
    private String description;
    private TestStatus testStatus;

}
