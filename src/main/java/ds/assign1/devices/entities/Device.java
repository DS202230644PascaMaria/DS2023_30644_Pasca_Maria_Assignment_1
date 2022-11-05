package ds.assign1.devices.entities;

import java.util.UUID;

public class Device {
    private UUID id;
    private String description;
    private String address;
    private Long maxHourlyConsumption;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMaxHourlyConsumption() {
        return maxHourlyConsumption;
    }

    public void setMaxHourlyConsumption(Long maxHourlyConsumption) {
        this.maxHourlyConsumption = maxHourlyConsumption;
    }
}
