package e.hospital.lims.model;

import lombok.Data;

@Data
public class NotificationModel {

    private String topic;
    private String title;
    private String message;
    private String receiver;
    private Long patientId;
}
