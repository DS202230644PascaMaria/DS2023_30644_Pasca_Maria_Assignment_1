package ds.assign1.messaging.controllers;

import ds.assign1.messaging.dtos.MeasurementDTO;
import ds.assign1.messaging.dtos.OutputMeasurementDTO;
import ds.assign1.messaging.entities.Measurement;
import ds.assign1.messaging.service.Consumer;
import ds.assign1.messaging.service.MeasurementService;
import ds.assign1.websockets.WebsocketController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@RestController
@CrossOrigin
@RequestMapping("/measures")
public class MeasurementController {
    private final MeasurementService SERVICE;
    @Autowired
    public MeasurementController(MeasurementService SERVICE){
        this.SERVICE = SERVICE;

        try {
            Consumer consumer = new Consumer(SERVICE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Measurement> getAll(){
        return SERVICE.getAll();
    }

    @GetMapping("/{device_id}")
    public List<MeasurementDTO> getMeasurementsById(@PathVariable UUID device_id){
        //return SERVICE.convertToOutput(device_id);
        return SERVICE.findByDevice(device_id);
    }
}