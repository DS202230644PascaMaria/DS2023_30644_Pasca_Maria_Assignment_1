package ds.assign1.messaging.service;

import ds.assign1.messaging.dtos.MeasurementBuilder;
import ds.assign1.messaging.dtos.MeasurementDTO;
import ds.assign1.messaging.entities.Measurement;
import ds.assign1.messaging.infrastructure.IDeviceMeasurement;
import ds.assign1.messaging.repos.IMeasurementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeasurementService {
    private final IMeasurementRepo REPO;
    private final IDeviceMeasurement DEVICE;

    public List<Measurement> getAll(){
        return REPO.findAll();
    }

    public Long addMeasurement(MeasurementDTO dto){
        Measurement measurement = MeasurementBuilder.build(dto);
        measurement = REPO.save(measurement);

        return measurement.getId();
    }

    public List<MeasurementDTO> findByDevice(UUID device_id){
        return REPO.findAll().stream()
                .filter(dto -> dto.getDeviceId().equals(device_id))
                .map(MeasurementBuilder::build)
                .collect(Collectors.toList());
    }

    public Double getLastHourMeasures(UUID device_id){
        List<MeasurementDTO> dtoList = this.findByDevice(device_id).stream().sorted(Comparator.comparing(MeasurementDTO::getTimestamp).reversed()).collect(Collectors.toList());
        return dtoList.get(0).getMeasurement_value() - dtoList.get(6).getMeasurement_value();
    }

    public void checkMaxConsumption(UUID device_id){
        Float maxHourlyConsumption = DEVICE.findDeviceById(device_id).getMaxHourlyConsumption();

        if(getLastHourMeasures(device_id) > maxHourlyConsumption){
            System.out.println(getLastHourMeasures(device_id) + " max hourly consumption: " + maxHourlyConsumption + "MAX HOURLY CONSUMPTIONS EXCEEDED");
        }
    }
}
