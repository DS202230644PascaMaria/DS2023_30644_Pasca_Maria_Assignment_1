package ds.assign1.messaging.controllers;

import ds.assign1.messaging.dtos.MeasurementDTO;
import ds.assign1.messaging.entities.Measurement;
import ds.assign1.messaging.service.Consumer;
import ds.assign1.messaging.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/measures")
public class MeasurementController {
    private final MeasurementService SERVICE;

    @Autowired
    public MeasurementController(MeasurementService SERVICE){
        this.SERVICE = SERVICE;

        try {
            Consumer consumer = new Consumer(SERVICE);
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Measurement> getAll(){
        return SERVICE.getAll();
    }

}
