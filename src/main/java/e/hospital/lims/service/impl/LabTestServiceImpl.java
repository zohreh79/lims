package e.hospital.lims.service.impl;

import e.hospital.lims.dao.LabTestDao;
import e.hospital.lims.dao.PatientDao;
import e.hospital.lims.domain.LabTestResult;
import e.hospital.lims.domain.Patient;
import e.hospital.lims.model.LabTestModel;
import e.hospital.lims.service.Errors.BadRequest;
import e.hospital.lims.service.Errors.NotFound;
import e.hospital.lims.service.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabTestServiceImpl implements LabTestService {

    @Autowired
    private LabTestDao labTestDao;

    @Autowired
    private PatientDao patientDao;

    @Override
    public List<LabTestModel> getAllTests() {
        try {
            LabTestModel testModel = new LabTestModel();
            List<LabTestModel> labTestModelList = new ArrayList<>();
            labTestDao.findAll().forEach(
                    labTestResult -> {
                        testModel.setTestFields(labTestResult.getTestFields());
                        testModel.setDescription(labTestResult.getDescription());
                        testModel.setTestStatus(labTestResult.getTestStatus());

                        Patient patient = patientDao
                                .findByPatientId(labTestResult.getPatient().getPatientId());
                        if (patient == null) throw new NotFound("Patient not found!");

                        testModel.setGender(patient.getGender());
                        testModel.setInitialSymptoms(patient.getInitialSymptoms());
                        testModel.setPatientName(patient.getPatientName());
                        testModel.setAge(patient.getAge());

                        labTestModelList.add(testModel);
                    }
            );
            return labTestModelList;
        } catch (Exception e) {
            throw new BadRequest("Error on finding tests" + e.getMessage());
        }
    }

    @Override
    public LabTestModel getTestResult(Long patientId) {
        Patient patient = patientDao
                .findByPatientId(patientId);
        if (patient == null) throw new NotFound("Patient not found!");

        LabTestResult labTestResult = labTestDao
                .findByPatient(patient);
        if (labTestResult == null) throw new NotFound("Test result not found!");

        LabTestModel testModel = new LabTestModel();
        testModel.setTestFields(labTestResult.getTestFields());
        testModel.setDescription(labTestResult.getDescription());
        testModel.setTestStatus(labTestResult.getTestStatus());
        testModel.setGender(patient.getGender());
        testModel.setInitialSymptoms(patient.getInitialSymptoms());
        testModel.setPatientName(patient.getPatientName());
        testModel.setAge(patient.getAge());

        return testModel;
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
