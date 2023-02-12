package e.hospital.lims.service.impl;

import e.hospital.lims.dao.*;
import e.hospital.lims.domain.*;
import e.hospital.lims.model.CurrentUser;
import e.hospital.lims.model.LabTestModel;
import e.hospital.lims.model.Result;
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

    @Autowired
    private TestFieldsDao testFieldsDao;

    @Autowired
    private LabTestResultDao labTestResultDao;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private DoctorDao doctorDao;

    @Override
    public List<LabTestModel> getAllTests() {
        try {
            LabTestModel testModel = new LabTestModel();
            List<LabTestModel> labTestModelList = new ArrayList<>();
            labTestResultDao.findAll().forEach(
                    labTestResult -> {
                        testModel.setTestFields(labTestResult.getTestFields());
                        testModel.setDescription(labTestResult.getDescription());
                        testModel.setTestStatus(labTestResult.getTestStatus());
                        testModel.setResultId(labTestResult.getResultId());

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

        LabTestResult labTestResult = labTestResultDao
                .findByPatient(patient);
        if (labTestResult == null) throw new NotFound("Test result not found!");

        LabTestModel testModel = new LabTestModel();
        testModel.setResultId(labTestResult.getResultId());
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
        List<LabTests> labTests = new ArrayList<>();
        for (String testField : model.getSelectedTestFields()) {
            var fields = testFieldsDao.findByTestName(testField);
            if (fields != null) {
                labTests.add(LabTests.builder()
                        .testFields(fields)
                        .build());
            }
        }
        Patient patient = patientDao.findByPatientName(model.getPatientName());
        if (patient == null) {
            patient = Patient.builder()
                    .patientName(model.getPatientName())
                    .age(model.getAge())
                    .initialSymptoms(model.getInitialSymptoms())
                    .gender(model.getGender())
                    .build();
            patientDao.save(patient);
        }
        Doctor doctor = doctorDao.findById(currentUser.getDoctorId())
                .orElseThrow(() -> new NotFound("Doctor id invalid"));

        LabTestResult labTestResult = LabTestResult.builder()
                .testFields(labTests)
                .testStatus(model.getTestStatus())
                .description(model.getDescription())
                .doctor(doctor)
                .patient(patient)
                .build();

        labTestResultDao.save(labTestResult);

    }

    @Override
    public void setTestResult(LabTestModel model) {
        LabTestResult labTestResult =
                labTestResultDao.findById(model.getResultId())
                        .orElseThrow(() -> new NotFound("Test not found"));

        List<LabTests> labTestsList = new ArrayList<>();
        for (Result result : model.getResults()) {
            TestFields testFields = testFieldsDao.findByTestName(result.getTestName());

            LabTests labTests = labTestDao.findByTestFields(testFields);
            if (labTests == null) throw new NotFound("Test not found");

            labTests.setTestResult(result.getResult());
            labTestsList.add(labTests);
        }

        labTestResult.setTestFields(labTestsList);
        labTestResult.setDescription(model.getDescription());
        labTestResult.setTestStatus(model.getTestStatus());

        labTestResultDao.save(labTestResult);
    }


    @Override
    public void updateTestStatus(Long resultId, TestStatus testStatus) {
        LabTestResult labTestResult =
                labTestResultDao.findById(resultId)
                        .orElseThrow(() -> new NotFound("Test not found"));
        labTestResult.setTestStatus(testStatus);

        labTestResultDao.save(labTestResult);
    }
}
