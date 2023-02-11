package e.hospital.lims.dao;

import e.hospital.lims.domain.LabTestResult;
import e.hospital.lims.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestDao extends JpaRepository<LabTestResult,Long> {

    LabTestResult findByPatient(Patient patient);

}
