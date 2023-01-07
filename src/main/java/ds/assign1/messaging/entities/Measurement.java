package ds.assign1.messaging.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long timestamp;
    private UUID deviceId;
    private Double value;

    public Measurement(Long timestamp, UUID deviceId, Double value){
        this.timestamp = timestamp;
        this.deviceId = deviceId;
        this.value = value;
    }
}
