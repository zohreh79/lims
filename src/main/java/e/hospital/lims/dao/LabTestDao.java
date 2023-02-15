package e.hospital.lims.dao;

import e.hospital.lims.domain.LabTests;
import e.hospital.lims.domain.TestFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabTestDao extends JpaRepository<LabTests,Long> {

    LabTests findByFieldsId(Long testFields);

    List<LabTests> findByResultId(Long resultId);


}
