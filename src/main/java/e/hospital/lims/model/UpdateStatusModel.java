package e.hospital.lims.model;

import e.hospital.lims.domain.TestStatus;
import lombok.Data;

@Data
public class UpdateStatusModel {

    private Long resultId;
    private TestStatus testStatus;
}
