package e.hospital.lims.dao;

import e.hospital.lims.domain.LabTestResult;
import e.hospital.lims.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestResultDao extends JpaRepository<LabTestResult, Long> {
    LabTestResult findByPatient(Patient patient);
}
