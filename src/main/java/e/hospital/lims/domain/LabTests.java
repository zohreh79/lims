package e.hospital.lims.domain;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LabTests {

    @Column(name = "test_name")
    private String testName;

    @Column(name = "normal_range")
    private String range;

}
