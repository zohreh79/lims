package e.hospital.lims.service.impl;

import e.hospital.lims.model.LabTestModel;
import e.hospital.lims.service.LabTestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTestServiceImpl implements LabTestService {

    @Override
    public List<LabTestModel> getAllTests(String topic) {
        return null;
    }

    @Override
    public LabTestModel getTestResult(Long patientId) {
        return null;
    }

    @Override
    public void setTest(LabTestModel model) {

    }

    @Override
    public void setTestResult(LabTestModel model) {

    }

    @Override
    public void updateTestResult(LabTestModel model) {

    }
}
