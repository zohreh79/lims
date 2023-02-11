package e.hospital.lims.dao;

import e.hospital.lims.domain.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends MongoRepository<Notification, String> {
}
