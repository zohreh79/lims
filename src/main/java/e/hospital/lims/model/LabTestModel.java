package e.hospital.lims.model;

import e.hospital.lims.domain.LabTests;
import e.hospital.lims.domain.TestStatus;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LabTestModel extends PatientInfo{

    private List<LabTests> testFields;
    private ArrayList<Integer> selectedTestFields;
    private String description;
    private TestStatus testStatus;

}
