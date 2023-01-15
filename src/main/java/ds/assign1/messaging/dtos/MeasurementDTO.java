package ds.assign1.messaging.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDTO {
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("device_id")
    private String device_id;
    @JsonProperty("measurement_value")
    private Double measurement_value;
}
