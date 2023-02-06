package e.hospital.lims.service;

import e.hospital.lims.domain.SystemRole;
import e.hospital.lims.model.UserRequestModel;
import e.hospital.lims.model.UserResponseModel;

public interface AuthenticationService {

    String generateAccessToken(String username, SystemRole role);

    String generateRefreshToken(String username, SystemRole role);

    boolean isTokenValid(String token);

    UserResponseModel register(UserRequestModel model);
}
