package ds.assign1.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ds.assign1.messaging.dtos.MeasurementDTO;
import net.minidev.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class Consumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String fileBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MeasurementDTO dto = objectMapper.readValue(fileBody, MeasurementDTO.class);
            System.out.println(dto.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}