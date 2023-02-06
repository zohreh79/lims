package e.hospital.lims.controller;

import e.hospital.lims.model.NotificationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class NotificationController {

    @GetMapping()
    public ResponseEntity<NotificationModel> getMessage(){
        return null;
    }
}
