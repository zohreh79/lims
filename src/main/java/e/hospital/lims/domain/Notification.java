package e.hospital.lims.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "notification")
public class Notification {

    @Id
    @Indexed(useGeneratedName = true)
    private String id;

    private String title;
    private String text;
    private String receiver;
    private Long timestamp;
    private Long patientId;

    public Notification(String title, String text, Long timestamp, String receiver, Long patientId) {
        this.title = title;
        this.text = text;
        this.timestamp = timestamp;
        this.receiver = receiver;
        this.patientId = patientId;
    }
}
