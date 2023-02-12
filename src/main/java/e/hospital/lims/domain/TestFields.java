package e.hospital.lims.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "test_fields")
public class TestFields {

    @Id
    @Column(name = "field_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long fieldId;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "normal_range")
    private String range;

    @OneToMany
    private List<LabTests> testFieldsResult;
}
