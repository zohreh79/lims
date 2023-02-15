package e.hospital.lims.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fields_result")
public class LabTests {

    @Id
    @Column(name = "test_id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    @Column(name = "result")
    private String testResult;

    @JoinColumn(name = "result_id")
    @JsonIgnore
    private Long resultId;

    @JoinColumn(name = "fields_id")
    @JsonIgnore
    private Long fieldsId;

}


