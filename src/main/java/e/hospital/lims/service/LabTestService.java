package e.hospital.lims.service;

import e.hospital.lims.model.LabTestModel;

import java.util.List;

public interface LabTestService {

    List<LabTestModel> getAllTests(String topic);

    LabTestModel getTestResult(Long patientId);

    void setTest(LabTestModel model);

    void setTestResult(LabTestModel model);

    void updateTestResult(LabTestModel model);


}
