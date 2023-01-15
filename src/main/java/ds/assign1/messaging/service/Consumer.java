package ds.assign1.messaging.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ds.assign1.messaging.dtos.MeasurementDTO;
import ds.assign1.messaging.repos.IMeasurementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeoutException;


@Component
public class Consumer {
    private final MeasurementService SERVICE;

    public Consumer(MeasurementService ms) throws IOException, TimeoutException {
        SERVICE = ms;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("goose.rmq2.cloudamqp.com");
        factory.setUsername("nddwoxcj");
        factory.setVirtualHost("nddwoxcj");
        factory.setPassword("es_KY9pmQ1EF7X3ZT-uukHFRtyBCDrkk");
        factory.setConnectionTimeout(30000);
        factory.setRequestedHeartbeat(30);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("measurements", false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            String[] splitMsg = message.split(" ");
            MeasurementDTO dto = new MeasurementDTO(Long.parseLong(splitMsg[0]), splitMsg[1], Double.parseDouble(splitMsg[2]));
            //System.out.println("Received '" + dto + "'");
            SERVICE.addMeasurement(dto);
            SERVICE.checkMaxConsumption(UUID.fromString(dto.getDevice_id()));
            //System.out.println(SERVICE.getLastHourMeasures(UUID.fromString(dto.getDevice_id())));
        };
        channel.basicConsume("measurements", true, deliverCallback, consumerTag -> { });
    }
}