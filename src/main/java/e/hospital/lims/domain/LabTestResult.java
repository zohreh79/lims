package e.hospital.lims.domain;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "lab_test_result")
public class LabTestResult extends LabTests {

    @Id
    @Column(name = "result_id")
    private Long resultId;

    @Column(name = "description")
    private String description;

    @Column(name = "test_status")
    private String testStatus;

    @Column(name = "result")
    private String result;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private Doctor doctor;
}
