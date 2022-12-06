package ds.assign1.devices.services;

import ds.assign1.devices.repos.DevicesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class DeviceValidators {
    private final DevicesRepo REPO;

    public void hourlyConsumptionValidator(float maxHourlyConsumption){
        if(maxHourlyConsumption < 0){
            throw new RuntimeException("Hourly consumption cannot be lower than 0");
        }
    }

    public void descriptionValidator(String description){
        Pattern p = Pattern.compile("[a-zA-Z]+[0-9]*([_-]*[a-zA-Z])?");

        String[] words = description.split(" ");

        if(!Arrays.stream(words).allMatch(word -> p.matcher(word).matches())){
            throw new IllegalArgumentException("Name may contain special characters or may start with lower case letters");
        }
    }

    public void addressValidator(String address){
        Pattern p = Pattern.compile("[A-Z][a-z]*((-| )[A-Z][a-z]*)?, ((str|Str).?|(Strada|strada)) [A-Z][a-z]*( [A-Z][a-z]*)*, ((nr|Nr).?|(numarul|Numarul)) [0-9]+, [A-Z][a-z]*(( |-)[A-Z][a-z]*)*(, [A-Z][a-z]*( [A-Z][a-z]*)*)?");
        Matcher m = p.matcher(address);

        if(!m.matches()){
            throw new IllegalArgumentException("Address doesn't follow the wanted form");
        }
    }
}
