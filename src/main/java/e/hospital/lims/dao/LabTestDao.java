package e.hospital.lims.dao;

import e.hospital.lims.domain.LabTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestDao extends JpaRepository<LabTestResult,Long> {

    List<LabTestResult> findAll();


}
