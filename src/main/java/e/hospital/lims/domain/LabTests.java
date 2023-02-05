package e.hospital.lims.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lab_tests")
public class LabTests {

    @Id
    @Column(name = "test_id")
    private Long testId;

    @Column(name = "test")
    private String test;

    @Column(name = "normal_range")
    private String range;

    @Column(name = "result")
    private String result;
}
