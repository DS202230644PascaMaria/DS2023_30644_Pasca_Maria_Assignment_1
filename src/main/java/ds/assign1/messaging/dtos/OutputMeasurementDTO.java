package ds.assign1.messaging.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class OutputMeasurementDTO {
    private LocalDateTime localDateTime;
    private Double measurement_value;

    public OutputMeasurementDTO(Long timestamp, Double measurement_value){
        this.measurement_value = measurement_value;
        this.localDateTime = LocalDateTime.ofEpochSecond(timestamp, getLocalDateTime().getNano(), ZoneOffset.UTC);
    }
}
