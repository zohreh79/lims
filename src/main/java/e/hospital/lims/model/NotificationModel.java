package e.hospital.lims.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationModel {

    private String topic;
    private String title;
    private String message;
    private String receiver;
    private Long patientId;
}
