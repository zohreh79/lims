package e.hospital.lims.dao;

import e.hospital.lims.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {

    Patient findByPatientId(Long patientId);
}
