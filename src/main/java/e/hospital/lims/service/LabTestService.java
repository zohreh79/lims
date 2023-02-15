package e.hospital.lims.service;

import e.hospital.lims.domain.TestStatus;
import e.hospital.lims.model.LabTestModel;
import e.hospital.lims.model.UpdateStatusModel;

import java.util.List;

public interface LabTestService {

    List<LabTestModel> getAllTests();

    LabTestModel getTestResult(Long patientId);

    void setTest(LabTestModel model);

    void setTestResult(LabTestModel model);

    void updateTestStatus(UpdateStatusModel model);


}
