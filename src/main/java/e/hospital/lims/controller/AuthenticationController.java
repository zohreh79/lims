package e.hospital.lims.controller;

import e.hospital.lims.model.DrModel;
import e.hospital.lims.model.UserRequestModel;
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
    public ResponseEntity<?> login(@RequestBody UserRequestModel model) {
        return authenticationService.login(model);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestModel model) {
        return authenticationService.register(model);
    }

    @PostMapping("/profile")
    public ResponseEntity<?> profile(@RequestBody DrModel model) {
        return authenticationService.profile(model);
    }


}
