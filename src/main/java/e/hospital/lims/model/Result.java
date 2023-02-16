package e.hospital.lims.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {

    private String testName;
    private String result;
    private String maleNormalRange;
    private String femaleNormalRange;

}
