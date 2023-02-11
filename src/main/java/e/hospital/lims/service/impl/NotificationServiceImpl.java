package e.hospital.lims.service.impl;

import e.hospital.lims.dao.NotificationDao;
import e.hospital.lims.domain.Notification;
import e.hospital.lims.model.NotificationModel;
import e.hospital.lims.service.NotificationService;
import lombok.SneakyThrows;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private NotificationDao notificationDao;

    @Value("${lims.rabbitmq.exchange}")
    private String exchange;

    @Value("${lims.rabbitmq.routingkey}")
    private String routingKey;

    @Override
    public List<Notification> getNotificationList() {
        return notificationDao.findAll();
    }

    @SneakyThrows
    @Override
    public void sendNotification(NotificationModel model) {
        notificationDao.save(
                new Notification(
                        model.getTitle()
                        , model.getMessage()
                        , System.currentTimeMillis() / 1000
                        , model.getReceiver()
                        , model.getPatientId())
        );

        amqpTemplate.convertAndSend(exchange, routingKey, model);
        System.out.println("Send msg = " + model);

    }
}
