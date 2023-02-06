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
@Table(name = "doctors")
public class Doctor {

    @Id
    @Column(name = "dr_id")
    private Long DoctorId;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "doctor")
    private User user;

    @OneToMany
    @JoinColumn(name = "result_id")
    private List<LabTestResult> items;
}
