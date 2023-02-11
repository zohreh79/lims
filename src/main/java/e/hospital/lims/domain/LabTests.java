package e.hospital.lims.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "test_fields")
public class LabTests {

    @Id
    @Column(name = "field_id")
    private Long fieldId;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "normal_range")
    private String range;

    @Column(name = "result")
    private String result;

    @ManyToOne
    @JoinColumn(name = "result_id", insertable = false, updatable = false)
    private LabTestResult results;

}
