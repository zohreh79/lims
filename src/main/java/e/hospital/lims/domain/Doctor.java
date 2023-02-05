package e.hospital.lims.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
