package e.hospital.lims.controller;

import e.hospital.lims.domain.Notification;
import e.hospital.lims.model.NotificationModel;
import e.hospital.lims.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping()
    public ResponseEntity<List<Notification>> getMessages() {
        return ResponseEntity.ok(notificationService.getNotificationList());
    }

    @PostMapping()
    public ResponseEntity<String> sendMessage(@RequestBody NotificationModel notificationModel) {
        notificationService.sendNotification(notificationModel);
        return ResponseEntity.ok("message sent!");
    }
}
