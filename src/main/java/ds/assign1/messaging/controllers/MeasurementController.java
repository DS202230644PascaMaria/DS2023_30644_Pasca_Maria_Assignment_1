package ds.assign1.messaging.controllers;

import ds.assign1.messaging.dtos.MeasurementDTO;
import ds.assign1.messaging.entities.Measurement;
import ds.assign1.messaging.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measures")
public class MeasurementController {
    private final MeasurementService SERVICE;

    @GetMapping
    public List<Measurement> getAll(){
        return SERVICE.getAll();
    }

}
