package e.hospital.lims.model;

import e.hospital.lims.domain.SystemRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestModel {

    private String username;
    private String password;
    private SystemRole loginAs;
}
