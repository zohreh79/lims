package e.hospital.lims.service.impl;

import e.hospital.lims.dao.*;
import e.hospital.lims.domain.*;
import e.hospital.lims.model.*;
import e.hospital.lims.service.Errors.BadRequest;
import e.hospital.lims.service.Errors.NotFound;
import e.hospital.lims.service.LabTestService;
import e.hospital.lims.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabTestServiceImpl implements LabTestService {

    @Override
    public List<LabTestModel> getAllTests() {
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
    public void updateTestStatus(UpdateStatusModel model) {

    }
}
