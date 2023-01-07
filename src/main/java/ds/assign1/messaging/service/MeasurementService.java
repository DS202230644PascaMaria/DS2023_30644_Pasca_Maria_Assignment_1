package ds.assign1.messaging.service;

import ds.assign1.messaging.dtos.MeasurementDTO;
import ds.assign1.messaging.entities.Measurement;
import ds.assign1.messaging.repos.IMeasurementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeasurementService {
    private final IMeasurementRepo REPO;

    public List<Measurement> getAll(){
        return REPO.findAll();
    }
}
