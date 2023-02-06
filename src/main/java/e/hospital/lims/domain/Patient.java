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
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "patient_name")
    private String patientName;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "age")
    private int age;

    @Column(name = "initial_symptoms")
    private String initialSymptoms;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LabTestResult> labTestResult;
}
