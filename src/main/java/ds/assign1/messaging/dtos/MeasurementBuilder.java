package ds.assign1.messaging.dtos;

import ds.assign1.messaging.entities.Measurement;

import java.util.UUID;

public class MeasurementBuilder {
    public static Measurement build(MeasurementDTO dto){
        return new Measurement(dto.getTimestamp(), UUID.fromString(dto.getDevice_id()), dto.getMeasurement_value());
    }

    public static MeasurementDTO build(Measurement entity){
        return new MeasurementDTO(entity.getTimestamp(), entity.getDeviceId().toString(), entity.getValue());
    }
}
