package e.hospital.lims.dao;

import e.hospital.lims.domain.TestFields;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestFieldsDao extends CrudRepository<TestFields, Long> {

    TestFields findByTestName(String testName);

    TestFields findByFieldId(Long fieldId);
}
