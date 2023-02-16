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

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<LabTestModel> getAllTests() {
        try {
            LabTestModel testModel = new LabTestModel();
            List<LabTestModel> labTestModelList = new ArrayList<>();
            labTestResultDao.findAll().forEach(
                    labTestResult -> {
                        testModel.setDescription(labTestResult.getDescription());
                        testModel.setTestStatus(labTestResult.getTestStatus());
                        testModel.setResultId(labTestResult.getResultId());

                        Patient patient = patientDao
                                .findByPatientId(labTestResult.getPatientId());
                        if (patient == null) throw new NotFound("Patient not found!");

                        testModel.setPatientId(patient.getPatientId());
                        testModel.setGender(patient.getGender());
                        testModel.setInitialSymptoms(patient.getInitialSymptoms());
                        testModel.setPatientName(patient.getPatientName());
                        testModel.setAge(patient.getAge());

                        List<LabTests> labTests = labTestDao.findByResultId(labTestResult.getResultId());
                        if (labTests == null) throw new NotFound("Results not found");

                        List<Result> results = new ArrayList<>();
                        for (LabTests result : labTests) {
                            TestFields testFields = testFieldsDao.findByFieldId(result.getFieldsId());
                            if (testFields == null) throw new NotFound("Field not found");

                            results.add(new Result(
                                    testFields.getTestName()
                                    , result.getTestResult()
                                    , testFields.getMaleRange()
                                    , testFields.getFemaleRange()
                            ));
                        }
                        testModel.setResults(results);

                        labTestModelList.add(testModel);
                    }
            );
            return labTestModelList;
        } catch (Exception e) {
            throw new BadRequest("Error on finding tests => " + e.getMessage());
        }
    }

    @Override
    public LabTestModel getTestResult(Long patientId) {
        Patient patient = patientDao
                .findByPatientId(patientId);
        if (patient == null) throw new NotFound("Patient not found!");

        LabTestResult labTestResult = labTestResultDao
                .findByPatientId(patient.getPatientId());
        if (labTestResult == null) throw new NotFound("Test result not found!");

        LabTestModel testModel = new LabTestModel();
        testModel.setResultId(labTestResult.getResultId());
        testModel.setPatientId(patientId);
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

        LabTestResult labTestResult = new LabTestResult();

        labTestResult.setTestStatus(model.getTestStatus());
        labTestResult.setDescription(model.getDescription());

        labTestResult.setDoctorId(doctor.getDoctorId());
        labTestResult.setPatientId(patient.getPatientId());

        LabTests labTests = new LabTests();
        for (String testField : model.getSelectedTestFields()) {
            var fields = testFieldsDao.findByTestName(testField);
            if (fields != null) {
                LabTests.builder()
                        .fieldsId(fields.getFieldId())
                        .resultId(labTestResult.getResultId())
                        .build();
                labTestDao.save(labTests);
            }
        }
        labTestResultDao.save(labTestResult);
        NotificationModel notificationModel = NotificationModel.builder()
                .message(model.getDescription())
                .patientId(patient.getPatientId())
                .receiver(doctor.getName())
                .title("new Test")
                .topic(doctor.getName())
                .build();
        sendNotification(notificationModel);
    }

    @Override
    public void setTestResult(LabTestModel model) {
        LabTestResult labTestResult =
                labTestResultDao.findByResultId(model.getResultId());

        if (labTestResult == null) throw new NotFound("Test not found");

        for (Result result : model.getResults()) {
            TestFields testFields = testFieldsDao.findByTestName(result.getTestName());

            LabTests labTests = new LabTests();
            labTests.setTestResult(result.getResult());
            labTests.setResultId(labTestResult.getResultId());
            labTests.setFieldsId(testFields.getFieldId());
            labTestDao.save(labTests);
        }

        labTestResult.setDescription(model.getDescription());
        labTestResult.setTestStatus(model.getTestStatus());

        labTestResultDao.save(labTestResult);

        Doctor doctor = doctorDao.findById(currentUser.getDoctorId())
                .orElseThrow(() -> new NotFound("Doctor id invalid"));
        NotificationModel notificationModel = NotificationModel.builder()
                .message(model.getDescription())
                .patientId(labTestResult.getPatientId())
                .receiver(doctor.getName())
                .title("new Test result")
                .topic(doctor.getName())
                .build();
        sendNotification(notificationModel);
    }


    @Override
    public void updateTestStatus(UpdateStatusModel model) {
        LabTestResult labTestResult =
                labTestResultDao.findById(model.getResultId())
                        .orElseThrow(() -> new NotFound("Test not found"));
        labTestResult.setTestStatus(model.getTestStatus());
        labTestResultDao.save(labTestResult);
    }

    private void sendNotification(NotificationModel model) {
        notificationService.sendNotification(model);
    }
}
