package e.hospital.lims.domain;

import jakarta.persistence.*;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "lab_test_result")
public class LabTestResult {

    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @Column(name = "description")
    private String description;

    @Column(name = "test_status")
    @Enumerated(EnumType.ORDINAL)
    private TestStatus testStatus;

    @JoinColumn(name = "patient_id")
    private Long patientId;

    @JoinColumn(name = "doctor_id")
    private Long doctorId;

}
