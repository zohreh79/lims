package e.hospital.lims.controller;

import e.hospital.lims.domain.SystemRole;
import e.hospital.lims.model.UserRequestModel;
import e.hospital.lims.model.UserResponseModel;
import e.hospital.lims.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseModel> login(@RequestBody UserRequestModel model) {
        authenticate(model);
        return ResponseEntity.ok(UserResponseModel
                .from(authenticationService.generateAccessToken(model.getUsername())
                        , authenticationService.generateRefreshToken(model.getUsername()))
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseModel> register(@RequestBody UserRequestModel model) {
        return ResponseEntity.ok(authenticationService.register(model));
    }

    private SystemRole authenticate(UserRequestModel loginModel) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication == null) {
            throw new UsernameNotFoundException("");
        }

    }
}
