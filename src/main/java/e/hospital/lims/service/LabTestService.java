package e.hospital.lims.service;

import e.hospital.lims.domain.TestStatus;
import e.hospital.lims.model.LabTestModel;

import java.util.List;

public interface LabTestService {

    List<LabTestModel> getAllTests();

    LabTestModel getTestResult(Long patientId);

    void setTest(LabTestModel model);

    void setTestResult(LabTestModel model);

    void updateTestStatus(Long resultId, TestStatus testStatus);


}
