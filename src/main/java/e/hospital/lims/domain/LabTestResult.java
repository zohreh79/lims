package e.hospital.lims.domain;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private Doctor doctor;

    @OneToMany
    private List<LabTests> testFields;
}
