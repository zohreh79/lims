package e.hospital.lims.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "lab_test_result")
public class LabTestResult {

    @Id
    @Column(name = "result_id")
    private Long resultId;

    @Column(name = "description")
    private String description;

    @Column(name = "test_status")
    private String testStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LabTests> labTests;

}
