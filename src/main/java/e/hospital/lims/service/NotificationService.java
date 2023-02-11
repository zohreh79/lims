package e.hospital.lims.service;

import e.hospital.lims.domain.Notification;
import e.hospital.lims.model.NotificationModel;
import java.util.List;


public interface NotificationService {

    void sendNotification(NotificationModel model);

    List<Notification> getNotificationList();
}
