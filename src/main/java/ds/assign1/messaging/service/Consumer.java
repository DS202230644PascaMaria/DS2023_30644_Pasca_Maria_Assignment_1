package ds.assign1.messaging.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ds.assign1.messaging.dtos.MeasurementDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;


@Component
public class Consumer {

    public Consumer() throws IOException, TimeoutException {
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
            System.out.println("Received '" + dto + "'");
        };
        channel.basicConsume("measurements", true, deliverCallback, consumerTag -> { });
    }

}