package e.hospital.lims.controller;

import e.hospital.lims.model.UserRequestModel;
import e.hospital.lims.model.UserResponseModel;
import e.hospital.lims.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseModel> login(@RequestBody UserRequestModel model) {
        return ResponseEntity.ok(authenticationService.login(model));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseModel> register(@RequestBody UserRequestModel model) {
        return ResponseEntity.ok(authenticationService.register(model));
    }


}
