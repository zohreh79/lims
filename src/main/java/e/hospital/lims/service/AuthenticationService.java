package e.hospital.lims.service;

import e.hospital.lims.domain.SystemRole;
import e.hospital.lims.model.UserRequestModel;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    String generateAccessToken(String username, SystemRole role);

    String generateRefreshToken(String username, SystemRole role);

    boolean isTokenValid(String token);

    ResponseEntity<?> register(UserRequestModel model);

    ResponseEntity<?> login(UserRequestModel model);

    Claims getAllClaimsFromToken(String token);
}
