package ds.assign1.messaging.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ds.assign1.devices.dtos.DeviceDTO;
import ds.assign1.messaging.dtos.MeasurementDTO;
import ds.assign1.messaging.entities.Measurement;
import ds.assign1.messaging.infrastructure.IDeviceMeasurement;
import ds.assign1.messaging.repos.IMeasurementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class Consumer {
    private final IMeasurementRepo REPO;
    private final IDeviceMeasurement DEVICES;
    private Double sum = 0.0;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String fileBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            MeasurementDTO dto = objectMapper.readValue(fileBody, MeasurementDTO.class);
            System.out.println(dto.toString());
            UUID deviceID = UUID.fromString(dto.getDevice_id());
            Measurement measurement = new Measurement();
            measurement.setTimestamp(dto.getTimestamp());
            measurement.setDeviceId(deviceID);
            measurement.setValue(dto.getMeasurement_value());
            REPO.save(measurement);
            DeviceDTO deviceDTO = DEVICES.findDeviceById(deviceID);
            if(measurement.getValue() >= deviceDTO.getMaxHourlyConsumption()){
                System.out.println("Value exceeded!");
                return;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}